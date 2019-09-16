package com.balanzasgj.app.conn.serial;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import javafx.application.Platform;
import javafx.scene.control.TextField;

/**
 * A simple TCP server. When a client connects, it sends the client the current
 * datetime, then closes the connection. This is arguably the simplest server
 * you can write. Beware though that a client has to be completely served its
 * date before the server will be able to handle another client.
 */
public class SocketConnection {
	private SerialPort serialPort;
	private Thread threadReader;
	
	public SocketConnection() {
		super();
	}

	public void connect(String portName, TextField txtNumberSerial) throws Exception {
		CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
		if (portIdentifier.isCurrentlyOwned()) {
			System.out.println("Error: Port is currently in use");
		} else {
			CommPort commPort = portIdentifier.open(this.getClass().getName(), 2000);

			if (commPort instanceof SerialPort) {
				serialPort = (SerialPort) commPort;
				serialPort.setSerialPortParams(
						57600, 
						SerialPort.DATABITS_8, 
						SerialPort.STOPBITS_1,
						SerialPort.PARITY_NONE);						
				threadReader = new Thread(new SerialReader(serialPort.getInputStream(), txtNumberSerial));
				threadReader.start();
				//threadWrite = new Thread(new SerialWriter(serialPort.getOutputStream()));
				//threadWrite.start();

			} else {
				System.out.println("Error: Only serial ports are handled by this example.");
			}
		}
	}
	
	public void closeSocket() {
		//serialPort.addEventListener(this);
		serialPort.notifyOnDataAvailable(true);
		serialPort.notifyOnOutputEmpty(true);
		serialPort.close();
       
	}

	/** */
	public static class SerialReader implements Runnable {
		String val;
		TextField txtNumberSerial;
		InputStream in;

		public SerialReader(InputStream in, TextField txtNumberSerial) {
			this.in = in;
			this.txtNumberSerial = txtNumberSerial;
		}

		public void run() {
			val = "";
			byte[] buffer = new byte[1024];
			int len = -1;
			try {
				while ((len = this.in.read(buffer)) > -1) {
					val = new String(buffer, 0, len);	
					if(!val.equals("")) {
						System.out.println(val);
					}
					
					/*
					Platform.runLater(() -> {
						if(!val.equals("")) {
							System.out.println(val);
							txtNumberSerial.clear();
							txtNumberSerial.appendText(val);
						}						
					});
					*/
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/** */
	public static class SerialWriter implements Runnable {
		OutputStream out;

		public SerialWriter(OutputStream out) {
			this.out = out;
		}

		public void run() {
			try {
				int c = 0;
				while ((c = System.in.read()) > -1) {
					this.out.write(c);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		SocketConnection socket = new SocketConnection();
		try {
			
			socket.connect("COM1", null);
			System.out.println("conectado");
			Thread.sleep(5000);
			System.out.println("por desconectar");
			socket.closeSocket();
			System.out.println("desconectado");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
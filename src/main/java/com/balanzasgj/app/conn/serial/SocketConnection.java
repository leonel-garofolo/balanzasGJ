package com.balanzasgj.app.conn.serial;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.TooManyListenersException;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

public class SocketConnection implements SerialPortEventListener {
	private SerialPort serialPort;
	public static String st;
	public char[] c;

	/**
	 * Creates new form reading
	 */
	public static String dataToSend;
	private InputStream input;
	private OutputStream output;
	private static final int TIME_OUT = 2000;
	private static final int DATA_RATE = 9600;
	public static final char STX = 0x02;
	public static final char ETX = 0x03;
	public static final char EOT = 0x04;
	public static final char ENQ = 0x05;
	public static final char ACK = 0x06;

	public SocketConnection() {
		super();
	}

	public void conectar(String portName, int dataRate, int dataBits, int stopBits, int parity, int timeOut)
			throws Exception {
		CommPortIdentifier portId = null;
		Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();
		// iterate through, looking for the port
		while (portEnum.hasMoreElements()) {
			CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
			if (currPortId.getName().equals(portName)) {
				portId = currPortId;
				break;
			}
		}
		serialPort = (SerialPort) portId.open(this.getClass().getName(), timeOut);
		serialPort.setSerialPortParams(dataRate, dataBits, stopBits, parity);
		input = serialPort.getInputStream();
		output = serialPort.getOutputStream();
	}

	public synchronized void close() {
		if (serialPort != null) {
			serialPort.removeEventListener();
			serialPort.close();
		}
	}

	public void sendData(String data) {
		(new Thread(new SerialWriter(output, data))).start();
	}

	public synchronized void serialEvent(SerialPortEvent oEvent) {
		if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
			try {
				int available = input.available();
				byte[] chunk = new byte[available];
				input.read(chunk, 0, available);
				st = new String(chunk);
				System.out.print(st);
				try {
					Thread.sleep(5000);
				} catch (InterruptedException ex) {
					Thread.currentThread().interrupt();
				}
				// Displayed results are codepage dependent
			} catch (IOException e) {
				System.out.println("IO Error Occurred: " + e.toString());

			}
		}
	}

	public static class SerialWriter implements Runnable {

		OutputStream out;
		String data;

		public SerialWriter(OutputStream out, String data) {
			this.out = out;
			this.data = data;
		}

		public void run() {
			if (data != null) {
				data = data + "\r\n";
				while(true) {
					try {				
						out.write(data.getBytes());			
						out.write(ENQ);		
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}

		public void writetoport(String send) {
			try {				
				out.write(send.getBytes());				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public InputStream getInput() {
		return input;
	}

	public void addEventSocket(SerialPortEventListener event) {
		try {
			serialPort.addEventListener(event);
			serialPort.notifyOnDataAvailable(true);
		} catch (TooManyListenersException e) {
			e.printStackTrace();
		}
	}
}
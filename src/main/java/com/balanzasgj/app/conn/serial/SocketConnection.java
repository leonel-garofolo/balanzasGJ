package com.balanzasgj.app.conn.serial;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;
import java.util.TooManyListenersException;

import org.apache.log4j.Logger;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

public class SocketConnection implements SerialPortEventListener {
	final static Logger logger = Logger.getLogger(SocketConnection.class);
	private SerialPort serialPort;
	public static String st;
	public char[] c;

	/**
	 * Creates new form reading
	 */
	public static String dataToSend;
	private InputStream input;
	private OutputStream output;
	public static final char STX = 0x02;
	public static final char ETX = 0x03;
	public static final char EOT = 0x04;
	public static final char ENQ = 0x05;
	public static final char ACK = 0x06;

	public SocketConnection() {
		super();
	}

	public boolean conectar(String portName, int dataRate, int dataBits, int stopBits, int parity, int timeOut)
			throws Exception {	
		CommPortIdentifier portId =CommPortIdentifier.getPortIdentifier(portName);		
		if(portId != null) {
			serialPort = (SerialPort) portId.open(this.getClass().getName(), timeOut);
			serialPort.setSerialPortParams(dataRate, dataBits, stopBits, parity);		
			input = serialPort.getInputStream();
			output = serialPort.getOutputStream();
			logger.info("Connect Port: " + portName + " | Velocidad (dataRate): " + dataRate + " | Bit paridad (dataBits): " + dataBits + " | stop bit: " + stopBits + " | paridad: " + parity);
			return true;
		}
		logger.error("error no pudo conectar con: " + portName);
		return false;
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
				logger.info("type data: DATA_AVAILABLE");
				int available = input.available();
				byte[] chunk = new byte[available];
				input.read(chunk, 0, available);
				st = new String(chunk);
				logger.info("RX -> Sync: " + st);
				try {
					Thread.sleep(5000);
				} catch (InterruptedException ex) {
					Thread.currentThread().interrupt();
				}
				// Displayed results are codepage dependent
			} catch (IOException e) {
				System.out.println("IO Error Occurred: " + e.toString());

			}
		} else {			
			try {
				logger.info("type data: " + oEvent.getEventType());
								
				int available = input.available();
				byte[] chunk = new byte[available];
				input.read(chunk, 0, available);				
				logger.info("RX -> Sync No DATA: " + new String(chunk));
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
			logger.info("data: " + data);
			if (data != null) {
				data = data + "\r\n";
				while(true) {
					try {				
						out.write(data.getBytes());			
						out.write(ENQ);		
					} catch (IOException e) {
						logger.error(e);
					}
				}
			}
		}

		public void writetoport(String send) {
			try {				
				out.write(send.getBytes());				
			} catch (IOException e) {
				logger.error(e);
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
			logger.error(e);
		}
	}
}
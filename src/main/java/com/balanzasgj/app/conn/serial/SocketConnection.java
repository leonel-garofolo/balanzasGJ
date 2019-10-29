package com.balanzasgj.app.conn.serial;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.TooManyListenersException;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

public class SocketConnection implements SerialPortEventListener {
	SerialPort serialPort;
	public static String st;
	public char[] c;	

	/**
	 * Creates new form reading
	 */
	
	private InputStream input;	
	private static final int TIME_OUT = 2000;
	private static final int DATA_RATE = 9600;

	public SocketConnection() {
		super();	
	}

	public void conectar(String portName, int dataRate, int dataBits, int stopBits, int parity, int timeOut) throws Exception {
		CommPortIdentifier portId = null;
		Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();
		// iterate through, looking for the port
		while (portEnum.hasMoreElements()) {
			CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
			if(currPortId.getName().equals(portName)) {
				portId = currPortId;
				break;
			}
		}
		serialPort = (SerialPort) portId.open(this.getClass().getName(), timeOut);
		serialPort.setSerialPortParams(dataRate, dataBits, stopBits, parity);
		input = serialPort.getInputStream();		
	}

	public synchronized void close() {
		if (serialPort != null) {
			serialPort.removeEventListener();
			serialPort.close();
		}
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
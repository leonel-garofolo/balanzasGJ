package com.balanzasgj.app.conn.serial;

import java.io.InputStream;
import java.io.OutputStream;

import org.apache.log4j.Logger;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;

public class JSocketConnection {
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

	public JSocketConnection() {
		super();
	}

	public boolean conectar(String portName, int dataRate, int dataBits, int stopBits, int parity, int timeOut){
		try {
			serialPort = SerialPort.getCommPort(portName);			
			serialPort.openPort();					
			serialPort.setComPortParameters(dataRate, dataBits, stopBits, parity);
			serialPort.setComPortTimeouts(timeOut, 0, 0);			
			input = serialPort.getInputStream();		
			output = serialPort.getOutputStream();		
			logger.info("Connect Port: " + portName + " | Velocidad (dataRate): " + dataRate + " | Bit paridad (dataBits): " + dataBits + " | stop bit: " + stopBits + " | paridad: " + parity);
			return true;
		}catch (Exception e) {
			logger.error(e);
		}
			
		serialPort.closePort();
		return false;
	}
	

	public synchronized void close() {
		if (serialPort != null) {
			serialPort.closePort();
		}
	}
	
	public InputStream getInput() {
		return input;
	}

	
	public void addEventSocket(SerialPortDataListener event) {		
		serialPort.addDataListener(event);
	}	
	
	public SerialPort getSerialPort() {
		return this.serialPort;
	}
}

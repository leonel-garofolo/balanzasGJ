package com.balanzasgj.app.conn.serial;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.TooManyListenersException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;

public class SerialChannel extends AbstractConnectionChannel implements SerialPortEventListener {
	 private static final Logger logger = LogManager.getLogger("SerialChannel");

	private CommPortIdentifier portId;
	private SerialPort serialPort;
	private int baudrate;
	private int databits;
	private int stopbits;
	private int parity;
	private int flowControlMode = SerialPort.FLOWCONTROL_NONE;
	private boolean connected = false;
	private DataEventListener dataEventListener;

	public static int FLOWCONTROL_NONE = SerialPort.FLOWCONTROL_NONE;
	public static int FLOWCONTROL_RTSCTS_IN = SerialPort.FLOWCONTROL_RTSCTS_IN;
	public static int FLOWCONTROL_RTSCTS_OUT = SerialPort.FLOWCONTROL_RTSCTS_OUT;
	public static int FLOWCONTROL_XONXOFF_IN = SerialPort.FLOWCONTROL_XONXOFF_IN;
	public static int FLOWCONTROL_XONXOFF_OUT = SerialPort.FLOWCONTROL_XONXOFF_OUT;
	
	public static int DATABITS_5 = SerialPort.DATABITS_5;
	public static int DATABITS_6 = SerialPort.DATABITS_6;
	public static int DATABITS_7 = SerialPort.DATABITS_7;
	public static int DATABITS_8 = SerialPort.DATABITS_8;
	
	public static int PARITY_EVEN = SerialPort.PARITY_EVEN;
	public static int PARITY_MARK = SerialPort.PARITY_MARK;
	public static int PARITY_NONE = SerialPort.PARITY_NONE;
	public static int PARITY_ODD = SerialPort.PARITY_ODD;
	public static int PARITY_SPACE = SerialPort.PARITY_SPACE;
	
	

	public static List<String> listSerialPorts() {

		Enumeration ports = CommPortIdentifier.getPortIdentifiers();
		List<String> portList = new ArrayList<String>();

		while (ports.hasMoreElements()) {

			CommPortIdentifier port = (CommPortIdentifier) ports.nextElement();
			if (port.getPortType() == CommPortIdentifier.PORT_SERIAL) {

				portList.add(port.getName());
			}
		}

		return portList;
	}

	public SerialChannel(String portName, int baudrate, int databits, int stopbits, int parity, int flowControlMode) throws ConnectionChannelException {		
		logger.debug("Initializing " + SerialChannel.class);

		try {
			portId = CommPortIdentifier.getPortIdentifier(portName);
		} catch (NoSuchPortException e) {
			throw new ConnectionChannelException(String.format("Error getting Serial channel. No such port exception: %s", portName), e);
		}

		this.baudrate = baudrate;
		this.databits = databits;
		this.stopbits = stopbits;
		this.parity = parity;
		this.flowControlMode = flowControlMode;
	}

	@Override
	public String getName() {
		return portId.getName();
	}

	@Override
	public void connect() throws ConnectionChannelException {
		
		if (getConnected()) throw new ConnectionChannelException("Error while connecting: Serial channel already connected");

		try {

			serialPort = (SerialPort) portId.open("jaLISco", 2000);
			serialPort.setSerialPortParams(baudrate, databits, stopbits, parity);
			serialPort.setFlowControlMode(flowControlMode);

			inputStream = serialPort.getInputStream();
			outputStream = serialPort.getOutputStream();

			this.connected = true;

		} catch (PortInUseException | UnsupportedCommOperationException | IOException e) {

			logger.error(String.format("Error while connecting: %s", e.getMessage()), e);
			throw new ConnectionChannelException(String.format("Error while connecting: %s", e.getMessage()), e);
		}
	}

	@Override
	public void disconnect() throws ConnectionChannelException {

		if (!getConnected()) throw new ConnectionChannelException("Error while disconnecting: Serial channel not connected");

		// TODO: check closing issues in url (http://code.google.com/p/myrobotlab/source/browse/trunk/myrobotlab/src/org/myrobotlab/serial/gnu/SerialDeviceGNU.java)

		try {

			serialPort.removeEventListener();

			outputStream.flush();

			inputStream.close();
			outputStream.close();

		} catch (IOException e) {

			logger.error(String.format("Error while disconnecting: %s", e.getMessage()), e);
			throw new ConnectionChannelException(String.format("Error while disconnecting: %s", e.getMessage()), e);
		}


		inputStream = null;
		outputStream = null;

		serialPort.close();

		this.connected = false;
	}

	public boolean getConnected() {
		return connected;
	}

	@Override
	public void addDataEventListener(DataEventListener dataEventListener) throws ConnectionChannelException {

		this.dataEventListener = dataEventListener;

		try {

			serialPort.addEventListener(this);

		} catch (TooManyListenersException e) {

			logger.error(String.format("Error while adding listener: Too many listeners for Serial channel [%s]", portId.getName()), e);
			throw new ConnectionChannelException(String.format("Error while adding listener: Too many listeners for Serial channel [%s]", portId.getName()), e);
		}
	}

	@Override
	public void removeDataEventListener() {

		dataEventListener = null;
		serialPort.removeEventListener();
	}

	@Override
	public void enableListener() {

		serialPort.notifyOnDataAvailable(true);
	}

	@Override
	public void disableListener() {
		
		serialPort.notifyOnDataAvailable(false);
	}

	@Override
	public void serialEvent(SerialPortEvent event) {

		char c;
		while ( (c = getc(false)) > 0 ) {

			try {

				dataEventListener.dataEvent(c);

			} catch (Exception e) {

				e.printStackTrace();
				logger.error(e.getMessage(), e);
			}
		}

	}


	public SerialPort getSerialPort() { return serialPort; }

}
package com.balanzasgj.app.conn.serial;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import gnu.io.CommPortIdentifier;
import gnu.io.CommPortOwnershipListener;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;
import javafx.scene.control.TextField;

public class SerialOwnership implements SerialPortEventListener {
	private SerialPort serialPort;
	private OutputStream outStream;
	private InputStream inStream;
	private TextField txtNumberSerial;
	
	public SerialOwnership(TextField txtNumberSerial) {
		this.txtNumberSerial = txtNumberSerial;
	}

	public void connect(String portName, String id) throws IOException {
		try {
			// Obtain a CommPortIdentifier object for the port you want to open
			CommPortIdentifier portId = CommPortIdentifier.getPortIdentifier(portName);

			// Add ownership event listener
			portId.addPortOwnershipListener(new SerialOwnershipHandler(id));

			// Get the port's ownership
			serialPort = (SerialPort) portId.open("Demo application", 5000);
			// Set the parameters of the connection.
			setSerialPortParameters();

			// Open the input and output streams for the connection. If they won't
			// open, close the port before throwing an exception.
			outStream = serialPort.getOutputStream();
			inStream = serialPort.getInputStream();
			/*
			try {
				serialPort.addEventListener(this);
			} catch (TooManyListenersException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            serialPort.notifyOnDataAvailable(true);
            */
		} catch (NoSuchPortException e) {
			throw new IOException(e.getMessage());
		} catch (PortInUseException e) {
			throw new IOException(e.getMessage());
		} catch (IOException e) {
			serialPort.close();
			throw e;
		}
	}

	/**
	 * Get the serial port input stream
	 * 
	 * @return The serial port input stream
	 */
	public InputStream getSerialInputStream() {
		return inStream;
	}

	/**
	 * Get the serial port output stream
	 * 
	 * @return The serial port output stream
	 */
	public OutputStream getSerialOutputStream() {
		return outStream;
	}

	public void disconnect() {
		if (serialPort != null) {			
			try {
				serialPort.removeEventListener();				
				// close the i/o streams.
				outStream.close();
				inStream.close();
			} catch (IOException ex ) {
				ex.printStackTrace();
			} 
			// Close the port.
			serialPort.close();
		}
	}

	/**
	 * Sets the serial port parameters
	 */
	private void setSerialPortParameters() throws IOException {
		int baudRate = 57600; // 57600bps

		try {
			// Set serial port to 57600bps-8N1..my favourite
			serialPort.setSerialPortParams(baudRate, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE);

			serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
		} catch (UnsupportedCommOperationException ex) {
			throw new IOException("Unsupported serial port parameter");
		}
	}

	private class SerialOwnershipHandler implements CommPortOwnershipListener {
		String id;

		public SerialOwnershipHandler(String id) {
			this.id = id;
		}

		public void ownershipChange(int type) {
			switch (type) {
			case CommPortOwnershipListener.PORT_OWNED:
				System.out.println(id + ":We got the port");
				break;
			case CommPortOwnershipListener.PORT_UNOWNED:
				System.out.println(id + ":We've just lost our port ownership");
				break;
			case CommPortOwnershipListener.PORT_OWNERSHIP_REQUESTED:
				System.out.println(id + ":Someone is asking our port's ownership");
				break;
			}
		}
	}

	public static void main(String[] args) {
		/*
		SerialOwnership serialOwnershipTest = new SerialOwnership(null);

		try {
			serialOwnershipTest.connect("COM1", "1");			
			System.out.println("finish");
		} catch (IOException ex) {
			System.err.println(ex.getMessage());
		}

		serialOwnershipTest.disconnect();
		*/
		try {
			SerialChannel connectionChannel = new SerialChannel(
					"COM1"
					, 57600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE, 1);
			connectionChannel.connect();
			
			
			connectionChannel.disconnect();
		} catch (ConnectionChannelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

	public static String st;

	@Override
	public void serialEvent(SerialPortEvent oEvent) {
		if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
			try {
				int available = inStream.available();
				byte[] chunk = new byte[available];
				inStream.read(chunk, 0, available);
				st = new String(chunk);

				System.out.print(st);	
				/*
				Platform.runLater(() -> {					
					txtNumberSerial.clear();
					txtNumberSerial.appendText(st);										
				});
				*/
				
			} catch (IOException e) {
				System.out.println("IO Error Occurred: " + e.toString());

			}
		}

	}
}
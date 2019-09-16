package com.balanzasgj.app.conn.serial;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AbstractIndicador implements Interceptor{
	private static final Logger logger = LogManager.getLogger("SerialChannel");
	
	// serial channel configurations
	private String portName;
	private Integer baudrate;
	private Integer databits;
	private Integer stopbits;
	private Integer parity;
	private Integer flowControlMode;
	
	private boolean connected = false;
	
	protected AbstractConnectionChannel connectionChannel;
	
	public AbstractIndicador(String portName, int baudrate, int databits8, int stopbits, int parity, int flowControlMode) {
		System.out.println("conectors");
		logger.info(".conectors starts.");
		this.portName = portName;
		this.baudrate = baudrate;
		this.databits = databits8;
		this.stopbits = stopbits;
		this.parity= parity;
		this.flowControlMode= flowControlMode;
	}
	
	public String getPortName() {
		return portName;
	}
	public void setPortName(String portName) {
		this.portName = portName;
	}
	public Integer getBaudrate() {
		return baudrate;
	}
	public void setBaudrate(Integer baudrate) {
		this.baudrate = baudrate;
	}
	public Integer getDatabits() {
		return databits;
	}
	public void setDatabits(Integer databits) {
		this.databits = databits;
	}
	public Integer getStopbits() {
		return stopbits;
	}
	public void setStopbits(Integer stopbits) {
		this.stopbits = stopbits;
	}
	public Integer getParity() {
		return parity;
	}
	public void setParity(Integer parity) {
		this.parity = parity;
	}
	public Integer getFlowControlMode() {
		return flowControlMode;
	}
	public void setFlowControlMode(Integer flowControlMode) {
		this.flowControlMode = flowControlMode;
	}
		
	@Override
	public boolean isConnected() {
		return connected;
	}
	@Override
	public void connect() throws ConnectionChannelException {
		connectionChannel = new SerialChannel(getPortName(), getBaudrate(), getDatabits(), getStopbits(), getParity(), getFlowControlMode());
	}
	@Override
	public void disconnect() throws ConnectionChannelException {
		logger.info(".disconnect starts.");
		System.out.println(".disconnect starts.");
		if (!isConnected()) {

			throw new ConnectionChannelException("Analyzer is not connected, cannot disconnect");
		}

		try {

			connectionChannel.disconnect();

		} catch (ConnectionChannelException e) {
			throw new ConnectionChannelException(String.format("Error while disconnecting analyzer from channel [%s]", connectionChannel.getName()), e);
		}

		switchConnected();

		logger.info(".disconnect ends.");
		
	}
	
	protected void switchConnected() {
		this.connected = !this.connected;
	}
	
	public String receive() throws Exception {

		StringBuffer buffer = new StringBuffer();
		char c;
		while ((c = connectionChannel.getc(false)) != 0) {
			buffer.append(c);
		} 
		System.out.println(buffer.toString());
		logger.debug("Ending response reception");
		return buffer.toString();
	}
	
	public String sendAndReceive(String message) throws Exception {
		if(connectionChannel != null) {
			connectionChannel.getNc(5);
		}
		
		//System.out.println(connectionChannel.getNc(5));
		System.out.println(message);
		return "asdas";
	}
}

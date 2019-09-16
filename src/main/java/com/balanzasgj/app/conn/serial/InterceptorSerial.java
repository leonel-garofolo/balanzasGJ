package com.balanzasgj.app.conn.serial;

import gnu.io.SerialPort;

public class InterceptorSerial extends AbstractIndicador {
	
	public InterceptorSerial(){
		super("COM1"
				, 57600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE, 1);


		//messageProcessor = new XS1000iMessageProcessor(t);
	}
}

package balanzasGJ.serial;

import java.io.IOException;

import com.balanzasgj.app.conn.serial.SocketConnection;

import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

public class ConnTest implements SerialPortEventListener {
	private SocketConnection socket;
	public void rafagasEnvios() {
		socket= new SocketConnection();
		try {
			socket.conectar("COM2", 9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE, 2000);
			socket.addEventSocket(this);
			socket.sendData("D004500");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	
	public static void main(String[] ars) {
		ConnTest a = new ConnTest();
		a.rafagasEnvios();
	}

	@Override
	public void serialEvent(SerialPortEvent event) {
		if (event.getEventType() == SerialPortEvent.DATA_AVAILABLE) {	
			int available;
			try {
				available = socket.getInput().available();
				byte[] chunk = new byte[available];
				socket.getInput().read(chunk, 0, available);
				String sBufferConnection = new String(chunk);
				System.out.println(sBufferConnection);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
}

package com.balanzasgj.app.conn.serial;

public interface ConnectionChannel {

	public String getName();

	public void connect() throws ConnectionChannelException;
	public void disconnect() throws ConnectionChannelException;

	public boolean getConnected();

	public char getc(boolean allowLog);
	public char getc(int timeout) throws ConnectionChannelException;
	public char getNc(int timeout) throws ConnectionChannelException;
	
	public void putc(char c);
	public void puts(String str);

	public void addDataEventListener(DataEventListener dataEventListener) throws ConnectionChannelException;
	public void removeDataEventListener() throws ConnectionChannelException;

	public void enableListener();
	public void disableListener();

}

package com.balanzasgj.app.conn.serial;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.logging.log4j.Logger;

public abstract class AbstractConnectionChannel implements ConnectionChannel {

	protected static Logger logger;

	protected InputStream inputStream;
	protected OutputStream outputStream;


	@Override
	public abstract void connect() throws ConnectionChannelException;

	@Override
	public abstract void disconnect() throws ConnectionChannelException;

	@Override
	public abstract boolean getConnected();

	/**
	 * Reads a char from the channel with or without logging
	 *
	 * @param allowLog whether to log or not
	 * @return a char read from the channel
	 */
	@Override
	public char getc(boolean allowLog) {

		try {

			int i = inputStream.read();        // if there is nothing to read, returns -1,
			char c = (i < 0 ? 0 : (char) i); // this casted to char represents 65535, making it difficult to handle
			if (allowLog) logger.debug("Rx: " );
			return c;

		} catch (IOException e) {
			logger.error("Error reading byte, returning " , e);
			return 0;
		}
	}

	/**
	 * Reads a char from the channel with logging and timeout control
	 *
	 * @param timeout time to wait for a char, expressed in milliseconds
	 * @return a char read from the channel
	 * @throws ConnectionChannelException
	 */
	@Override
	public char getc(int timeout) throws ConnectionChannelException {

		boolean timeIsOut = true;
		char c = 0;
		long startTime = System.currentTimeMillis();

		while ((System.currentTimeMillis() - startTime) < timeout) {

			if ( (c = getc(false)) > 0) {
                timeIsOut = false;
                break;
			}

			try {
				Thread.sleep(0, 100); //Need to sleep some time to prevent high CPU loading
			} catch (InterruptedException ex) {
				// Do nothing
			}

		}

        if (timeIsOut) {
            throw new ConnectionChannelException("Timeout while reading char from channel. Timeout: " + timeout);
        }

//        logger.debug("Rx: " + ReformatForLog(c));
        //System.out.println("c: " + ReformatForLog(c));
        return c;
	}

	/**
	 * Writes a char to the output stream of the channel
	 * @param c the char to write
	 */
	@Override
	public void putc(char c) {

		try {

			outputStream.write(c);
			if(logger != null)
				logger.debug("Tx: " + c);
			else
				System.out.println("WARNING: com.wienerlab.connection.channel.AbstractConnectionChannel.logger IS NULL");
			
			
		} catch (IOException e) {
			logger.error("Error writing byte: " + c, e);
		}
	}

	/**
	 * Writes a string to the output stream of the channel
	 * @param str the string to write
	 */
	@Override
	public void puts(String str) {

		try {

			outputStream.write(str.getBytes());
			logger.debug("Tx: " + str);

		} catch (IOException e) {
			logger.error("Error writing string: \"" + str + "\"", e);
		}
	}

	@Override
	public abstract void addDataEventListener(DataEventListener dataEventListener) throws ConnectionChannelException;

	@Override
	public abstract void removeDataEventListener() throws ConnectionChannelException;

	@Override
	public abstract void enableListener();

	@Override
	public abstract void disableListener();

	
	
	/**
	 * Reads a char from the channel with logging and timeout control
	 *
	 * @param timeout time to wait for a char, expressed in milliseconds
	 * @return a char read from the channel
	 * @throws ConnectionChannelException
	 */
	@Override
	public char getNc(int timeout) throws ConnectionChannelException {

		boolean timeIsOut = true;
		char c = 0;
		long startTime = System.currentTimeMillis();

		while ((System.currentTimeMillis() - startTime) < timeout) {
			int i = -1;
			try {
				i = inputStream.read();
			} catch (IOException e) {
				logger.error("Error reading byte, returning " + 0, e);
			}
				c = (char) i;
					
			if ( c >= 0) {
                timeIsOut = false;
                break;
			}

			try {
				Thread.sleep(0, 100); //Need to sleep some time to prevent high CPU loading
			} catch (InterruptedException ex) {
				// Do nothing
			}

		}

        if (timeIsOut) {
            throw new ConnectionChannelException("Timeout while reading char from channel. Timeout: " + timeout);
        }

//        logger.debug("Rx: " + ReformatForLog(c));
        return c;
	}
	
	
	
	
}

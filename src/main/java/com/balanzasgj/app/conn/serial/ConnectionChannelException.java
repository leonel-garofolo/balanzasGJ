package com.balanzasgj.app.conn.serial;

public class ConnectionChannelException extends Exception {

	private static final long serialVersionUID = 5126130168533846905L;

	public ConnectionChannelException(final String msg) {
		super(msg);
	}

	public ConnectionChannelException(final String msg, final Throwable t) {
		super(msg, t);
	}

	@Override
	public String toString() {
		return String.format("%s: %s", getClass().getName(), getMessage());
	}
}

package com.balanzasgj.app.conn.serial;

public class LabConnConstants {

	public static final char ENDOFSTREAM = (char) -1;
	public static final char NUL = 0;
	public static final char SOH = 1;
	public static final char STX = 2;
	public static final char ETX = 3;
	public static final char EOT = 4;
	public static final char ENQ = 5;
	public static final char ACK = 6;
	public static final char BEL = 7;
	public static final char  HT = 9;
	public static final char  LF = 10;
	public static final char  VT = 11; // 0x0B
	public static final char  CR = 13;
	public static final char  SI = 15; // 0x0F
	public static final char DLE = 16;
	public static final char DC1 = 17;
	public static final char DC2 = 18;
	public static final char SP = 20;
	public static final char NAK = 21;
	public static final char ETB = 23;
	public static final char EOF = 26;
	public static final char FS = 28; // 0x1C
	public static final char GS = 29; // 0x1C
	public static final char RS = 30; // 0x1C
	
	public static final char XON = 17; //DC1
	public static final char XOFF = 19;
	
	public static final char SPACE = 32;
	public static final char ASTERISK = 42;
	public static final char ASPA = 215;
	

	public static final int SAMPLEID_LENGTH = 8;
	
	public static final int MAX_LENGTH_FRAME_TEXT = 253;
	
	public static final char LEFT_ALIGNED = 'l';
	public static final char RIGHT_ALIGNED = 'r';
	
	
	

}

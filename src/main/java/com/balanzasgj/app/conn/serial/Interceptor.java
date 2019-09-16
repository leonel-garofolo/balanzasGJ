package com.balanzasgj.app.conn.serial;

public interface Interceptor {
	
	public boolean isConnected();
	public void connect() throws ConnectionChannelException ;
	public void disconnect() throws ConnectionChannelException;
	
	/**Recibe la info desde la pantalla en un ObjCol, 
	 * envía al analizador y devuelve la respuesta a la pantalla en un ObjCol
	 * 
	 * @param message
	 * @return
	 * @throws AnalyzerException
	 */
	public default String sendAndReceive(String message) throws Exception {
		throw new Exception("Analyzer.sendAndReceive(ObjCol) not implemented.");
	}
}

/**
 * 
 */
package com.frasch.fraschchat.server;

import java.net.InetAddress;

/**
 * @author Frank Schumann
 * @version v0.1.0-alpha
 * @since v0.1.0-alpha
 *
 */
public class ServerClient {
	
	public String name;
	public InetAddress inAddr;
	public int port;
	
	private final int ID;
	
	public int attempt = 0;

	public ServerClient(String name, InetAddress inAddr, int port, int ID) {
		this.name = name;
		this.inAddr = inAddr;
		this.port = port;
		this.ID = ID;
	}

	/**
	 * @return the iD
	 */
	public int getID() {
		return ID;
	}

}

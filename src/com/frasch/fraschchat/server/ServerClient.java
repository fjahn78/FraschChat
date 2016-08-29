/**
 * 
 */
package com.frasch.fraschchat.server;

import java.net.InetAddress;
import java.util.UUID;

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
	
	private final UUID ID;
	
	public int attempt = 0;

	public ServerClient(String name, InetAddress inAddr, int port, UUID ID) {
		this.name = name;
		this.inAddr = inAddr;
		this.port = port;
		this.ID = ID;
	}

	/**
	 * @return the iD
	 */
	public UUID getID() {
		return ID;
	}

}

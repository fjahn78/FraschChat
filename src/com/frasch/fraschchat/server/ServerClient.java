/**
 *
 */
package com.frasch.fraschchat.server;

import java.net.InetAddress;
import java.util.UUID;

// TODO: Auto-generated Javadoc
/**
 * The Class ServerClient.
 *
 * @author Frank Schumann
 * @version v0.1.0-alpha
 * @since v0.1.0-alpha
 */
public class ServerClient {

	/** The name. */
	public String name;

	/** The in addr. */
	public InetAddress inAddr;

	/** The port. */
	public int port;

	/** The id. */
	private final UUID ID;

	/** The attempt. */
	public int attempt = 0;

	/**
	 * Instantiates a new server client.
	 *
	 * @param name
	 *            the user name
	 * @param inAddr
	 *            the inet address
	 * @param port
	 *            the port
	 * @param ID
	 *            the id
	 */
	public ServerClient(String name, InetAddress inAddr, int port, UUID ID) {
		this.name = name;
		this.inAddr = inAddr;
		this.port = port;
		this.ID = ID;
	}

	/**
	 * Gets the id.
	 *
	 * @return the iD
	 */
	public UUID getID() {
		return ID;
	}

}

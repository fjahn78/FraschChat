/*
 *
 */
package com.frasch.fraschchat.server;

// TODO: Auto-generated Javadoc
/**
 * The Class ServerMain.
 *
 * @author FraSch
 * @version v0.1.0-alpha
 * @since v0.1.0-alpha
 */
public class ServerMain {

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		int port;
		if (args.length != 1) {
			usage();
			return;
		}
		port = Integer.parseInt(args[0]);
		new ServerMain(port);

	}

	/**
	 * Usage.
	 */
	private static void usage() {
		System.out.println("Usage: java -jar FraschChatServer.jar [port]");
	}

	/** The port. */
	private int port;

	/** The server. */
	private Server server;

	/**
	 * Instantiates a new server main.
	 *
	 * @param port
	 *            the port
	 */
	public ServerMain(int port) {
		this.port = port;
		server = new Server(port);
	}

}

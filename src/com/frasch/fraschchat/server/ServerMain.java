/**
 * The ServerMain class serves as command line front end
 * 
 */
package com.frasch.fraschchat.server;

/**
 * @author FraSch
 *
 */
public class ServerMain {
	private int port;
	
	public ServerMain(int port){
		this.port = port;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int port;
		if (args.length != 1){
			usage();
			return;
		}
		port = Integer.parseInt(args[0]);
		new ServerMain(port);

	}

	/**
	 * 
	 */
	private static void usage() {
		System.out.println("Usage: java -jar FraschChatServer.jar [port]");
	}

}

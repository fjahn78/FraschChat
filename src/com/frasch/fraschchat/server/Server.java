/**
 * The class Server manages all client connections and chat transmission
 * 
 */
package com.frasch.fraschchat.server;

import java.net.DatagramSocket;
import java.net.SocketException;

public class Server implements Runnable {

	private DatagramSocket s;
	private int port;
	private boolean isRunning = false;
	private Thread run, manage, send, receive;
	
	/**
	 * @param port
	 */
	public Server(int port) {
		this.port = port;
		try {
			this.s = new DatagramSocket(port);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		run = new Thread(this, "Server");
	}


	@Override
	public void run() {
		isRunning = true;
		manageClients();
		receive();
	}


	private void receive() {
		receive = new Thread("Receive"){
			public void run(){
				while (isRunning){
					// TODO: Listening code
				}
			}
		};
		receive.start();
	}


	private void manageClients() {
		manage = new Thread("Manage"){
			public void run(){
				while (isRunning){
					// TODO: Client Management code
				}
			}
		};
		manage.start();
	}

}

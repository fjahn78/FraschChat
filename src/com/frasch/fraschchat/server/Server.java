/**
 * The class Server manages all client connections and chat transmission
 * 
 */
package com.frasch.fraschchat.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

public class Server implements Runnable {
	
	private List<ServerClient> clients = new ArrayList<ServerClient>();

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
			e.printStackTrace();
		}
		run = new Thread(this, "Server");
		run.start();
	}


	@Override
	public void run() {
		isRunning = true;
		System.out.println("Server running at port: " + port);
		manageClients();
		receive();
	}


	private void receive() {
		receive = new Thread("Receive"){
			public void run(){
				while (isRunning){
					// TODO: Listening code
					byte[] data = new byte[1024];
					DatagramPacket p = new DatagramPacket(data, data.length);
					try {
						s.receive(p);
					} catch (IOException e) {
						e.printStackTrace();
					}
					doProcess(p);
				}
			}
		};
		receive.start();
	}


	protected void doProcess(DatagramPacket p) {
		// TODO Auto-generated method stub
		String str = new String(p.getData());
//		String type = str.substring(0, 2);
//		System.out.println(type);
		switch (str.substring(0, 3)) {
		case "/c/":
			ServerClient client = new ServerClient(str.substring(3, str.length()), p.getAddress(), p.getPort(), 50);
			clients.add(client);
			System.out.println("User " + client.name.trim() + " connected from " + client.inAddr.toString() + ":" + client.port );
			break;
		case "/m/":
			System.out.println(str.substring(3, str.length()));
			break;
		default:
			break;
		}
		
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

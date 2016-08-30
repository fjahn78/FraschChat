/*
 *
 */
package com.frasch.fraschchat.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// TODO: Auto-generated Javadoc
/**
 * The Class Server.
 *
 * @author Frank Schumann
 * @version v0.2.0-alpha
 * @since v0.1.0-alpha
 */
public class Server implements Runnable {

	/** The clients. */
	private List<ServerClient> clients = new ArrayList<ServerClient>();

	/** The socket. */
	private DatagramSocket s;

	/** The port. */
	private int port;

	/** The is running. */
	private boolean isRunning = false;

	/** The receive. */
	private Thread run, manage, send, receive;

	/**
	 * Instantiates a new server.
	 *
	 * @param port
	 *            the port
	 */
	public Server(int port) {
		this.port = port;
		try {
			s = new DatagramSocket(port);
		} catch (SocketException e) {
			e.printStackTrace();
		}
		run = new Thread(this, "Server");
		run.start();
	}

	/**
	 * Do process.
	 *
	 * @param p
	 *            the packet
	 */
	protected void doProcess(DatagramPacket p) {
		// TODO Auto-generated method stub
		String str = new String(p.getData());
		// String type = str.substring(0, 2);
		// System.out.println(type);
		switch (str.substring(0, 3)) {
		case "/c/":
			UUID uuid = UUID.randomUUID();
			ServerClient client = new ServerClient(str.substring(3, str.length()), p.getAddress(), p.getPort(), uuid);
			clients.add(client);
			System.out.println("User " + client.name.trim() + " connected from " + client.inAddr.toString().substring(1)
					+ ":" + client.port);
			send(("/c/" + uuid.toString()).getBytes(), client.inAddr, client.port);
			// int id = new SecureRandom().nextInt();
			break;
		case "/m/":
			sendToAll(str);
			break;
		default:
			break;
		}

	}

	/**
	 * Manage clients.
	 */
	private void manageClients() {
		manage = new Thread("Manage") {
			@Override
			public void run() {
				while (isRunning) {
					// TODO: Client Management code
				}
			}
		};
		manage.start();
	}

	/**
	 * Receive.
	 */
	private void receive() {
		receive = new Thread("Receive") {
			@Override
			public void run() {
				while (isRunning) {
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

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		isRunning = true;
		System.out.println("Server running at port: " + port);
		manageClients();
		receive();
	}

	/**
	 * Send.
	 *
	 * @param data
	 *            the data
	 * @param inAddr
	 *            the inet address
	 * @param port
	 *            the port
	 */
	private void send(final byte[] data, final InetAddress inAddr, final int port) {
		// TODO Auto-generated method stub
		send = new Thread("Send") {

			/*
			 * (non-Javadoc)
			 *
			 * @see java.lang.Thread#run()
			 */
			@Override
			public void run() {
				DatagramPacket p = new DatagramPacket(data, data.length, inAddr, port);
				try {
					s.send(p);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		send.start();
	}

	/**
	 * Send to all.
	 *
	 * @param message
	 *            the message
	 */
	private void sendToAll(String message) {
		// TODO Auto-generated method stub
		clients.forEach((c) -> send(message.getBytes(), c.inAddr, c.port));

	}

}

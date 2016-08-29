/**
 * 
 */
package com.frasch.fraschchat.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

// TODO: Auto-generated Javadoc
/**
 * The Class Client.
 *
 * @author FraSch
 * @version v0.1.0-alpha
 * @since 0.0.2_pre-alpha
 * @serial
 */
public class Client {

	/** The address. */
	private String name, address;

	/** The port. */
	private int port;

	/** The socket. */
	private DatagramSocket socket;

	/** The inet addr. */
	private InetAddress inetAddr;

	/** The send. */
	private Thread send;

	/**
	 * Instantiates a new client.
	 *
	 * @param name
	 *            the name
	 * @param address
	 *            the address
	 * @param port
	 *            the port
	 */
	public Client(String name, String address, int port) {
		this.name = name;
		this.address = address;
		this.port = port;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the port.
	 *
	 * @return the port
	 */
	public int getPort() {
		return port;
	}

	/**
	 * Gets the inet addr.
	 *
	 * @return the inet addr
	 */
	public InetAddress getInetAddr() {
		return inetAddr;
	}

	/**
	 * Sets the name.
	 *
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the port.
	 *
	 * @param port
	 *            the new port
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * Sets the inet addr.
	 *
	 * @param inetAddr
	 *            the new inet addr
	 */
	public void setInetAddr(InetAddress inetAddr) {
		this.inetAddr = inetAddr;
	}

	/**
	 * Checks if is connected.
	 *
	 * @param address
	 *            the address
	 * @return true, if is connected
	 */
	public boolean isConnected(String address) {
		try {
			socket = new DatagramSocket();
			inetAddr = InetAddress.getByName(address);
		} catch (UnknownHostException | SocketException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	/**
	 * The method receive listens for new data being sent by a server.
	 *
	 * @return the string
	 */
	public String receive() {
		byte[] data = new byte[1024];
		DatagramPacket packet = new DatagramPacket(data, data.length);
		try {
			socket.receive(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String message = new String(packet.getData());
		return message;
	}

	/**
	 * Send message to a server.
	 *
	 * @param data
	 *            the data
	 */
	// @SuppressWarnings("unused")
	void send(final byte[] data) {
		send = new Thread("Send") {

			/*
			 * (non-Javadoc)
			 * 
			 * @see java.lang.Thread#run()
			 */
			@Override
			public void run() {
				DatagramPacket p = new DatagramPacket(data, data.length, inetAddr, port);
				try {
					socket.send(p);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		send.start();
	}
}

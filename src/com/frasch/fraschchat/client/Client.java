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

/**
 * @author		FraSch
 * @version 	v0.1.0-alpha
 * @since 		0.0.2_pre-alpha
 * @serial
 *
 */
public class Client {
	private String name, address;
	private int port;

	private DatagramSocket socket;
	private InetAddress inetAddr;

	private Thread send;


	public Client(String name, String address, int port){
		this.name = name;
		this.address = address;
		this.port = port;
	}

	public String getName() {
		return name;
	}

	public int getPort() {
		return port;
	}

	public InetAddress getInetAddr() {
		return inetAddr;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void setInetAddr(InetAddress inetAddr) {
		this.inetAddr = inetAddr;
	}

	public boolean isConnected(String address){
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
	 * The method receive listens for new data being sent by a server
	 *
	 * @return the string
	 */
	public String receive(){
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
	 * @param data the data
	 */
	//	@SuppressWarnings("unused")
	void send(final byte[] data){
		send = new Thread("Send"){

			/* (non-Javadoc)
			 * @see java.lang.Thread#run()
			 */
			@Override
			public void run(){
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

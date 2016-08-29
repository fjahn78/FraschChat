/**
 * 
 */
package com.frasch.fraschchat.client;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * @author		FraSch
 * @version 	0.0.2_pre-alpha
 * @since 		0.0.2_pre-alpha
 * @serial
 *
 */
public class Client {
	private String name, address;
	private int port;
	
	private DatagramSocket socket;
	private InetAddress inetAddr;
	
	public boolean isConnected(String address, int port){
		try {
			socket = new DatagramSocket();
			inetAddr = InetAddress.getByName(address);
		} catch (UnknownHostException | SocketException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	public Client(String name, String address, int port){
		this.name = name;
		this.address = address;
		this.port = port;
	}
}

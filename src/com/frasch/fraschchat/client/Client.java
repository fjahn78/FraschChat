/**
 * 
 */
package com.frasch.fraschchat.client;

/**
 * @author FraSch
 * @version
 * @since
 * @serial
 *
 */
public class Client {
	private String name, address;
	private int port;
	
	public boolean isConnected(String address, int port){
		return false;
		
	}
	public Client(String name, String address, int port){
		this.name = name;
		this.address = address;
		this.port = port;
	}
}

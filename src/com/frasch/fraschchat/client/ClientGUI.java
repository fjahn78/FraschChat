package com.frasch.fraschchat.client;

import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ClientGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	/** The m name. */
	private String name, address;
	private int port;

	/**
	 * Create the frame.
	 * @param port listen port of the server
	 * @param address server address
	 * @param name user name
	 */
	public ClientGUI(String name, String address, int port) {
		this.name = name;
		this.address = address;
		this.port = port;
		
		createWindow();
	}

	/**
	 * Creates the chat window
	 */
	private void createWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWeights = new double[]{};
		gbl_contentPane.rowWeights = new double[]{};
		contentPane.setLayout(gbl_contentPane);
		this.setVisible(true);
	}

}

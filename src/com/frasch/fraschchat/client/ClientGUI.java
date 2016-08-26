package com.frasch.fraschchat.client;

import java.awt.Color;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.JTextArea;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClientGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private String name, address;
	private int port;
	private JTextArea textArea = new JTextArea();
	private JTextField txtMessage;

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
		console("Successfully connected to " + this.address + ":" + this.port + "; user: " + this.name);
	}

	/**
	 * Creates the chat window
	 */
	private void createWindow() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		setTitle("FraschChat");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0};
		gbl_contentPane.rowHeights = new int[]{0};
		gbl_contentPane.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		textArea.setEditable(false);
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.gridwidth = 2;
		gbc_textArea.insets = new Insets(0, 0, 5, 0);
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 0;
		gbc_textArea.gridy = 0;
		contentPane.add(textArea, gbc_textArea);
		
		txtMessage = new JTextField();
		GridBagConstraints gbc_txtMessage = new GridBagConstraints();
		gbc_txtMessage.insets = new Insets(0, 0, 0, 5);
		gbc_txtMessage.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMessage.gridx = 0;
		gbc_txtMessage.gridy = 1;
		contentPane.add(txtMessage, gbc_txtMessage);
		txtMessage.setColumns(10);
		
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				doEcho();
			}

		});
		GridBagConstraints gbc_btnSend = new GridBagConstraints();
		gbc_btnSend.gridx = 1;
		gbc_btnSend.gridy = 1;
		contentPane.add(btnSend, gbc_btnSend);
		
		this.setVisible(true);
		txtMessage.requestFocusInWindow();
	}

	/**
	 * print the message into the Console text area.
	 *
	 * @param message the message
	 */
	public void console(String message) {
		textArea.append(message+"\n\r");
	}

	/**
	 * Send the input to the text area and clear the input field.
	 */
	private void doEcho() {
		String input = txtMessage.getText();
		if (!input.equals("")) 
			console(name + ": " + input);
		txtMessage.setText("");
		txtMessage.requestFocusInWindow();
	}
}

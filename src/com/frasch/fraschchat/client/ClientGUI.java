/*
 * 
 */
package com.frasch.fraschchat.client;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.text.DefaultCaret;

/**
 * The Class ClientGUI.
 * 
 * @author		FraSch
 * @version 	v0.1.0-alpha
 * @since		0.0.1
 */
public class ClientGUI extends JFrame {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The content pane. */
	private JPanel contentPane;
	
	/** The address. */
	private String name, address;
	
	/** The port. */
	private int port;
	
	/** The text area. */
	private JTextArea textArea = new JTextArea();
	
	/** The txt message. */
	private JTextField txtMessage;
	
	/** The caret. */
	private DefaultCaret caret;
	
	private Client c;

	/**
	 * Create the frame.
	 *
	 * @param name user name
	 * @param address server address
	 * @param port listen port of the server
	 */
	public ClientGUI(String name, String address, int port) {
		this.name = name;
		this.address = address;
		this.port = port;
		
		c = new Client(name, address, port);
		
		createWindow();
		if (!c.isConnected(this.address)){
			System.err.println("Connection to " + this.address + ":" + port + " failed!");
			console("Connection to " + this.address + ":" + port + " failed!");
		} else {
			console("Successfully connected to " + this.address + ":" + this.port + "; user: " + this.name);
			String connection = "/c/" + this.name;
			doSend(connection);
		}
	}

	/**
	 * Creates the chat window.
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
		JScrollPane scroll = new JScrollPane(textArea);
		caret = (DefaultCaret)textArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		GridBagConstraints gbc_scroll = new GridBagConstraints();
		gbc_scroll.gridwidth = 2;
		gbc_scroll.insets = new Insets(0, 0, 5, 0);
		gbc_scroll.fill = GridBagConstraints.BOTH;
		gbc_scroll.gridx = 0;
		gbc_scroll.gridy = 0;
		contentPane.add(scroll, gbc_scroll);
		
		txtMessage = new JTextField();
		txtMessage.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER){
					doSend("/m/" + txtMessage.getText());
					doEcho(name + ": " + txtMessage.getText());
				}
			}
		});
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
				doSend("/m/" + txtMessage.getText());
				doEcho(name + ": " + txtMessage.getText());
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
		textArea.setCaretPosition(textArea.getDocument().getLength());
	}

	/**
	 * Send the input to the text area and clear the input field.
	 *
	 * @param input the message
	 */
	private void doSend(String input) {
		
		if (input.equals("")) return;
		c.send(input.getBytes());
		System.out.println(input);
//		doEcho(name + ": " + input);
		
	}

	/**
	 * @param input
	 */
	private void doEcho(String input) {
		console(input);
		txtMessage.setText("");
		txtMessage.requestFocusInWindow();
	}
}

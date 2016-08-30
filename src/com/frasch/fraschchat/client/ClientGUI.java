/*
 *
 */
package com.frasch.fraschchat.client;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.UUID;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.text.DefaultCaret;

// TODO: Auto-generated Javadoc
/**
 * The Class ClientGUI.
 *
 * @author FraSch
 * @version v0.2.0-alpha
 * @since 0.0.1
 */
public class ClientGUI extends JFrame implements Runnable {

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

	/** The c. */
	private Client c;

	/** The listen. */
	private Thread run, listen;

	/**
	 * Create the frame.
	 *
	 * @param name
	 *            user name
	 * @param address
	 *            server address
	 * @param port
	 *            listen port of the server
	 */
	public ClientGUI(String name, String address, int port) {
		c = new Client(name, address, port);

		createWindow();
		if (!c.doConnect(address)) {
			System.err.println("Connection to " + address + ":" + port + " failed!");
			console("Connection to " + address + ":" + port + " failed!");
		} else {
			// console("Successfully connected to " + address + ":" + port + ";
			// user: " + name);
			String connection = "/c/" + name;
			doSend(connection);
			run = new Thread(this, "Running");
			run.start();
		}
	}

	/**
	 * print the message into the Console text area.
	 *
	 * @param message
	 *            the message
	 */
	public void console(String message) {
		textArea.append(message + "\n\r");
		textArea.setCaretPosition(textArea.getDocument().getLength());
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
		gbl_contentPane.columnWidths = new int[] { 0 };
		gbl_contentPane.rowHeights = new int[] { 0 };
		gbl_contentPane.columnWeights = new double[] { Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		textArea.setEditable(false);
		JScrollPane scroll = new JScrollPane(textArea);
		caret = (DefaultCaret) textArea.getCaret();
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
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					String msg = name + ": " + txtMessage.getText();
					doSend("/m/" + msg);
					doEcho(msg);
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
		btnSend.addActionListener(arg0 -> {
			String msg = name + ": " + txtMessage.getText();
			doSend("/m/" + msg);
			doEcho(msg);
		});
		GridBagConstraints gbc_btnSend = new GridBagConstraints();
		gbc_btnSend.gridx = 1;
		gbc_btnSend.gridy = 1;
		contentPane.add(btnSend, gbc_btnSend);

		setVisible(true);
		txtMessage.requestFocusInWindow();
	}

	/**
	 * Do echo.
	 *
	 * @param input
	 *            The String to be echoed
	 */
	private void doEcho(String input) {
		console(input);
		txtMessage.setText("");
		txtMessage.requestFocusInWindow();
	}

	/**
	 * Do listen.
	 */
	public void doListen() {
		listen = new Thread("Listen") {
			@Override
			public void run() {
				while (c.isConnected) {
					String message = c.receive().trim();
					if (message.startsWith("/c/"))
						c.setUuid(UUID.fromString(message.substring(3)));
					console("Successfully connected to " + c.getInetAddr().getHostName() + ":" + c.getPort()
							+ "\n\rUser: " + c.getName() + "\n\rID: " + c.getUuid());
				}
			}
		};
		listen.start();
	}

	/**
	 * Send the input to the text area and clear the input field.
	 *
	 * @param input
	 *            the message
	 */
	private void doSend(String input) {

		if (input.equals(""))
			return;
		c.send(input.getBytes());
		System.out.println(input);
		// doEcho(name + ": " + input);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		doListen();
	}
}

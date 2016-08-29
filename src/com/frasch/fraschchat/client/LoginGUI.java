/*
 * 
 */
package com.frasch.fraschchat.client;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

// TODO: Auto-generated Javadoc
/**
 * The Class LoginGUI.
 * 
 * @author Frank Schumann
 * @version v0.1.0-alpha
 * @since 0.0.1
 */
public class LoginGUI extends JFrame {

	/**
	 * The Constant serialVersionUID.
	 *
	 * Login Window
	 * 
	 * @author FraSch
	 * @version v0.1.1-alpha
	 * @since 0.0.1
	 */
	private static final long serialVersionUID = 1L;

	/** The content pane. */
	private JPanel contentPane;

	/** The txt name. */
	private JTextField txtName;

	/** The txt server. */
	private JTextField txtServer;

	/** The txt port. */
	private JTextField txtPort;

	/**
	 * Instantiates a new login GUI.
	 *
	 * @author FraSch
	 * 
	 *         Create the frame.
	 */
	public LoginGUI() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		setResizable(false);
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(217, 259);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtName = new JTextField();
		txtName.setBounds(10, 41, 182, 20);
		contentPane.add(txtName);
		txtName.setColumns(10);

		txtServer = new JTextField();
		txtServer.setBounds(10, 98, 126, 20);
		contentPane.add(txtServer);
		txtServer.setColumns(10);

		txtPort = new JTextField();
		txtPort.setBounds(146, 98, 46, 20);
		contentPane.add(txtPort);
		txtPort.setColumns(10);

		JLabel lblName = new JLabel("Name");
		lblName.setBounds(10, 11, 46, 14);
		contentPane.add(lblName);

		JLabel lblServeraddress = new JLabel("Server address");
		lblServeraddress.setBounds(10, 72, 89, 14);
		contentPane.add(lblServeraddress);

		JLabel lblPort = new JLabel("Port");
		lblPort.setBounds(146, 72, 32, 14);
		contentPane.add(lblPort);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.ActionListener#actionPerformed(java.awt.event.
			 * ActionEvent)
			 */
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// System.out.println("Button clicked.");
				String name = txtName.getText();
				String address = txtServer.getText();
				int port = Integer.parseInt(txtPort.getText());
				login(name, address, port);
			}
		});
		btnLogin.setBounds(61, 174, 89, 23);
		contentPane.add(btnLogin);
	}

	/**
	 * Calls the chat window.
	 *
	 * @author FraSch
	 * @param name
	 *            User name
	 * @param address
	 *            Chat Server Address
	 * @param port
	 *            Port
	 */
	private void login(String name, String address, int port) {
		dispose();
		System.out.println(name + "@" + address + ":" + port);
		new ClientGUI(name, address, port);
	}

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}

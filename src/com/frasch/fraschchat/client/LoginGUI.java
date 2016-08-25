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

public class LoginGUI extends JFrame {

	/**
	 * @author FraSch
	 * Login Window
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtServer;
	private JTextField txtPort;

	/**
	 * @author FraSch
	 * 
	 * Create the frame.
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
			
			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent arg0) {
//				System.out.println("Button clicked.");
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
	 * Calls the chat window
	 * @author FraSch
	 * @category GUI
	 * @param name User name
	 * @param address Chat Server Address
	 * @param port Port
	 * 
	 */
	private void login(String name, String address, int port) {
		dispose();
		System.out.println(name+"@"+address+":"+port);
		new ClientGUI(name, address, port);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
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

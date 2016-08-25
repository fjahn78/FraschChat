package com.frasch.fraschchat.client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class login extends JFrame {

	/**
	 * Login Window
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtServer;
	private JTextField txtPort;

	/**
	 * Create the frame.
	 */
	public login() {
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
		btnLogin.setBounds(61, 174, 89, 23);
		contentPane.add(btnLogin);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}

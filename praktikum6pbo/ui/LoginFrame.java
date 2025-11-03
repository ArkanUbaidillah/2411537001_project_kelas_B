package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import error.ValidationException;
import model.User;
import service.LoginService;
import util.ValidationUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsername;
	private JTextField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 487, 304);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login Form");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNewLabel.setBounds(183, 29, 87, 40);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setBounds(108, 75, 57, 13);
		contentPane.add(lblNewLabel_1);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(108, 93, 229, 24);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password");
		lblNewLabel_1_1.setBounds(108, 127, 57, 13);
		contentPane.add(lblNewLabel_1_1);
		
		txtPassword = new JTextField();
		txtPassword.setColumns(10);
		txtPassword.setBounds(108, 142, 229, 24);
		contentPane.add(txtPassword);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    String userValue = txtUsername.getText();
			    String passValue = txtPassword.getText();
			    // Create user object
			    User user = new User(userValue, passValue);
			    try {
			        ValidationUtil.validate(user);
			        LoginService loginService = new LoginService();
			        if (loginService.authenticate(user)) {
			            System.out.println("Login successful!");
			            new MainFrame().setVisible(true);
			            dispose();
			        } 
			        else {
			            System.out.println("Invalid username or password.");
			            JOptionPane.showMessageDialog(null, "Login Gagal, Invalid username or password.");
			        }
			    } 
			    catch (ValidationException | NullPointerException exception) {
			        System.out.println("Data tidak valid : " + exception.getMessage());
			        JOptionPane.showMessageDialog(null, "Login Gagal: " + exception.getMessage());
			    } 
			    finally {
			        System.out.println("Selalu di eksekusi");
			    }
			}	
		});
		btnNewButton.setBounds(106, 189, 231, 24);
		contentPane.add(btnNewButton);
	}
}

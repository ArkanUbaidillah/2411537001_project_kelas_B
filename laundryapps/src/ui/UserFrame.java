package ui;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.UserRepo;
import model.User;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;

public class UserFrame extends JFrame {
	
	UserRepo usr = new UserRepo();
	List<User> ls;
	public String id;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtUsername;
	private JTextField txtPassword;
	private JButton btnSave;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnCancel;
	private JTable tableUsers;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserFrame frame = new UserFrame();
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
	public UserFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 570, 441);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(26, 46, 45, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(26, 96, 61, 13);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(26, 143, 45, 13);
		contentPane.add(lblPassword);
		
		txtName = new JTextField();
		txtName.setBounds(129, 43, 224, 19);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		txtUsername = new JTextField();
		txtUsername.setColumns(10);
		txtUsername.setBounds(129, 93, 224, 19);
		contentPane.add(txtUsername);
		
		txtPassword = new JTextField();
		txtPassword.setColumns(10);
		txtPassword.setBounds(129, 140, 224, 19);
		contentPane.add(txtPassword);
		
		btnSave = new JButton("Save");
		btnSave.setBounds(129, 182, 72, 21);
		contentPane.add(btnSave);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setBounds(199, 182, 72, 21);
		contentPane.add(btnUpdate);
		
		btnDelete = new JButton("Delete");
		btnDelete.setBounds(268, 182, 72, 21);
		contentPane.add(btnDelete);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(339, 182, 72, 21);
		contentPane.add(btnCancel);
		
		tableUsers = new JTable();
		tableUsers.setBounds(10, 229, 536, 165);
		contentPane.add(tableUsers);
	}
	public void reset() {
		txtName.setText(" ");
		txtUsername.setText(" ");
		txtPassword.setText(" ");
	}
}

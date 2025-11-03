package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
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
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 495, 318);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Masukkan data");
		lblNewLabel.setBounds(73, 66, 84, 13);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(73, 92, 210, 30);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Simpan");
		btnNewButton.setBounds(300, 92, 107, 30);
		contentPane.add(btnNewButton);
		
		JLabel lblData = new JLabel("Data :");
		lblData.setBounds(73, 146, 84, 13);
		contentPane.add(lblData);
		
		JLabel lblCheckArrayKe = new JLabel("Check array ke-");
		lblCheckArrayKe.setBounds(73, 192, 84, 13);
		contentPane.add(lblCheckArrayKe);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(154, 189, 124, 30);
		contentPane.add(textField_1);
		
		JButton btnCheck = new JButton("Check");
		btnCheck.setBounds(300, 188, 107, 30);
		contentPane.add(btnCheck);
	}

}

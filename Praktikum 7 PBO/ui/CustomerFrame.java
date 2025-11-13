package ui;

import DAO.CustomerRepo;
import model.Costumer;
import model.CustomerBuilder;

import java.util.List;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomerFrame extends JFrame {    
    CustomerRepo repo = new CustomerRepo();
    List<Costumer> list = new ArrayList<>();
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTable table;
    
    public String id;

    public CustomerFrame() {
        setTitle("PELANGGAN");
        setSize(650, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Nama");
        lblNewLabel.setBounds(77, 127, 45, 13);
        getContentPane().add(lblNewLabel);
        
        textField = new JTextField();
        textField.setBounds(152, 124, 377, 19);
        getContentPane().add(textField);
        textField.setColumns(10);
        
        JLabel lblAlamat = new JLabel("Alamat");
        lblAlamat.setBounds(77, 150, 45, 13);
        getContentPane().add(lblAlamat);
        
        JLabel lblNoHp = new JLabel("No. HP");
        lblNoHp.setBounds(77, 173, 45, 13);
        getContentPane().add(lblNoHp);
        
        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(77, 196, 45, 13);
        getContentPane().add(lblEmail);
        
        // input fields
        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(152, 147, 377, 19);
        getContentPane().add(textField_1);
        
        textField_3 = new JTextField();
        textField_3.setColumns(10);
        textField_3.setBounds(152, 170, 377, 19);
        getContentPane().add(textField_3);
        
        textField_2 = new JTextField();
        textField_2.setColumns(10);
        textField_2.setBounds(152, 190, 377, 19);
        getContentPane().add(textField_2);
        initComponents();
        loadData();
    }

    private void initComponents() {
        JScrollPane scrollPane = new JScrollPane();
        // place the table roughly below the input fields
        scrollPane.setBounds(77, 260, 452, 180);
        getContentPane().add(scrollPane);
        
        table = new JTable();
        scrollPane.setViewportView(table);

        // attach mouse listener to fill form when a row is clicked
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                int row = table.rowAtPoint(evt.getPoint());
                if (row >= 0) {
                    id = String.valueOf(table.getValueAt(row, 0));
                    textField.setText(String.valueOf(table.getValueAt(row, 1)));
                    textField_2.setText(String.valueOf(table.getValueAt(row, 2)));
                    textField_1.setText(String.valueOf(table.getValueAt(row, 3)));
                    textField_3.setText(String.valueOf(table.getValueAt(row, 4)));
                }
            }
        });
        
        // add Save and Refresh buttons between the form and the table
        JButton btnSave = new JButton("Simpan");
        btnSave.setBounds(252, 210, 100, 25);
        btnSave.addActionListener(e -> saveCustomer());
        getContentPane().add(btnSave);

        JButton btnUpdate = new JButton("Ubah");
        btnUpdate.setBounds(357, 210, 100, 25);
        btnUpdate.addActionListener(e -> updateCustomer());
        getContentPane().add(btnUpdate);

        JButton btnDelete = new JButton("Hapus");
        btnDelete.setBounds(462, 210, 81, 25);
        btnDelete.addActionListener(e -> deleteCustomer());
        getContentPane().add(btnDelete);

        JButton btnRefresh = new JButton("Batal");
        btnRefresh.setBounds(551, 210, 76, 25);
        btnRefresh.addActionListener(e -> {
            loadData();
            resetForm();
        });
        getContentPane().add(btnRefresh);
    }

    private void saveCustomer() {
        // basic validation
        if (textField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama tidak boleh kosong", "Validasi", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Costumer c = new CustomerBuilder()
                .setId(String.valueOf(System.currentTimeMillis()))
                .setNama(textField.getText())
                .setEmail(textField_2.getText())
                .setAlamat(textField_1.getText())
                .setNoHp(textField_3.getText())
                .build();

        repo.save(c);
        loadData();
        resetForm();
        JOptionPane.showMessageDialog(this, "Data tersimpan");
    }

    private void loadData() {
        list = repo.show();
        if (list == null) {
            list = new ArrayList<>();
        }
        String[] cols = {"ID", "Nama", "Email", "Alamat", "No. HP"};
        DefaultTableModel model = new DefaultTableModel(cols, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        for (Costumer cs : list) {
            model.addRow(new Object[]{cs.getId(), cs.getNama(), cs.getEmail(), cs.getAlamat(), cs.getNoHp()});
        }
        table.setModel(model);
    }

    private void resetForm() {
        textField.setText("");
        textField_1.setText("");
        textField_2.setText("");
        textField_3.setText("");
        id = null;
    }
    
    private void updateCustomer() {
        if (id == null || id.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Pilih data pada tabel untuk diubah", "Informasi", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (textField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama tidak boleh kosong", "Validasi", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Costumer c = new CustomerBuilder()
                .setId(id)
                .setNama(textField.getText())
                .setEmail(textField_2.getText())
                .setAlamat(textField_1.getText())
                .setNoHp(textField_3.getText())
                .build();
        repo.update(c);
        loadData();
        resetForm();
        JOptionPane.showMessageDialog(this, "Data berhasil diubah");
    }

    private void deleteCustomer() {
        if (id == null || id.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Pilih data pada tabel untuk dihapus", "Informasi", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        int ok = JOptionPane.showConfirmDialog(this, "Yakin ingin menghapus data ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (ok == JOptionPane.YES_OPTION) {
            repo.delete(id);
            loadData();
            resetForm();
            JOptionPane.showMessageDialog(this, "Data berhasil dihapus");
        }
    }
    
}
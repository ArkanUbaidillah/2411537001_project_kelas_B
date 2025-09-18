package ui;

import DAO.PelangganRepo;
import DAO.PelangganDAO;
import model.Pelanggan;
import table.TablePelanggan;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;
import java.awt.Color;

public class PelangganFrame extends JFrame {
    private JTextField txtNama, txtAlamat, txtNoTelp;
    private JButton btnSave, btnUpdate, btnDelete, btnCancel;
    private JTable tablePelanggan;
    private int selectedId = -1;

    private PelangganDAO pelangganDAO = new PelangganRepo();

    public PelangganFrame() {
        setTitle("CRUD Pelanggan");
        setSize(700, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel lblNama = new JLabel("Nama");
        lblNama.setBounds(20, 20, 100, 25);
        getContentPane().add(lblNama);

        txtNama = new JTextField();
        txtNama.setBounds(130, 20, 200, 25);
        getContentPane().add(txtNama);

        JLabel lblAlamat = new JLabel("Alamat");
        lblAlamat.setBounds(20, 60, 100, 25);
        getContentPane().add(lblAlamat);

        txtAlamat = new JTextField();
        txtAlamat.setBounds(130, 60, 200, 25);
        getContentPane().add(txtAlamat);

        JLabel lblNoTelp = new JLabel("No. Telp");
        lblNoTelp.setBounds(20, 100, 100, 25);
        getContentPane().add(lblNoTelp);

        txtNoTelp = new JTextField();
        txtNoTelp.setBounds(130, 100, 200, 25);
        getContentPane().add(txtNoTelp);

        btnSave = new JButton("Save");
        btnSave.setBackground(new Color(0, 255, 0));
        btnSave.setBounds(20, 140, 80, 25);
        getContentPane().add(btnSave);

        btnUpdate = new JButton("Update");
        btnUpdate.setBackground(new Color(0, 0, 255));
        btnUpdate.setBounds(110, 140, 80, 25);
        getContentPane().add(btnUpdate);

        btnDelete = new JButton("Delete");
        btnDelete.setBackground(new Color(255, 0, 0));
        btnDelete.setBounds(200, 140, 80, 25);
        getContentPane().add(btnDelete);

        btnCancel = new JButton("Cancel");
        btnCancel.setBackground(new Color(255, 255, 0));
        btnCancel.setBounds(290, 140, 80, 25);
        getContentPane().add(btnCancel);

        tablePelanggan = new JTable();
        JScrollPane sp = new JScrollPane(tablePelanggan);
        sp.setBounds(20, 190, 640, 200);
        getContentPane().add(sp);

        loadTable();

        // Save
        btnSave.addActionListener(e -> {
            Pelanggan p = new Pelanggan();
            p.setNama(txtNama.getText());
            p.setAlamat(txtAlamat.getText());
            p.setNoTelp(txtNoTelp.getText());
            pelangganDAO.save(p);
            reset();
            loadTable();
        });

        // Update
        btnUpdate.addActionListener(e -> {
            if (selectedId != -1) {
                Pelanggan p = new Pelanggan();
                p.setId(selectedId);
                p.setNama(txtNama.getText());
                p.setAlamat(txtAlamat.getText());
                p.setNoTelp(txtNoTelp.getText());
                pelangganDAO.update(p);
                reset();
                loadTable();
            }
        });

        // Delete
        btnDelete.addActionListener(e -> {
            if (selectedId != -1) {
                pelangganDAO.delete(selectedId);
                reset();
                loadTable();
            }
        });

        // Cancel
        btnCancel.addActionListener(e -> reset());

        // Klik tabel -> isi form
        tablePelanggan.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = tablePelanggan.getSelectedRow();
                selectedId = (int) tablePelanggan.getValueAt(row, 0);
                txtNama.setText(tablePelanggan.getValueAt(row, 1).toString());
                txtAlamat.setText(tablePelanggan.getValueAt(row, 2).toString());
                txtNoTelp.setText(tablePelanggan.getValueAt(row, 3).toString());
            }
        });
    }

    private void loadTable() {
        List<Pelanggan> list = pelangganDAO.show();
        TablePelanggan tbl = new TablePelanggan(list);
        tablePelanggan.setModel(tbl);
    }

    private void reset() {
        txtNama.setText("");
        txtAlamat.setText("");
        txtNoTelp.setText("");
        selectedId = -1;
    }

    public static void main(String[] args) {
        new PelangganFrame().setVisible(true);
    }
}

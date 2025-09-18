package ui;

import DAO.LayananRepo;
import DAO.LayananDAO;
import model.Layanan;
import table.TableLayanan;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;
import java.awt.Color;

public class LayananFrame extends JFrame {
    private JTextField txtNamaLayanan, txtHarga;
    private JButton btnSave, btnUpdate, btnDelete, btnCancel;
    private JTable tableLayanan;
    private int selectedId = -1;

    private LayananDAO layananDAO = new LayananRepo();

    public LayananFrame() {
        setTitle("CRUD Layanan");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel lblNama = new JLabel("Nama Layanan");
        lblNama.setBounds(20, 20, 100, 25);
        getContentPane().add(lblNama);

        txtNamaLayanan = new JTextField();
        txtNamaLayanan.setBounds(130, 20, 200, 25);
        getContentPane().add(txtNamaLayanan);

        JLabel lblHarga = new JLabel("Harga");
        lblHarga.setBounds(20, 60, 100, 25);
        getContentPane().add(lblHarga);

        txtHarga = new JTextField();
        txtHarga.setBounds(130, 60, 200, 25);
        getContentPane().add(txtHarga);

        btnSave = new JButton("Save");
        btnSave.setBackground(new Color(0, 255, 64));
        btnSave.setBounds(20, 100, 80, 25);
        getContentPane().add(btnSave);

        btnUpdate = new JButton("Update");
        btnUpdate.setBackground(new Color(0, 0, 255));
        btnUpdate.setBounds(110, 100, 80, 25);
        getContentPane().add(btnUpdate);

        btnDelete = new JButton("Delete");
        btnDelete.setBackground(new Color(255, 0, 0));
        btnDelete.setBounds(200, 100, 80, 25);
        getContentPane().add(btnDelete);

        btnCancel = new JButton("Cancel");
        btnCancel.setBackground(new Color(255, 255, 0));
        btnCancel.setBounds(290, 100, 80, 25);
        getContentPane().add(btnCancel);

        tableLayanan = new JTable();
        JScrollPane sp = new JScrollPane(tableLayanan);
        sp.setBounds(20, 150, 540, 200);
        getContentPane().add(sp);

        loadTable();

        // Event save
        btnSave.addActionListener(e -> {
            Layanan l = new Layanan();
            l.setNamaLayanan(txtNamaLayanan.getText());
            l.setHarga(Double.parseDouble(txtHarga.getText()));
            layananDAO.save(l);
            reset();
            loadTable();
        });

        // Event update
        btnUpdate.addActionListener(e -> {
            if (selectedId != -1) {
                Layanan l = new Layanan();
                l.setId(selectedId);
                l.setNamaLayanan(txtNamaLayanan.getText());
                l.setHarga(Double.parseDouble(txtHarga.getText()));
                layananDAO.update(l);
                reset();
                loadTable();
            }
        });

        // Event delete
        btnDelete.addActionListener(e -> {
            if (selectedId != -1) {
                layananDAO.delete(selectedId);
                reset();
                loadTable();
            }
        });

        // Event cancel
        btnCancel.addActionListener(e -> reset());

        // Klik tabel -> isi ke form
        tableLayanan.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = tableLayanan.getSelectedRow();
                selectedId = (int) tableLayanan.getValueAt(row, 0);
                txtNamaLayanan.setText(tableLayanan.getValueAt(row, 1).toString());
                txtHarga.setText(tableLayanan.getValueAt(row, 2).toString());
            }
        });
    }

    private void loadTable() {
        List<Layanan> list = layananDAO.show();
        TableLayanan tbl = new TableLayanan(list);
        tableLayanan.setModel(tbl);
    }

    private void reset() {
        txtNamaLayanan.setText("");
        txtHarga.setText("");
        selectedId = -1;
    }

    public static void main(String[] args) {
        new LayananFrame().setVisible(true);
    }
}

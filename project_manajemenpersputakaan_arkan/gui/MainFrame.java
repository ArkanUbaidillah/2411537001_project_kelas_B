package gui;

import dao.BookDAO;
import dao.MemberDAO;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import model.Book;
import model.Member;
import util.IDGenerator;

public class MainFrame extends JFrame {
    private BookDAO bookDAO;
    private MemberDAO memberDAO;

    public MainFrame() {
        bookDAO = new BookDAO();
        memberDAO = new MemberDAO();

        

        setTitle("Library Manager - Arkan");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(30, 30, 30)); // Dark background

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBackground(new Color(45, 45, 45));
        tabbedPane.setForeground(Color.WHITE);
        tabbedPane.add("Buku", createBookPanel());
        tabbedPane.add("Member", createMemberPanel());

        add(tabbedPane);
    }

    private JPanel createBookPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(30, 30, 30));

        // Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(45, 45, 45));
        JButton addButton = new JButton("Tambah Buku");
        JButton viewButton = new JButton("Lihat Buku");
        JButton editButton = new JButton("Edit Buku");
        JButton deleteButton = new JButton("Hapus Buku");

        // Style buttons for Gen Z look
        Color accentColor = new Color(100, 150, 255); // Modern blue accent
        addButton.setBackground(accentColor);
        addButton.setForeground(Color.WHITE);
        viewButton.setBackground(accentColor);
        viewButton.setForeground(Color.WHITE);
        editButton.setBackground(accentColor);
        editButton.setForeground(Color.WHITE);
        deleteButton.setBackground(new Color(255, 100, 100)); // Red for delete
        deleteButton.setForeground(Color.WHITE);

        buttonPanel.add(addButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        // List
        DefaultListModel<String> listModel = new DefaultListModel<>();
        JList<String> bookList = new JList<>(listModel);
        bookList.setBackground(new Color(50, 50, 50));
        bookList.setForeground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(bookList);
        scrollPane.setBackground(new Color(30, 30, 30));
        panel.add(scrollPane, BorderLayout.NORTH);

        // Buttons
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Load initial data
        viewBooks(listModel);

        // Action listeners
        addButton.addActionListener(e -> addBook(listModel));
        viewButton.addActionListener(e -> viewBooks(listModel));
        editButton.addActionListener(e -> editBook(bookList, listModel));
        deleteButton.addActionListener(e -> deleteBook(bookList, listModel));

        return panel;
    }

    private JPanel createMemberPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(30, 30, 30));

        // Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(45, 45, 45));
        JButton addButton = new JButton("Tambah Member");
        JButton viewButton = new JButton("Lihat Member");
        JButton editButton = new JButton("Edit Member");
        JButton deleteButton = new JButton("Hapus Member");

        // Style buttons for Gen Z look
        Color accentColor = new Color(100, 150, 255); // Modern blue accent
        addButton.setBackground(accentColor);
        addButton.setForeground(Color.WHITE);
        viewButton.setBackground(accentColor);
        viewButton.setForeground(Color.WHITE);
        editButton.setBackground(accentColor);
        editButton.setForeground(Color.WHITE);
        deleteButton.setBackground(new Color(255, 100, 100)); // Red for delete
        deleteButton.setForeground(Color.WHITE);

        buttonPanel.add(addButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        // List
        DefaultListModel<String> listModel = new DefaultListModel<>();
        JList<String> memberList = new JList<>(listModel);
        memberList.setBackground(new Color(50, 50, 50));
        memberList.setForeground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(memberList);
        scrollPane.setBackground(new Color(30, 30, 30));
        panel.add(scrollPane, BorderLayout.NORTH);

        // Buttons
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Load initial data
        viewMembers(listModel);

        // Action listeners
        addButton.addActionListener(e -> addMember(listModel));
        viewButton.addActionListener(e -> viewMembers(listModel));
        editButton.addActionListener(e -> editMember(memberList, listModel));
        deleteButton.addActionListener(e -> deleteMember(memberList, listModel));

        return panel;
    }

    private void addBook(DefaultListModel<String> listModel) {
        JTextField titleField = new JTextField();
        JTextField authorField = new JTextField();
        JTextField yearField = new JTextField();

        Object[] message = {
            "Judul:", titleField,
            "Penulis:", authorField,
            "Tahun:", yearField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Tambah Buku", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                String id = IDGenerator.generate("B");
                Book book = new Book.BookBuilder()
                    .setId(id)
                    .setTitle(titleField.getText())
                    .setAuthor(authorField.getText())
                    .setYear(Integer.parseInt(yearField.getText()))
                    .build();
                bookDAO.create(book);
                listModel.addElement(book.getTitle() + " - " + book.getAuthor());
                JOptionPane.showMessageDialog(this, "Buku berhasil ditambahkan!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }
        }
    }

    private void viewBooks(DefaultListModel<String> listModel) {
        listModel.clear();
        List<Book> books = bookDAO.read();
        for (Book book : books) {
            listModel.addElement(book.getId() + " - " + book.getTitle() + " - " + book.getAuthor() + " - " + book.getYear());
        }
    }

    private void editBook(JList<String> bookList, DefaultListModel<String> listModel) {
        int selectedIndex = bookList.getSelectedIndex();
        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(this, "Pilih buku untuk diedit!");
            return;
        }

        String selected = bookList.getSelectedValue();
        String[] parts = selected.split(" - ");
        String id = parts[0];

        List<Book> books = bookDAO.read();
        Book bookToEdit = null;
        for (Book b : books) {
            if (b.getId().equals(id)) {
                bookToEdit = b;
                break;
            }
        }

        if (bookToEdit != null) {
            JTextField titleField = new JTextField(bookToEdit.getTitle());
            JTextField authorField = new JTextField(bookToEdit.getAuthor());
            JTextField yearField = new JTextField(String.valueOf(bookToEdit.getYear()));

            Object[] message = {
                "Judul:", titleField,
                "Penulis:", authorField,
                "Tahun:", yearField
            };

            int option = JOptionPane.showConfirmDialog(this, message, "Edit Buku", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                try {
                    Book updatedBook = new Book.BookBuilder()
                        .setId(bookToEdit.getId())
                        .setTitle(titleField.getText())
                        .setAuthor(authorField.getText())
                        .setYear(Integer.parseInt(yearField.getText()))
                        .build();
                    bookDAO.update(updatedBook);
                    viewBooks(listModel);
                    JOptionPane.showMessageDialog(this, "Buku berhasil diupdate!");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
                }
            }
        }
    }

    private void deleteBook(JList<String> bookList, DefaultListModel<String> listModel) {
        int selectedIndex = bookList.getSelectedIndex();
        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(this, "Pilih buku untuk dihapus!");
            return;
        }

        String selected = bookList.getSelectedValue();
        String[] parts = selected.split(" - ");
        String id = parts[0];

        int confirm = JOptionPane.showConfirmDialog(this, "Hapus buku ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            bookDAO.delete(id);
            listModel.remove(selectedIndex);
            JOptionPane.showMessageDialog(this, "Buku berhasil dihapus!");
        }
    }

    private void addMember(DefaultListModel<String> listModel) {
        JTextField nameField = new JTextField();
        JTextField addressField = new JTextField();

        Object[] message = {
            "Nama:", nameField,
            "Alamat:", addressField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Tambah Member", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                String id = IDGenerator.generate("M");
                Member member = new Member(id, nameField.getText(), addressField.getText());
                memberDAO.create(member);
                listModel.addElement(member.getName() + " - " + member.getAddress());
                JOptionPane.showMessageDialog(this, "Member berhasil ditambahkan!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }
        }
    }

    private void viewMembers(DefaultListModel<String> listModel) {
        listModel.clear();
        List<Member> members = memberDAO.read();
        for (Member member : members) {
            listModel.addElement(member.getId() + " - " + member.getName() + " - " + member.getAddress());
        }
    }

    private void editMember(JList<String> memberList, DefaultListModel<String> listModel) {
        int selectedIndex = memberList.getSelectedIndex();
        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(this, "Pilih member untuk diedit!");
            return;
        }

        String selected = memberList.getSelectedValue();
        String[] parts = selected.split(" - ");
        String id = parts[0];

        List<Member> members = memberDAO.read();
        Member memberToEdit = null;
        for (Member m : members) {
            if (m.getId().equals(id)) {
                memberToEdit = m;
                break;
            }
        }

        if (memberToEdit != null) {
            JTextField nameField = new JTextField(memberToEdit.getName());
            JTextField addressField = new JTextField(memberToEdit.getAddress());

            Object[] message = {
                "Nama:", nameField,
                "Alamat:", addressField
            };

            int option = JOptionPane.showConfirmDialog(this, message, "Edit Member", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                try {
                    Member updatedMember = new Member(memberToEdit.getId(), nameField.getText(), addressField.getText());
                    memberDAO.update(updatedMember);
                    viewMembers(listModel);
                    JOptionPane.showMessageDialog(this, "Member berhasil diupdate!");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
                }
            }
        }
    }

    private void deleteMember(JList<String> memberList, DefaultListModel<String> listModel) {
        int selectedIndex = memberList.getSelectedIndex();
        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(this, "Pilih member untuk dihapus!");
            return;
        }

        String selected = memberList.getSelectedValue();
        String[] parts = selected.split(" - ");
        String id = parts[0];

        int confirm = JOptionPane.showConfirmDialog(this, "Hapus member ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            memberDAO.delete(id);
            listModel.remove(selectedIndex);
            JOptionPane.showMessageDialog(this, "Member berhasil dihapus!");
        }
    }
}

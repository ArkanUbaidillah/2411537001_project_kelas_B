package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Database {
    private static Connection conn;
    public static Connection koneksi() {
        if (conn == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost/build_pattern",
                    "root",
                    ""
                );
                System.out.println("Koneksi database berhasil!");
            } catch (ClassNotFoundException | SQLException e) {
                JOptionPane.showMessageDialog(null, "Koneksi database gagal:\n" + e.getMessage());
            }
        }
        return conn;
    }
}

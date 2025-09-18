package DAO;

import config.Database;
import model.Layanan;
import java.sql.*;
import java.util.*;

public class LayananRepo implements LayananDAO {
    Connection conn;

    public LayananRepo() {
        conn = Database.koneksi();
    }

    @Override
    public void save(Layanan layanan) {
        try {
            String sql = "INSERT INTO layanan (namalayanan, harga) VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, layanan.getNamaLayanan());
            ps.setDouble(2, layanan.getHarga());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Layanan> show() {
        List<Layanan> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM layanan";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Layanan l = new Layanan();
                l.setId(rs.getInt("id"));
                l.setNamaLayanan(rs.getString("namalayanan"));
                l.setHarga(rs.getDouble("harga"));
                list.add(l);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void update(Layanan layanan) {
        try {
            String sql = "UPDATE layanan SET namalayanan=?, harga=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, layanan.getNamaLayanan());
            ps.setDouble(2, layanan.getHarga());
            ps.setInt(3, layanan.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try {
            String sql = "DELETE FROM layanan WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

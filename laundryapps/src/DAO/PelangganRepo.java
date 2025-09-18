package DAO;

import config.Database;
import model.Pelanggan;
import java.sql.*;
import java.util.*;

public class PelangganRepo implements PelangganDAO {
    Connection conn;

    public PelangganRepo() {
        conn = Database.koneksi();
    }
@Override
    public void save(Pelanggan pelanggan) {
        try {
            String sql = "INSERT INTO pelanggan (nama, alamat, no_telp) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, pelanggan.getNama());
            ps.setString(2, pelanggan.getAlamat());
            ps.setString(3, pelanggan.getNoTelp());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Pelanggan> show() {
        List<Pelanggan> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM pelanggan";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Pelanggan p = new Pelanggan();
                p.setId(rs.getInt("id"));
                p.setNama(rs.getString("nama"));
                p.setAlamat(rs.getString("alamat"));
                p.setNoTelp(rs.getString("no_telp"));
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void update(Pelanggan pelanggan) {
        try {
            String sql = "UPDATE pelanggan SET nama=?, alamat=?, no_telp=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, pelanggan.getNama());
            ps.setString(2, pelanggan.getAlamat());
            ps.setString(3, pelanggan.getNoTelp());
            ps.setInt(4, pelanggan.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try {
            String sql = "DELETE FROM pelanggan WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

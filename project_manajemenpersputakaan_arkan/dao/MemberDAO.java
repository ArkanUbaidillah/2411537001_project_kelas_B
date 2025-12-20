package dao;

import database.DatabaseConnection;
import model.Member;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO implements CRUD<Member> {
    private final Connection conn;

    public MemberDAO() {
        conn = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public void create(Member member) {
        try {
            String sql = "INSERT INTO members VALUES (?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, member.getId());
            ps.setString(2, member.getName());
            ps.setString(3, member.getAddress());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Gagal menambah member");
        }
    }

    @Override
    public List<Member> read() {
        List<Member> list = new ArrayList<>();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM members");
            while (rs.next()) {
                Member member = new Member(rs.getString("id"), rs.getString("name"), rs.getString("address"));
                list.add(member);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void update(Member member) {
        try {
            String sql = "UPDATE members SET name=?, address=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, member.getName());
            ps.setString(2, member.getAddress());
            ps.setString(3, member.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String id) {
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM members WHERE id=?");
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

package dao;

import database.DatabaseConnection;
import model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO implements CRUD<Book> {
    private final Connection conn;

    public BookDAO() {
        conn = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public void create(Book book) {
        try {
            String sql = "INSERT INTO books VALUES (?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, book.getId());
            ps.setString(2, book.getTitle());
            ps.setString(3, book.getAuthor());
            ps.setInt(4, book.getYear());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Gagal menambah buku");
        }
    }

    @Override
    public List<Book> read() {
        List<Book> list = new ArrayList<>();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM books");
            while (rs.next()) {
                Book book = new Book.BookBuilder()
                        .setId(rs.getString("id"))
                        .setTitle(rs.getString("title"))
                        .setAuthor(rs.getString("author"))
                        .setYear(rs.getInt("year"))
                        .build();
                list.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void update(Book book) {
        try {
            String sql = "UPDATE books SET title=?, author=?, year=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setInt(3, book.getYear());
            ps.setString(4, book.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String id) {
        try {
            PreparedStatement ps = conn.prepareStatement(
                "DELETE FROM books WHERE id=?"
            );
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

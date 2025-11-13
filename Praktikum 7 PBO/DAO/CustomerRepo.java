package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import config.Database;
import model.Costumer;
import model.CustomerBuilder;
public class CustomerRepo implements CustomerDAO {
    private Connection connection;
    private final String insert = "INSERT INTO customer (nama, email, alamat, hp) VALUES (?,?,?,?)";
    private final String select = "SELECT * FROM customer";
    private final String delete = "DELETE FROM customer WHERE id=?";
    private final String update = "UPDATE customer SET nama=?, email=?, alamat=?, hp=? WHERE id=?";
    public CustomerRepo() {
        connection = Database.koneksi();
    }
    public List<Costumer> show() {
        List<Costumer> ls = new ArrayList<>();
        if (connection == null) {
            Logger.getLogger(CustomerRepo.class.getName()).log(Level.WARNING, "Database connection is null in show()");
            return ls;
        }
        Statement st = null;
        ResultSet rs = null;
        try {
            st = connection.createStatement();
            rs = st.executeQuery(select);
            while(rs.next()) {
                Costumer cs = new CustomerBuilder()
                    .setId(rs.getString("id"))
                    .setNama(rs.getString("nama"))
                    .setEmail(rs.getString("email"))
                    .setAlamat(rs.getString("alamat"))
                    .setNoHp(rs.getString("hp"))
                    .build();
                ls.add(cs);
            }
        } catch (SQLException e) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            try {
                if (st != null) st.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return ls;
    }
    public void save(Costumer cs) {
        if (connection == null) {
            Logger.getLogger(CustomerRepo.class.getName()).log(Level.WARNING, "Database connection is null in save()");
            return;
        }
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(insert);
            st.setString(1, cs.getNama());
            st.setString(2, cs.getEmail());
            st.setString(3, cs.getAlamat());
            st.setString(4, cs.getNoHp());
            st.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(CustomerRepo.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (st != null) st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void update(Costumer cs) {
        if (connection == null) {
            Logger.getLogger(CustomerRepo.class.getName()).log(Level.WARNING, "Database connection is null in update()");
            return;
        }
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(update);
            st.setString(1, cs.getNama());
            st.setString(2, cs.getEmail());
            st.setString(3, cs.getAlamat());
            st.setString(4, cs.getNoHp());
            st.setString(5, cs.getId());
            int affected = st.executeUpdate();
            if (affected == 0) {
                Logger.getLogger(CustomerRepo.class.getName()).log(Level.WARNING, "Update affected 0 rows for id=" + cs.getId());
            }
        } catch (SQLException e) {
            Logger.getLogger(CustomerRepo.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (st != null) st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void delete(String id) {
        if (connection == null) {
            Logger.getLogger(CustomerRepo.class.getName()).log(Level.WARNING, "Database connection is null in delete()");
            return;
        }
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(delete);
            st.setString(1, id);
            int affected = st.executeUpdate();
            if (affected == 0) {
                Logger.getLogger(CustomerRepo.class.getName()).log(Level.WARNING, "Delete affected 0 rows for id=" + id);
            }
        } catch (SQLException e) {
            Logger.getLogger(CustomerRepo.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (st != null) st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
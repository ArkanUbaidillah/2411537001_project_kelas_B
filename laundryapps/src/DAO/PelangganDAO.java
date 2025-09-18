package DAO;

import java.util.List;
import model.Pelanggan;

public interface PelangganDAO {
    void save(Pelanggan pelanggan);
    List<Pelanggan> show();
    void update(Pelanggan pelanggan);
    void delete(int id);
}


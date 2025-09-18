package DAO;

import java.util.List;
import model.Layanan;

public interface LayananDAO {
    void save(Layanan layanan);
    List<Layanan> show();
    void update(Layanan layanan);
    void delete(int id);
}

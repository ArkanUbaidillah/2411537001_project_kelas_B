package DAO;

import java.util.List;
import model.Costumer;

public interface CustomerDAO {

    void save(Costumer cs);
    void update(Costumer cs);
    void delete(String id);
    List<Costumer> show();
}

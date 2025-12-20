package dao;

import java.util.List;

public interface CRUD<T> {
    void create(T data);
    List<T> read();
    void update(T data);
    void delete(String id);
}

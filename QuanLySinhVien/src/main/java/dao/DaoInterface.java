package dao;

import java.util.ArrayList;

public interface DaoInterface<T> {
    int insert(T t);

    int update(T t);

    int delete(T t);

    ArrayList<T> getAll();

    T selectById(int id);

}

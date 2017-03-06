package team.lw.arena.dao;

import java.io.Serializable;
import java.util.List;


public interface BaseDao<T> {

     void merge(T entity);

     void save(T entity);

     void update(T entity);

     void delete(Serializable id);

     T findObjectById(Serializable id);

     List<T> findObjects();
}

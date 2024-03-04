package de.ait.taskhandler.repositories;

import java.util.List;

public interface CrudRepository<T> {

    T findById(Long id); // поиск по идентификатору

    List<T> findAll(); // получение всех данных

    void save(T model); // сохранение объекта в хранилище

    void deleteById(Long id); // удаление по id

    void update(T model); // обновление данных в хранилище
}

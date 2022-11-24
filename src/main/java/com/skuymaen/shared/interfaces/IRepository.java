package com.skuymaen.shared.interfaces;

import java.util.List;

public interface IRepository<T> {
    Boolean insert(T newItem);

    List<T> findAll();

    T findById(Long id);

    T update(T updatedItem);

    Long delete(Long deletedId);
}

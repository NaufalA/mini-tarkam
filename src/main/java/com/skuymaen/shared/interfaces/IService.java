package com.skuymaen.shared.interfaces;

import java.util.List;

public interface IService<T> {
    Boolean create(T newItem);

    List<T> getAll();

    T getById(Long id);

    T update(T updatedItem);

    Long remove(Long deletedId);
}

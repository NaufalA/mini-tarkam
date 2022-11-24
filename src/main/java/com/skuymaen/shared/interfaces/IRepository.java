package com.skuymaen.shared.interfaces;

import java.util.List;

public interface IRepository<T, Id> {
    Boolean insert(T newItem);

    List<T> findAll();

    T findById(Id id);

    T update(T updatedItem);

    Id delete(Id deletedId);
}

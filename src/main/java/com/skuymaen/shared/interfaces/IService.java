package com.skuymaen.shared.interfaces;

import java.util.List;

public interface IService<T, Id> {
    Boolean create(T newItem);

    List<T> getAll();

    T getById(Id id);

    T update(T updatedItem);

    Id remove(Id deletedId);
}

package com.skuymaen.shared.classes;

import com.skuymaen.shared.interfaces.IRepository;
import com.skuymaen.shared.interfaces.IService;

import java.util.List;

public abstract class BaseService<T> implements IService<T> {
    protected IRepository<T> repository;

    public BaseService(IRepository<T> repository) {
        this.repository = repository;
    }

    @Override
    public Boolean create(T newItem) {
        return repository.insert(newItem);
    }

    @Override
    public List<T> getAll() {
        return repository.findAll();
    }

    @Override
    public T getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public T update(T updatedItem) {
        return repository.update(updatedItem);
    }

    @Override
    public Long remove(Long deletedId) {
        return repository.delete(deletedId);
    }
}

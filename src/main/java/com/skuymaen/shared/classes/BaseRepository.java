package com.skuymaen.shared.classes;

import com.skuymaen.shared.interfaces.IRepository;
import jakarta.persistence.EntityManager;

import java.util.List;

public abstract class BaseRepository<T, Id> implements IRepository<T, Id> {
    protected EntityManager entityManager;

    public BaseRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Boolean insert(T newItem) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(newItem);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }

        return true;
    }

    @Override
    public abstract List<T> findAll();

    @Override
    public abstract T findById(Id id);

    @Override
    public T update(T updatedItem) {
        try {
            entityManager.getTransaction().begin();
            T savedItem = entityManager.merge(updatedItem);
            entityManager.getTransaction().commit();
            return savedItem;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public Id delete(Id deletedId) {
        try {
            T deletedItem = findById(deletedId);
            entityManager.getTransaction().begin();
            entityManager.remove(deletedItem);
            entityManager.getTransaction().commit();
            return deletedId;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }
}

package com.skuymaen.shared.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public abstract class JPAUtility {
    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;

    private static final String PERSISTENCE_UNIT_NAME = "default";

    private static void createEntityManager() {
        entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        entityManager = entityManagerFactory.createEntityManager();
    }

    public static EntityManager getEntityManager() {
        if (entityManager == null) {
            createEntityManager();
        }
        return entityManager;
    }

    public static void close() {
        entityManager.close();
        entityManagerFactory.close();
    }
}

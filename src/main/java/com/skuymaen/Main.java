package com.skuymaen;

import com.skuymaen.shared.utils.JPAUtility;
import jakarta.persistence.EntityManager;

public class Main {
    public static void main(String[] args) {
        EntityManager em = JPAUtility.getEntityManager();

        JPAUtility.close();
    }
}
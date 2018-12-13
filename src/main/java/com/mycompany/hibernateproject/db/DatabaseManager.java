package com.mycompany.hibernateproject.db;

import com.mycompany.hibernateproject.models.Player;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DatabaseManager {

    public static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hibernate-dynamic");
    public static EntityManager entityManager = entityManagerFactory.createEntityManager();

    public static void addToDatabase(Object obj) {
        entityManager.getTransaction().begin();
        entityManager.persist(obj);
        entityManager.getTransaction().commit();
    }

    public static void close() {
        entityManager.close();
        entityManagerFactory.close();
    }
}

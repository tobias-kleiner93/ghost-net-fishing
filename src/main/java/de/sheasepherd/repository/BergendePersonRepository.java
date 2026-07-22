package de.sheasepherd.repository;

import de.sheasepherd.entity.BergendePerson;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class BergendePersonRepository {

    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY =
            Persistence.createEntityManagerFactory("ghostNetPU");

    public BergendePerson speichern(BergendePerson bergendePerson) {
        EntityManager entityManager =
                ENTITY_MANAGER_FACTORY.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(bergendePerson);
            entityManager.getTransaction().commit();

            return bergendePerson;

        } catch (RuntimeException exception) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }

            throw exception;

        } finally {
            entityManager.close();
        }
    }
}
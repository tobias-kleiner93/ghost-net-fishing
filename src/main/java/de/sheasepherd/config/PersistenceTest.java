package de.sheasepherd.config;

import de.sheasepherd.entity.GhostNet;
import de.sheasepherd.entity.Status;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class PersistenceTest {

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = null;
        EntityManager entityManager = null;

        try {
            entityManagerFactory =
                    Persistence.createEntityManagerFactory("ghostNetPU");

            entityManager = entityManagerFactory.createEntityManager();

            GhostNet ghostNet = new GhostNet();
            ghostNet.setBreitengrad(54.321);
            ghostNet.setLaengengrad(10.123);
            ghostNet.setGroesse(15.5);
            ghostNet.setStatus(Status.GEMELDET);

            entityManager.getTransaction().begin();
            entityManager.persist(ghostNet);
            entityManager.getTransaction().commit();

            System.out.println(
                    "Das Geisternetz wurde erfolgreich gespeichert. ID: "
                    + ghostNet.getId());

        } catch (Exception exception) {

            if (entityManager != null
                    && entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }

            exception.printStackTrace();

        } finally {

            if (entityManager != null) {
                entityManager.close();
            }

            if (entityManagerFactory != null) {
                entityManagerFactory.close();
            }
        }
    }
}
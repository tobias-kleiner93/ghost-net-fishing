package de.sheasepherd.repository;

import java.util.List;

import de.sheasepherd.entity.GhostNet;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class GhostNetRepository {

    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY =
            Persistence.createEntityManagerFactory("ghostNetPU");

    public GhostNet speichern(GhostNet ghostNet) {
        EntityManager entityManager =
                ENTITY_MANAGER_FACTORY.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(ghostNet);
            entityManager.getTransaction().commit();

            return ghostNet;

        } catch (RuntimeException exception) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }

            throw exception;

        } finally {
            entityManager.close();
        }
    }

    public List<GhostNet> findeAlle() {
        EntityManager entityManager =
                ENTITY_MANAGER_FACTORY.createEntityManager();

        try {
            return entityManager
                    .createQuery(
                            "SELECT g FROM GhostNet g ORDER BY g.id",
                            GhostNet.class)
                    .getResultList();

        } finally {
            entityManager.close();
        }
    }

    public GhostNet findeNachId(Long id) {
        EntityManager entityManager =
                ENTITY_MANAGER_FACTORY.createEntityManager();

        try {
            return entityManager.find(GhostNet.class, id);

        } finally {
            entityManager.close();
        }
    }

    public GhostNet aktualisieren(GhostNet ghostNet) {
        EntityManager entityManager =
                ENTITY_MANAGER_FACTORY.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            GhostNet aktualisiertesGhostNet =
                    entityManager.merge(ghostNet);
            entityManager.getTransaction().commit();

            return aktualisiertesGhostNet;

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
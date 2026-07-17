package de.sheasepherd.config;

import java.util.List;

import de.sheasepherd.entity.GhostNet;
import de.sheasepherd.entity.Status;
import de.sheasepherd.repository.GhostNetRepository;

public class RepositoryTest {

    public static void main(String[] args) {

        GhostNetRepository repository = new GhostNetRepository();

        GhostNet ghostNet = new GhostNet();
        ghostNet.setBreitengrad(53.5511);
        ghostNet.setLaengengrad(9.9937);
        ghostNet.setGroesse(22.5);
        ghostNet.setStatus(Status.GEMELDET);

        repository.speichern(ghostNet);

        System.out.println(
                "Neues Geisternetz gespeichert. ID: "
                + ghostNet.getId());

        List<GhostNet> geisternetze = repository.findeAlle();

        System.out.println(
                "Anzahl gespeicherter Geisternetze: "
                + geisternetze.size());

        for (GhostNet netz : geisternetze) {
            System.out.println(
                    "ID: " + netz.getId()
                    + ", Größe: " + netz.getGroesse()
                    + ", Status: " + netz.getStatus());
        }
    }
}
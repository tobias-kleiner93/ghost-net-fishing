package de.sheasepherd.bean;

import java.util.List;

import de.sheasepherd.entity.BergendePerson;
import de.sheasepherd.entity.GhostNet;
import de.sheasepherd.entity.Status;
import de.sheasepherd.repository.BergendePersonRepository;
import de.sheasepherd.repository.GhostNetRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
public class GhostNetBean {

    private Double breitengrad;
    private Double laengengrad;
    private Double groesse;

    private final GhostNetRepository repository =
            new GhostNetRepository();

    private final BergendePersonRepository bergendePersonRepository =
            new BergendePersonRepository();

    private String meldung;

    private Long ausgewaehlteGhostNetId;
    private String nameBergendePerson;

    private Long geborgenesGhostNetId;

    public String speichern() {

        GhostNet ghostNet = new GhostNet();

        ghostNet.setBreitengrad(breitengrad);
        ghostNet.setLaengengrad(laengengrad);
        ghostNet.setGroesse(groesse);
        ghostNet.setStatus(Status.GEMELDET);

        repository.speichern(ghostNet);

        meldung = "Geisternetz wurde erfolgreich gespeichert.";

        return null;
    }

    public String bergen() {

        GhostNet ghostNet =
                repository.findeNachId(ausgewaehlteGhostNetId);

        if (ghostNet == null) {
            meldung = "Das ausgewählte Geisternetz wurde nicht gefunden.";
            return null;
        }

        BergendePerson bergendePerson = new BergendePerson();
        bergendePerson.setName(nameBergendePerson);

        bergendePersonRepository.speichern(bergendePerson);

        ghostNet.setBergendePerson(bergendePerson);
        ghostNet.setStatus(Status.BERGUNG_BEVORSTEHEND);

        repository.aktualisieren(ghostNet);

        meldung = "Die Bergung wurde erfolgreich übernommen.";

        return null;
    }

    public String alsGeborgenMarkieren() {

        GhostNet ghostNet =
                repository.findeNachId(geborgenesGhostNetId);

        if (ghostNet == null) {
            meldung = "Das ausgewählte Geisternetz wurde nicht gefunden.";
            return null;
        }

        ghostNet.setStatus(Status.GEBORGEN);

        repository.aktualisieren(ghostNet);

        meldung =
                "Das Geisternetz wurde erfolgreich als geborgen markiert.";

        return null;
    }

    public List<GhostNet> getGeisternetze() {
        return repository.findeAlle();
    }

    public Double getBreitengrad() {
        return breitengrad;
    }

    public void setBreitengrad(Double breitengrad) {
        this.breitengrad = breitengrad;
    }

    public Double getLaengengrad() {
        return laengengrad;
    }

    public void setLaengengrad(Double laengengrad) {
        this.laengengrad = laengengrad;
    }

    public Double getGroesse() {
        return groesse;
    }

    public void setGroesse(Double groesse) {
        this.groesse = groesse;
    }

    public String getMeldung() {
        return meldung;
    }

    public void setMeldung(String meldung) {
        this.meldung = meldung;
    }

    public Long getAusgewaehlteGhostNetId() {
        return ausgewaehlteGhostNetId;
    }

    public void setAusgewaehlteGhostNetId(
            Long ausgewaehlteGhostNetId) {
        this.ausgewaehlteGhostNetId = ausgewaehlteGhostNetId;
    }

    public String getNameBergendePerson() {
        return nameBergendePerson;
    }

    public void setNameBergendePerson(
            String nameBergendePerson) {
        this.nameBergendePerson = nameBergendePerson;
    }

    public Long getGeborgenesGhostNetId() {
        return geborgenesGhostNetId;
    }

    public void setGeborgenesGhostNetId(
            Long geborgenesGhostNetId) {
        this.geborgenesGhostNetId = geborgenesGhostNetId;
    }
}
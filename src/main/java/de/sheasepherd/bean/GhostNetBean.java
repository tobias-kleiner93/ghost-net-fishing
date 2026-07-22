package de.sheasepherd.bean;

import java.util.List;

import de.sheasepherd.entity.BergendePerson;
import de.sheasepherd.entity.GhostNet;
import de.sheasepherd.entity.Status;
import de.sheasepherd.repository.GhostNetRepository;
import de.sheasepherd.repository.BergendePersonRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
public class GhostNetBean {

    private double breitengrad;

    private double laengengrad;
    
	private double groesse;

    private GhostNetRepository repository = new GhostNetRepository();
	
    private BergendePersonRepository bergendePersonRepository =
            new BergendePersonRepository();
    
    private String meldung;
    
    private Long ausgewaehlteGhostNetId;

    private String nameBergendePerson;
        

	public Long getAusgewaehlteGhostNetId() {
		return ausgewaehlteGhostNetId;
	}


	public void setAusgewaehlteGhostNetId(Long ausgewaehlteGhostNetId) {
		this.ausgewaehlteGhostNetId = ausgewaehlteGhostNetId;
	}


	public String getNameBergendePerson() {
		return nameBergendePerson;
	}


	public void setNameBergendePerson(String nameBergendePerson) {
		this.nameBergendePerson = nameBergendePerson;
	}


	public double getBreitengrad() {
		return breitengrad;
	}
    

	public void setBreitengrad(double breitengrad) {
		this.breitengrad = breitengrad;
	}

	public double getLaengengrad() {
		return laengengrad;
	}

	public void setLaengengrad(double laengengrad) {
		this.laengengrad = laengengrad;
	}

	public double getGroesse() {
		return groesse;
	}

	public void setGroesse(double groesse) {
		this.groesse = groesse;
	}
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
	
	public String getMeldung() {
		return meldung;
	}


	public void setMeldung(String meldung) {
		this.meldung = meldung;
	}
	
	public List<GhostNet> getGeisternetze() {
	    return repository.findeAlle();
	}

}
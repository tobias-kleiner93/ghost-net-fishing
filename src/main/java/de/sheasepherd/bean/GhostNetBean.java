package de.sheasepherd.bean;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import de.sheasepherd.repository.GhostNetRepository;
import de.sheasepherd.entity.GhostNet;
import de.sheasepherd.entity.Status;

@Named
@RequestScoped
public class GhostNetBean {

    private double breitengrad;

    private double laengengrad;
    
	private double groesse;

    private GhostNetRepository repository = new GhostNetRepository();
	
    private String meldung;
    

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
	
	public String getMeldung() {
		return meldung;
	}


	public void setMeldung(String meldung) {
		this.meldung = meldung;
	}

}
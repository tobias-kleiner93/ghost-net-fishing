package de.sheasepherd.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class GhostNet {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private double breitengrad;

private double laengengrad;

private double groesse;

@Enumerated(EnumType.STRING)
private Status status;

@ManyToOne
@JoinColumn(name = "meldende_person_id")
private MeldendePerson meldendePerson;

@ManyToOne
@JoinColumn(name = "bergende_person_id")
private BergendePerson bergendePerson;

public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
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

public Status getStatus() {
    return status;
}

public void setStatus(Status status) {
    this.status = status;
}

public MeldendePerson getMeldendePerson() {
    return meldendePerson;
}

public void setMeldendePerson(MeldendePerson meldendePerson) {
    this.meldendePerson = meldendePerson;
}

public BergendePerson getBergendePerson() {
    return bergendePerson;
}

public void setBergendePerson(BergendePerson bergendePerson) {
    this.bergendePerson = bergendePerson;
}

}

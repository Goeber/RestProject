package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Taetigkeit implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;

    @NotNull
    private LocalDate datum;

    @NotNull
    private Integer dauer;

    @NotNull
    private String beschreibung;

    @JsonManagedReference
    @ManyToOne
    private Mitarbeiter mitarbeiter;

    protected Taetigkeit(){}

    public Taetigkeit( @NotNull String beschreibung, @NotNull LocalDate datum, @NotNull Integer dauer, @NotNull Mitarbeiter mitarbeiter){
        this.datum = datum;
        this.dauer = dauer;
        this.beschreibung = beschreibung;
        this.mitarbeiter = mitarbeiter;
    }

    public Integer getId() {
        return id;
    }


    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public Integer getDauer() {
        return dauer;
    }

    public void setDauer(Integer dauer) {
        this.dauer = dauer;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public Mitarbeiter getMitarbeiter() {
        return mitarbeiter;
    }

    public void setMitarbeiter(Mitarbeiter mitarbeiter) {
        this.mitarbeiter = mitarbeiter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Taetigkeit that = (Taetigkeit) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Taetigkeit{" +
                "id=" + id +
                ", datum=" + datum +
                ", dauer=" + dauer +
                ", beschreibung='" + beschreibung + '\'' +
                ", mitarbeiter=" + mitarbeiter +
                '}';
    }
}

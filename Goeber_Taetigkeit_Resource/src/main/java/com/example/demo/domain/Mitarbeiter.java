package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Stream;

@Entity
public class Mitarbeiter implements Serializable {
    @Id
    @Size(max = 6)
    private String id;

    @NotNull
    private String zuname;

    @NotNull
    private String vorname;

    @JsonBackReference
    @OneToMany(mappedBy = "mitarbeiter")
    private Collection<Taetigkeit> taetigkeiten = new ArrayList<>();

    protected Mitarbeiter(){}

    public Mitarbeiter(@NotNull String id, @NotNull String zuname, @NotNull String vorname){
        this.id = id;
        this.zuname = zuname;
        this.vorname = vorname;
    }

    public void addTaetigkeit(Taetigkeit taetigkeit){
        this.taetigkeiten.add(taetigkeit);
        taetigkeit.setMitarbeiter(this);
    }

    public String getId() {
        return id;
    }

    public String getZuname() {
        return zuname;
    }

    public void setZuname(String zuname) {
        this.zuname = zuname;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public Stream<Taetigkeit> getTaetigkeiten() {
        return taetigkeiten.stream();
    }

    public void setTaetigkeiten(Collection<Taetigkeit> taetigkeiten) {
        this.taetigkeiten = taetigkeiten;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mitarbeiter that = (Mitarbeiter) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Mitarbeiter{" +
                "id='" + id + '\'' +
                ", zuname='" + zuname + '\'' +
                ", vorname='" + vorname + '\'' +
                ", taetigkeiten=" + taetigkeiten +
                '}';
    }
}

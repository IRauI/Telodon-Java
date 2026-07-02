package mpp.telodon.model;

import mpp.telodon.repository.HasId;

import java.util.Objects;

public class Donator implements HasId<Integer> {
    private int id;
    private String nume;
    private String prenume;
    private String adresa;
    private String telefon;

    public Donator(int id, String nume, String prenume, String adresa, String telefon) {
        this.id = id;
        this.nume = nume;
        this.prenume = prenume;
        this.adresa = adresa;
        this.telefon = telefon;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Donator donator = (Donator) o;
        return id == donator.id && Objects.equals(nume, donator.nume) && Objects.equals(prenume, donator.prenume) && Objects.equals(adresa, donator.adresa) && Objects.equals(telefon, donator.telefon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nume, prenume, adresa, telefon);
    }

    @Override
    public String toString() {
        return "Donator{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", adresa='" + adresa + '\'' +
                ", telefon='" + telefon + '\'' +
                '}';
    }
}

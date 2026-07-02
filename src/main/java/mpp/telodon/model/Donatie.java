package mpp.telodon.model;

import mpp.telodon.repository.HasId;

import java.util.Objects;

public class Donatie implements HasId<Integer> {
    int id;
    private int donator_id;
    private int caritabil_id;
    private double suma;

    public Donatie(int id, int donator_id, int caritabil_id, double suma) {
        this.id = id;
        this.donator_id = donator_id;
        this.caritabil_id = caritabil_id;
        this.suma = suma;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public int getDonator_id() {
        return donator_id;
    }

    public void setDonator_id(int donator_id) {
        this.donator_id = donator_id;
    }

    public int getCaritabil_id() {
        return caritabil_id;
    }

    public void setCaritabil_id(int caritabil_id) {
        this.caritabil_id = caritabil_id;
    }

    public double getSuma() {
        return suma;
    }

    public void setSuma(double suma) {
        this.suma = suma;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Donatie donatie = (Donatie) o;
        return id == donatie.id && donator_id == donatie.donator_id && caritabil_id == donatie.caritabil_id && suma == donatie.suma;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, donator_id, caritabil_id, suma);
    }

    @Override
    public String toString() {
        return "Donatie{" +
                "id=" + id +
                ", donator_id=" + donator_id +
                ", caritabil_id=" + caritabil_id +
                ", suma=" + suma +
                '}';
    }
}

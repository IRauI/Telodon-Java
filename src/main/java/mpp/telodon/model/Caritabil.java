package mpp.telodon.model;

import mpp.telodon.repository.HasId;

import java.util.Objects;

public class Caritabil implements HasId<Integer> {
    private int id;
    private String denumire;
    private double total;

    public Caritabil(int id, String denumire, double suma) {
        this.id = id;
        this.denumire = denumire;
        this.total = suma;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Caritabil caritabil = (Caritabil) o;
        return id == caritabil.id && total == caritabil.total && Objects.equals(denumire, caritabil.denumire);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, denumire, total);
    }

    @Override
    public String toString() {
        return "Caritabil{" +
                "id=" + id +
                ", denumire='" + denumire + '\'' +
                ", total=" + total +
                '}';
    }
}

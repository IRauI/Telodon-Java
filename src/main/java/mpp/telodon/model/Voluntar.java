package mpp.telodon.model;

import mpp.telodon.repository.HasId;

import java.util.Objects;

public class Voluntar implements HasId<Integer> {
    private int id;
    private String username;
    private String password;

    public Voluntar(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Voluntar voluntar = (Voluntar) o;
        return id == voluntar.id && Objects.equals(username, voluntar.username) && Objects.equals(password, voluntar.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password);
    }

    @Override
    public String toString() {
        return "Voluntar{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

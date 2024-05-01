package ro.mpp2024.Domain;

import java.io.Serializable;
import java.util.Objects;

public class Order extends Entity<Integer> implements Serializable {

    private Integer id_exemplar;
    private User user;
    private Status status;

    public Order() {}

    public Order(Integer ID, Integer id_exemplar, User user, Status status) {
        this.ID = ID;
        this.id_exemplar = id_exemplar;
        this.user = user;
        this.status = status;
    }

    public Order(Integer id_exemplar, User user, Status status) {
        this.id_exemplar = id_exemplar;
        this.user = user;
        this.status = status;
    }

    public Integer getId_exemplar() {
        return id_exemplar;
    }

    public void setId_exemplar(Integer id_exemplar) {
        this.id_exemplar = id_exemplar;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id " + ID + '\'' +
                "id_exemplar=" + id_exemplar +
                ", idSection=" + user +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id_exemplar, order.id_exemplar) && Objects.equals(user, order.user) && status == order.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_exemplar, user, status);
    }
}

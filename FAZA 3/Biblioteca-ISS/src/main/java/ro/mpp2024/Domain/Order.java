package ro.mpp2024.Domain;

import java.io.Serializable;
import java.util.Objects;

public class Order extends Entity<Integer> implements Serializable {

    private Integer id_exemplar;
    private Integer id_user;
    private Status status;

    public Order() {}

    public Order(Integer ID, Integer id_exemplar, Integer id_user, Status status) {
        this.ID = ID;
        this.id_exemplar = id_exemplar;
        this.id_user = id_user;
        this.status = status;
    }

    public Order(Integer id_exemplar, Integer id_user, Status status) {
        this.id_exemplar = id_exemplar;
        this.id_user = id_user;
        this.status = status;
    }

    public Integer getId_exemplar() {
        return id_exemplar;
    }

    public void setId_exemplar(Integer id_exemplar) {
        this.id_exemplar = id_exemplar;
    }

    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer user) {
        this.id_user = user;
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
                ", id_user=" + id_user +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id_exemplar, order.id_exemplar) && Objects.equals(id_user, order.id_user) && status == order.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_exemplar, id_user, status);
    }
}

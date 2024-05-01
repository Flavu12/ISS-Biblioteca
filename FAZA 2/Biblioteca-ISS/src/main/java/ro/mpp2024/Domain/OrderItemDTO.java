package ro.mpp2024.Domain;

import java.util.Objects;

public class OrderItemDTO{

    private String name;
    private Integer id_exemplar;
    private Status status;

public OrderItemDTO(String name, Integer id_exemplar, Status status) {
    this.name = name;
    this.id_exemplar = id_exemplar;
    this.status = status;
}

public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}

public Integer getIdExemplar() {
    return id_exemplar;
}

public void setId_exemplar(Integer id_exemplar) {
    this.id_exemplar = id_exemplar;
}

public Status getStatus() {
    return status;
}

public void setStatus(Status status) {
    this.status = status;
}

@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    OrderItemDTO that = (OrderItemDTO) o;
    return Objects.equals(name, that.name) && Objects.equals(id_exemplar, that.id_exemplar) && status == that.status;
}

@Override
public int hashCode() {
    return Objects.hash(name, id_exemplar, status);
}

@Override
public String toString() {
    return "OrderItemDTO{" +
            "name='" + name + '\'' +
            ", id_exemplar=" + id_exemplar +
            ", status=" + status +
            '}';
}
}

package ro.mpp2024.Domain;

import java.io.Serializable;
import java.util.Objects;
import javafx.util.Pair;
public class OrderItem extends Entity<Pair<Integer,Integer>> implements Serializable {

    private String titluCarte;
    private Integer id_exemplar;
    private Book book;
    private Order order;

    public OrderItem() {}

    public OrderItem(Pair<Integer,Integer> ID, String titluCarte, Integer id_exemplar, Book book, Order order) {
        this.ID = ID;
        this.titluCarte = titluCarte;
        this.id_exemplar = id_exemplar;
        this.book = book;
        this.order = order;
    }

    public OrderItem(String titluCarte, Integer id_exemplar, Book book, Order order) {
        this.titluCarte = titluCarte;
        this.id_exemplar = id_exemplar;
        this.book = book;
        this.order = order;
    }

    public String getTitluCarte() {
        return titluCarte;
    }

    public void setTitluCarte(String titluCarte) {
        this.titluCarte = titluCarte;
    }

    public Integer getId_exemplar() {
        return id_exemplar;
    }

    public void setId_exemplar(Integer id_exemplar) {
        this.id_exemplar = id_exemplar;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "ID=" + ID +
                ", titluCarte='" + titluCarte + '\'' +
                ", id_exemplar=" + id_exemplar +
                ", Carte=" + book +
                ", order=" + order +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return Objects.equals(titluCarte, orderItem.titluCarte) && Objects.equals(id_exemplar, orderItem.id_exemplar) && Objects.equals(book, orderItem.book) && Objects.equals(order, orderItem.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titluCarte, id_exemplar, book, order);
    }
}


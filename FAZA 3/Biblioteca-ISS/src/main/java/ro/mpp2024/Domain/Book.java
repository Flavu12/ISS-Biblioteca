package ro.mpp2024.Domain;

import java.util.Objects;

public class Book extends Entity<Integer> {

    private String titlu;
    private String autor;
    private int id_exemplar;
    private Boolean disponibilitate;

    public Book(){}

    public Book(Integer ID,String titlu, String autor, int id_exemplar, boolean disponibilitate) {
        this.ID = ID;
        this.titlu = titlu;
        this.autor = autor;
        this.id_exemplar = id_exemplar;
        this.disponibilitate = disponibilitate;
    }

    public Book(String titlu, String autor, int id_exemplar, boolean disponibilitate) {
        this.titlu = titlu;
        this.autor = autor;
        this.id_exemplar = id_exemplar;
        this.disponibilitate = disponibilitate;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu_nou) {
        this.titlu = titlu_nou;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor_nou) {
        this.autor = autor;
    }

    public Boolean getDisponibilitate() {
        return disponibilitate;
    }

    public void setDisponibilitate(Boolean disponibilitate_noua) {
        this.disponibilitate = disponibilitate_noua;
    }

    public int getId_exemplar() {
        return id_exemplar;
    }

    public void setId_exemplar(int id_exemplar) {
        this.id_exemplar = id_exemplar;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id " + ID + '\'' +
                "titlu='" + titlu + '\'' +
                "autor='" + autor + '\'' +
                "id_exemplar='" + id_exemplar+ '\'' +
                "disponibilitate='" + disponibilitate+ '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id_exemplar == book.id_exemplar && Objects.equals(titlu, book.titlu) && Objects.equals(autor, book.autor) && Objects.equals(disponibilitate, book.disponibilitate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titlu, autor, id_exemplar, disponibilitate);
    }
}

package ro.mpp2024.Repository;

import ro.mpp2024.Domain.Book;
import ro.mpp2024.Domain.validators.Validator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

public class BookDBRepository implements IBookRepository{

    private JdbcUtils jdbcUtils;
    private Validator<Book> validator;

    public BookDBRepository(Properties properties, Validator<Book> validator) {
        this.validator = validator;
        jdbcUtils = new JdbcUtils(properties);
    }

    @Override
    public void save(Book elem) {
        validator.validate(elem);
        try(Connection connection = jdbcUtils.getConnection();
            PreparedStatement insertStmt = connection
                    .prepareStatement("insert into books(titlu, autor, id_exemplar,disponibilitate) values (?,?,?,?)")) {
            insertStmt.setString(1, elem.getTitlu());
            insertStmt.setString(2, elem.getAutor());
            insertStmt.setInt(3,elem.getId_exemplar() );
            insertStmt.setBoolean(4, elem.getDisponibilitate());
            insertStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer ID) {
        try(Connection connection = jdbcUtils.getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from books where id=?")) {
            preparedStatement.setInt(1, ID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Book elem) {
        validator.validate(elem);
        try(Connection connection = jdbcUtils.getConnection();
            PreparedStatement updateStmt = connection
                    .prepareStatement("update books set titlu=?, autor=?, id_exemplar=?, disponibilitate=? where id=?")) {
            updateStmt.setString(1, elem.getTitlu());
            updateStmt.setString(2, elem.getAutor());
            updateStmt.setInt(3, elem.getId_exemplar());
            updateStmt.setBoolean(4, elem.getDisponibilitate());
            // Verificăm dacă ID-ul este nenul
            if (elem.getID() != null) {
                updateStmt.setInt(5, elem.getID());
            } else {
                // Afisăm un mesaj de eroare sau aruncăm o excepție în caz că ID-ul este null
                throw new IllegalArgumentException("ID-ul cărții este null. Nu poate fi actualizată fără un ID valid.");
            }
            updateStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Book find(Integer ID) {
        try(Connection connection = jdbcUtils.getConnection();
            PreparedStatement findStmt = connection.prepareStatement("select * from books where id=?")) {
            findStmt.setInt(1, ID);
            ResultSet resultSet = findStmt.executeQuery();
            if(resultSet.next() == false)
                return null;
            Integer id = resultSet.getInt("id");
            String titlu = resultSet.getString("titlu");
            String autor = resultSet.getString("autor");
            Integer id_exemplar = resultSet.getInt("id_exemplar");
            Boolean disponibilitate = resultSet.getBoolean("disponibilitate");
            Book book = new Book(id, titlu, autor,id_exemplar, disponibilitate);
            return book;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Collection<Book> findAll() {
        Collection<Book> books = new ArrayList<>();
        try(Connection connection = jdbcUtils.getConnection();
            PreparedStatement findStmt = connection
                    .prepareStatement("select * from books")) {
            ResultSet resultSet = findStmt.executeQuery();
            while(resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String titlu = resultSet.getString("titlu");
                String autor = resultSet.getString("autor");
                Integer id_exemplar = resultSet.getInt("id_exemplar");
                Boolean disponibilitate = resultSet.getBoolean("disponibilitate");
                Book book = new Book(id, titlu, autor,id_exemplar, disponibilitate);
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  books;
    }
}

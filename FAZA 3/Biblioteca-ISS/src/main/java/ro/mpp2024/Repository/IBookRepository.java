package ro.mpp2024.Repository;

import ro.mpp2024.Domain.Book;

import java.util.Collection;

public interface IBookRepository extends Repository<Book, Integer> {
   Collection<Book> findAvailableBooks();
}
package ro.mpp2024.Repository;

import ro.mpp2024.Domain.Entity;

import java.util.Collection;

public interface Repository< T extends Entity<TID>, TID > {
    void save(T elem);
    void delete(TID ID);
    void update(T elem);
    T find(TID ID);
    Collection<T> findAll();
}

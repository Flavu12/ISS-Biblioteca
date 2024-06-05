package ro.mpp2024.Utils.observer;

import ro.mpp2024.Utils.events.Event;

public interface Observable<E extends Event> {
    void addObserver(Observer<E> observer);
    void removeObserver(Observer<E> observer);
    void notifyObservers(E t);
}

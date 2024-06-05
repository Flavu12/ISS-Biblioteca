package ro.mpp2024.Utils.observer;

import ro.mpp2024.Utils.events.Event;

public interface Observer<E extends Event> {
    void update(E event);
}

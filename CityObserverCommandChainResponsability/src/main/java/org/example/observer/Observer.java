package org.example.observer;

public interface Observer<T extends Subject> {
    void notifyObserver(T subject);
}

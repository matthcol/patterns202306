package org.example.observer;

import java.util.LinkedList;
import java.util.List;

public abstract class Subject {
    // NB: a Set is possible too
    private List<Observer> observers;

    public Subject() {
        observers = new LinkedList<>();
    }

    public void register(Observer observer) {
        observers.add(observer);
    }

    public void unregister(Observer observer) {
        observers.remove(observer);
    }

    protected void notifyAllObservers() {
        // functional mode
        observers.forEach(Observer::notifyObserver);
        // imperative mode
//        for (var observer: observers) {
//            observer.notifyObserver();
//        }
    }
}

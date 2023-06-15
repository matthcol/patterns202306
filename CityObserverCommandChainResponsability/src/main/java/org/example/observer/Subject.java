package org.example.observer;

import java.util.LinkedList;
import java.util.List;

public abstract class Subject<T extends Subject> {
    // NB: a Set is possible too
    private List<Observer<T>> observers;
    private Class<T> subjectConcreteClass;

    protected Subject(Class<T> subjectConcreteClass) {
        this.subjectConcreteClass = subjectConcreteClass;
        this.observers = new LinkedList<>();
    }

    public void register(Observer<T> observer) {
        observers.add(observer);
    }

    public void unregister(Observer<T> observer) {
        observers.remove(observer);
    }

    protected void notifyAllObservers() {
        observers.forEach(observer -> observer.notifyObserver(
                subjectConcreteClass.cast(this)
        ));
    }
}

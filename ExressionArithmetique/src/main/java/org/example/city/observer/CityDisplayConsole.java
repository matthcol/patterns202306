package org.example.city.observer;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.example.city.City;
import org.example.observer.Observer;

@RequiredArgsConstructor(staticName = "of")
public class CityDisplayConsole implements Observer {
    @NonNull
    private City city;
    public void display() {
        System.out.println("City: " + city);
    }

    @Override
    public void notifyObserver() {
        display();
    }
}

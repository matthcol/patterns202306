package org.example.city.observer;


import org.example.city.City;
import org.example.observer.Observer;

public class CityDisplayConsole implements Observer<City> {

    public void display(City city) {
        System.out.println("City: " + city);
    }

    @Override
    public void notifyObserver(City subject) {
        display(subject);
    }
}

package org.example.city;

import lombok.*;
import org.example.observer.Subject;

@Builder
@Getter
@ToString
public class City extends Subject<City> {
    private String name;
    private int population;
    private String region;

    public City() {
        super(City.class);
    }

    public City(String name, int population, String region) {
        this();
        this.name = name;
        this.population = population;
        this.region = region;
    }

    public void setName(String name) {
        this.name = name;
        notifyAllObservers();
    }

    public void setPopulation(int population) {
        this.population = population;
        notifyAllObservers();
    }

    public void setRegion(String region) {
        this.region = region;
        notifyAllObservers();
    }

    public void update(City cityTemp) {
        this.name = cityTemp.name;
        this.population = cityTemp.population;
        this.region = cityTemp.region;
        notifyAllObservers();
    }
}

package org.example.city;

import lombok.*;
import org.example.observer.Subject;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class City extends Subject {
    private String name;
    private int population;
    private String region;

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
}

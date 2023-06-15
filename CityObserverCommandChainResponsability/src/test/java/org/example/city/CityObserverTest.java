package org.example.city;

import org.example.city.observer.CityDisplayConsole;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CityObserverTest {
    @Test
    void cityAndObservers(){
        // subjects
        City city = City.builder()
                .name("Toulouse")
                .population(470000)
                .region("Occitanie")
                .build();
        City city2 = City.builder()
                .name("Marseille")
                .population(800000)
                .region("Provence Alpes Côte d'Azur")
                .build();
        // observer
        CityDisplayConsole cityDisplayConsole = new CityDisplayConsole();
        // first display
        cityDisplayConsole.display(city);
        cityDisplayConsole.display(city2);
        // register for future modifications
        city.register(cityDisplayConsole);
        city2.register(cityDisplayConsole);
        // modify city
        for (int i = 0; i < 5; i++){
            city.setPopulation(city.getPopulation() + 1);
            city2.setPopulation(city.getPopulation() + 5);
        }
    }

}
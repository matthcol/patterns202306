package org.example.city;

import org.example.city.observer.CityDisplayConsole;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CityObserverTest {
    @Test
    void cityAndObservers(){
        City city = City.builder()
                .name("Toulouse")
                .population(470000)
                .region("Occitanie")
                .build();
        CityDisplayConsole cityDisplayConsole = CityDisplayConsole.of(city);
        // first display
        cityDisplayConsole.display();
        // register for future modifications
        city.register(cityDisplayConsole);
        // modify city
        for (int i = 0; i < 10; i++){
            city.setPopulation(city.getPopulation() + 1);
        }
    }

}
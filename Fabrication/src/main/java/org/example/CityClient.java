package org.example;

import org.example.model.AbstractCity;
import org.example.model.factory.CityFactory;

import java.util.stream.Stream;

public class CityClient {
    public CityClient(CityFactory cityFactory) {
        this.cityFactory = cityFactory;
    }

    private CityFactory cityFactory;

    public void run() {
        AbstractCity city1 = cityFactory.getCity();;
        AbstractCity city2 = cityFactory.getCity("Toulouse");
        AbstractCity city3 = cityFactory.getCity("Toulouse", 470000, "Occitanie");;
        Stream.of(city1, city2, city3)
                .forEach(System.out::println);
    }
}

package org.example.model.factory;

import org.example.model.AbstractCity;
import org.example.model.FrenchCity;

public class FrenchCityFactory implements CityFactory {
    @Override
    public AbstractCity getCity() {
        return new FrenchCity();
    }

    @Override
    public AbstractCity getCity(String name) {
       return FrenchCity.builder()
               .name(name)
               .build();
    }

    @Override
    public AbstractCity getCity(String name, int population, String region) {
        return FrenchCity.builder()
                .name(name)
                .population(population)
                .region(region)
                .build();
    }
}

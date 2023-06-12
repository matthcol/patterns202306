package org.example.zenmodel.factory;

import org.example.model.AbstractCity;
import org.example.model.factory.CityFactory;
import org.example.zenmodel.ZenCity;

public class ZenCityFactory implements CityFactory {
    @Override
    public AbstractCity getCity() {
        return ZenCity.builder().build();
    }

    @Override
    public AbstractCity getCity(String name) {
        return ZenCity.builder()
                .name(name)
                .build();
    }

    @Override
    public AbstractCity getCity(String name, int population, String region) {
        return ZenCity.builder()
                .name(name)
                .population(population)
                .region(region)
                .zenitude(100)
                .build();
    }
}

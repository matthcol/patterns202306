package org.example.model.factory;

import org.example.model.AbstractCity;

public interface CityFactory {
    AbstractCity getCity();
    AbstractCity getCity(String name);

    AbstractCity getCity(String name, int population, String region);

}

package org.example.model.factory;

import org.example.frenchmodel.factory.FrenchCityFactory;
import org.example.model.AbstractCity;

public interface CityFactory {
    static CityFactory getDefaultFactory(){
        return new FrenchCityFactory();
    }
    AbstractCity getCity();
    AbstractCity getCity(String name);

    AbstractCity getCity(String name, int population, String region);

}

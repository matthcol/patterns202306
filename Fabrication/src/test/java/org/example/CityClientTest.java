package org.example;

import org.example.model.factory.CityFactory;
import org.example.model.factory.FrenchCityFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CityClientTest {

    @Test
    void testRunWithFrenchCityFactory() {
        CityFactory cityFactory = new FrenchCityFactory();
        CityClient cityClient = new CityClient(cityFactory);
        cityClient.run();
    }

}
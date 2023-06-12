package org.example;

import org.example.model.factory.CityFactory;
import org.example.frenchmodel.factory.FrenchCityFactory;
import org.junit.jupiter.api.Test;

class CityClientTest {

    @Test
    void testRunWithFrenchCityFactory() {
        CityFactory cityFactory = new FrenchCityFactory();
        CityClient cityClient = new CityClient(cityFactory);
        cityClient.run();
    }

}
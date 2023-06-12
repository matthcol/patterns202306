package org.example;

import org.example.model.factory.CityFactory;
import org.example.frenchmodel.factory.FrenchCityFactory;
import org.example.zenmodel.factory.ZenCityFactory;
import org.junit.jupiter.api.Test;

class CityClientTest {

    @Test
    void testRunWithFrenchCityFactory() {
        CityFactory cityFactory = new FrenchCityFactory();
        CityClient cityClient = new CityClient(cityFactory);
        cityClient.run();
    }

    @Test
    void testRunWithZenCityFactory() {
        CityFactory cityFactory = new ZenCityFactory();
        CityClient cityClient = new CityClient(cityFactory);
        cityClient.run();
    }

    @Test
    void testRunWithDefaultCityFactory() {
        CityFactory cityFactory = CityFactory.getDefaultFactory();
        CityClient cityClient = new CityClient(cityFactory);
        cityClient.run();
    }

}
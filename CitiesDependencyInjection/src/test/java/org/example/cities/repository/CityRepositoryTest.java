package org.example.cities.repository;

import org.example.cities.entity.City;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CityRepositoryTest {

    @Autowired
    CityRepository cityRepository;

    @Test
    void testSave(){
        City city = City.builder()
                .name("Toulouse")
                .population(470000)
                .build();
        cityRepository.save(city);
    }
}
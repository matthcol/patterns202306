package org.example.cities.controller;

import org.example.cities.entity.City;
import org.example.cities.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cities")
public class CityController {

    @Autowired
    private CityRepository cityRepository;

    @GetMapping
    public List<City> getAll(){
        return cityRepository.findAll();
    }

    @PostMapping
    public City add(@RequestBody City city){
        cityRepository.saveAndFlush(city);
        return city;
    }

}

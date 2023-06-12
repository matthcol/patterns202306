package design;


import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.example.City;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class CreateObjects {


    @Test
    void buildListConstructor() {
        List<String> cities = new ArrayList<>();
        Collections.addAll(cities, "Toulouse", "Pau", "Marseille");
        System.out.println(cities);
    }

    @Test
    void builListFactory() {
        List<String> cities = List.of("Toulouse", "Pau", "Marseille");
        System.out.println(cities);
        System.out.println(cities.getClass());
    }

    @Test
    void concatenateStrings() {
        String city = "Toulouse";
        int population = 470000;
        String region = "Occitanie";
        String texte = city + " a " + population + " habitants, region " + region;
        System.out.println(texte);
    }

    @Test
    void concatenateStringsWithBuilder(){
        String city = "Toulouse";
        int population = 470000;
        String region = "Occitanie";
        String texte = new StringBuilder(city)
                .append( " a ")
                .append(population)
                .append(" habitants, region " )
                .append(region)
                .toString();
        System.out.println(texte);
    }

//    @Test
//    void buildCity() {
//        City city1 = new City();
//        City city2 = new City("Toulouse");
//        City city3 = new City("Toulouse", 470000, "Occitanie");
//        // create with name + population ?
//        City city4 = new City("Toulouse", 470000, null);
////        City city5 = new City("Toulouse", 470000);  // constructor does not exist
//        Stream.of(city1, city2, city3, city4)
//                .forEach(System.out::println);
//    }

    @Test
    void buildCityWithBuilder() {
        City city1 = City.builder().build();
        City city2 = City.builder()
                .name("Toulouse")
                .build();
        City city3 = City.builder()
                .name("Toulouse")
                .population(470000)
                .region("Occitanie")
                .build();
        City city4 = City.builder()
                .name("Toulouse")
                .population(470000)
                .build();
        City city5 = City.builder()
                .name("Toulouse")
                .region("Occitanie")
                .build();
        City city6 = City.builder()
                .name("Toulouse")
                .population(470000)
                .region("Occitanie")
                .gpsCoordinates("ici c'est Toulouse")
                .build();
        Stream.of(city1, city2, city3, city4, city5, city6)
                .forEach(System.out::println);
    }

}

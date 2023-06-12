package design;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.*;

import org.example.City;

import java.util.stream.Stream;

public class ValidateCities {
    static Validator validator;
    @BeforeAll
    static void initValidator(){
        validator = Validation
                .buildDefaultValidatorFactory()
                .getValidator();
    }

    private static Stream<City> validCities() {
        return Stream.of(
                City.builder()
                        .name("Toulouse")
                        .population(470000)
                        .region("Occitanie")
                        .gpsCoordinates("ici c'est Toulouse")
                        .build(),
                City.builder()
                        .name("Toulouse")
                        .gpsCoordinates("ici c'est Toulouse")
                        .build()
        );
    }
    @ParameterizedTest
    @MethodSource("validCities")
    void validateCity(City city) {
        var constraintsViolations = validator.validate(city);
        assertEquals(0, constraintsViolations.size());
    }

    private static Stream<City> invalidCities() {
        return Stream.of(
                // no name
                City.builder()
                        .population(470000)
                        .region("Occitanie")
                        .gpsCoordinates("ici c'est Toulouse")
                        .build(),
                // blank name
                City.builder()
                        .name("")
                        .population(470000)
                        .region("Occitanie")
                        .gpsCoordinates("ici c'est Toulouse")
                        .build(),
                // 0 population
                City.builder()
                        .name("Toulouse")
                        .population(0)
                        .region("Occitanie")
                        .gpsCoordinates("ici c'est Toulouse")
                        .build(),
                // negative population
                City.builder()
                        .name("Toulouse")
                        .population(-470000)
                        .region("Occitanie")
                        .gpsCoordinates("ici c'est Toulouse")
                        .build(),
                // no Gps
                City.builder()
                        .name("Toulouse")
                        .population(470000)
                        .region("Occitanie")
                        .build(),
                // blank gps
                City.builder()
                        .name("Toulouse")
                        .population(470000)
                        .region("Occitanie")
                        .gpsCoordinates("")
                        .build()
        );
    }
    @ParameterizedTest
    @MethodSource("invalidCities")
    void invalidateCities(City city){
        var constraintsViolations = validator.validate(city);
        System.out.println(constraintsViolations);
        assertTrue(constraintsViolations.size() > 0);
    }
}

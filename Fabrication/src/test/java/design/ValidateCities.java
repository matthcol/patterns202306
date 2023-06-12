package design;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.*;

import org.example.model.FrenchCity;

import java.util.stream.Stream;

public class ValidateCities {
    static Validator validator;
    @BeforeAll
    static void initValidator(){
        validator = Validation
                .buildDefaultValidatorFactory()
                .getValidator();
    }

    private static Stream<FrenchCity> validCities() {
        return Stream.of(
                FrenchCity.builder()
                        .name("Toulouse")
                        .population(470000)
                        .region("Occitanie")
                        .gpsCoordinates("ici c'est Toulouse")
                        .build(),
                FrenchCity.builder()
                        .name("Toulouse")
                        .gpsCoordinates("ici c'est Toulouse")
                        .build()
        );
    }
    @ParameterizedTest
    @MethodSource("validCities")
    void validateCity(FrenchCity city) {
        var constraintsViolations = validator.validate(city);
        assertEquals(0, constraintsViolations.size());
    }

    private static Stream<FrenchCity> invalidCities() {
        return Stream.of(
                // no name
                FrenchCity.builder()
                        .population(470000)
                        .region("Occitanie")
                        .gpsCoordinates("ici c'est Toulouse")
                        .build(),
                // blank name
                FrenchCity.builder()
                        .name("")
                        .population(470000)
                        .region("Occitanie")
                        .gpsCoordinates("ici c'est Toulouse")
                        .build(),
                // 0 population
                FrenchCity.builder()
                        .name("Toulouse")
                        .population(0)
                        .region("Occitanie")
                        .gpsCoordinates("ici c'est Toulouse")
                        .build(),
                // negative population
                FrenchCity.builder()
                        .name("Toulouse")
                        .population(-470000)
                        .region("Occitanie")
                        .gpsCoordinates("ici c'est Toulouse")
                        .build(),
                // no Gps
                FrenchCity.builder()
                        .name("Toulouse")
                        .population(470000)
                        .region("Occitanie")
                        .build(),
                // blank gps
                FrenchCity.builder()
                        .name("Toulouse")
                        .population(470000)
                        .region("Occitanie")
                        .gpsCoordinates("")
                        .build()
        );
    }
    @ParameterizedTest
    @MethodSource("invalidCities")
    void invalidateCities(FrenchCity city){
        var constraintsViolations = validator.validate(city);
        System.out.println(constraintsViolations);
        assertTrue(constraintsViolations.size() > 0);
    }
}

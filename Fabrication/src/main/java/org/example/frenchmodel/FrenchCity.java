package org.example.frenchmodel;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.example.model.AbstractCity;

import java.util.ArrayList;
import java.util.List;

@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class FrenchCity implements AbstractCity {
//    @NonNull
//    @NotNull
    @NotBlank(message = "name must not be blank")
    String name;

    @Min(value = 1, message = "population must be greater or equal to 1")
    Integer population;

    String region;

//    @NonNull
    @NotBlank(message = "gps coordinates must not be blank")
    String gpsCoordinates;

    @Builder.Default
    List<String> stadiums = new ArrayList<>();

//    public City() {
//    }
//
//    public City(String name) {
//        this.name = name;
//    }
//
//    public City(String name, int population, String region) {
//        this.name = name;
//        this.population = population;
//        this.region = region;
//    }
}

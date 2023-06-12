package org.example.zenmodel;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.model.AbstractCity;

@Builder
@Getter
@ToString
public class ZenCity implements AbstractCity {
    private String name;

    private Integer population;

    private String region;

    private Integer zenitude;
}

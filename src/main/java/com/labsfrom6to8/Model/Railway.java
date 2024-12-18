package com.labsfrom6to8.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Railway {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("length_km")
    private Double lengthKm;

    @JsonProperty("country")
    private String country;

    @JsonProperty("is_operational")
    private Boolean isOperational;
}

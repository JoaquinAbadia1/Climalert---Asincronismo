package ar.edu.utn.frba.ddsi.climalert.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrentDTO {

    @JsonProperty("temp_c")
    private Double tempC;

    private Integer humidity;

    @JsonProperty("feelslike_c")
    private Double feelslikeC;

    @JsonProperty("wind_kph")
    private Double windKph;

    @JsonProperty("last_updated")
    private String lastUpdated;

    private ConditionDTO condition;
}
package ar.edu.utn.frba.ddsi.climalert.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherResponseDTO {
    private LocationDTO location;
    private CurrentDTO current;
}
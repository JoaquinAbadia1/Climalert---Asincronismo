package ar.edu.utn.frba.ddsi.climalert.services;

import ar.edu.utn.frba.ddsi.climalert.dto.CurrentDTO;
import ar.edu.utn.frba.ddsi.climalert.dto.LocationDTO;
import ar.edu.utn.frba.ddsi.climalert.dto.WeatherResponseDTO;
import ar.edu.utn.frba.ddsi.climalert.models.entities.DatoClimatico;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class WeatherApiClient {

    private final RestClient restClient;
    private final String apiKey;
    private final String location;

    public WeatherApiClient(
            @Value("${weatherapi.base-url}") String baseUrl,
            @Value("${weatherapi.key}") String apiKey,
            @Value("${weatherapi.location}") String location) {
        this.apiKey = apiKey;
        this.location = location;
        this.restClient = RestClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    // Llama a WeatherAPI y devuelve un DatoClimatico ya mapeado
    public DatoClimatico obtenerClimaActual() {
        WeatherResponseDTO respuesta = this.restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/current.json")
                        .queryParam("key", this.apiKey)
                        .queryParam("q", this.location)
                        .build())
                .retrieve()
                .body(WeatherResponseDTO.class);

        return this.mapearADatoClimatico(respuesta);
    }

    // Traduce el DTO (mundo externo) a nuestra entidad (mundo interno)
    private DatoClimatico mapearADatoClimatico(WeatherResponseDTO respuesta) {
        LocationDTO loc = respuesta.getLocation();
        CurrentDTO actual = respuesta.getCurrent();
        return new DatoClimatico(
                loc.getName(),
                actual.getTempC(),
                actual.getHumidity(),
                actual.getFeelslikeC(),
                actual.getWindKph(),
                actual.getCondition().getText()
        );
    }
}
package ar.edu.utn.frba.ddsi.climalert.services;

import ar.edu.utn.frba.ddsi.climalert.models.entities.DatoClimatico;
import ar.edu.utn.frba.ddsi.climalert.models.repositories.DatoClimaticoRepository;
import org.springframework.stereotype.Service;

@Service
public class ServicioMonitoreo {

    private final WeatherApiClient weatherApiClient;
    private final DatoClimaticoRepository repositorio;

    public ServicioMonitoreo(WeatherApiClient weatherApiClient,
                             DatoClimaticoRepository repositorio) {
        this.weatherApiClient = weatherApiClient;
        this.repositorio = repositorio;
    }

    public void registrarClimaActual() {
        DatoClimatico dato = this.weatherApiClient.obtenerClimaActual();
        this.repositorio.guardar(dato);
        System.out.println("[MONITOREO] Dato guardado: " + dato.getUbicacion()
                + " | " + dato.getTemperatura() + "°C | humedad " + dato.getHumedad() + "%");
    }
}
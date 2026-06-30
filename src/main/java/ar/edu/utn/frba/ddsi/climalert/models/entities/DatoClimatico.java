package ar.edu.utn.frba.ddsi.climalert.models.entities;

import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class DatoClimatico {

    private final String ubicacion;
    private final Double temperatura;
    private final Integer humedad;
    private final Double sensacionTermica;
    private final Double viento;
    private final String descripcion;
    private final LocalDateTime fechaRegistro;

    public DatoClimatico(String ubicacion, Double temperatura, Integer humedad,
                         Double sensacionTermica, Double viento, String descripcion) {
        this.ubicacion = ubicacion;
        this.temperatura = temperatura;
        this.humedad = humedad;
        this.sensacionTermica = sensacionTermica;
        this.viento = viento;
        this.descripcion = descripcion;
        this.fechaRegistro = LocalDateTime.now();
    }

    // Método de negocio: ¿estas condiciones son una alerta?
    public boolean esCritico() {
        return this.temperatura > 35 && this.humedad > 60;
    }
}
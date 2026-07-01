package ar.edu.utn.frba.ddsi.climalert.services;

import ar.edu.utn.frba.ddsi.climalert.models.entities.DatoClimatico;
import ar.edu.utn.frba.ddsi.climalert.models.repositories.DatoClimaticoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServicioAlertas {

    private final DatoClimaticoRepository repositorio;
    private final ServicioNotificacion servicioNotificacion;

    public ServicioAlertas(DatoClimaticoRepository repositorio,
                           ServicioNotificacion servicioNotificacion) {
        this.repositorio = repositorio;
        this.servicioNotificacion = servicioNotificacion;
    }

    public void analizarUltimoDato() {
        Optional<DatoClimatico> ultimo = this.repositorio.obtenerUltimo();

        if (ultimo.isEmpty()) {
            System.out.println("[ALERTAS] Todavía no hay datos para analizar.");
            return;
        }

        DatoClimatico dato = ultimo.get();
        if (dato.esCritico()) {
            System.out.println("[ALERTAS] ¡Condición crítica detectada! Enviando alerta...");
            this.servicioNotificacion.notificarAlerta(dato);
        } else {
            System.out.println("[ALERTAS] Clima normal. Sin alertas.");
        }
    }
}
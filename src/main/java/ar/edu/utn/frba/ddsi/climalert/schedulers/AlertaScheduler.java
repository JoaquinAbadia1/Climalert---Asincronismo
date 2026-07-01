package ar.edu.utn.frba.ddsi.climalert.schedulers;

import ar.edu.utn.frba.ddsi.climalert.services.ServicioAlertas;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AlertaScheduler {

    private final ServicioAlertas servicioAlertas;

    public AlertaScheduler(ServicioAlertas servicioAlertas) {
        this.servicioAlertas = servicioAlertas;
    }

    // Cada 1 minuto (60 * 1000 = 60000 milisegundos)
    @Scheduled(fixedRate = 60000)
    public void analizarClima() {
        this.servicioAlertas.analizarUltimoDato();
    }
}
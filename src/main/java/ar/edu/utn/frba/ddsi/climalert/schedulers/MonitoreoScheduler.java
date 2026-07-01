package ar.edu.utn.frba.ddsi.climalert.schedulers;

import ar.edu.utn.frba.ddsi.climalert.services.ServicioMonitoreo;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MonitoreoScheduler {

    private final ServicioMonitoreo servicioMonitoreo;

    public MonitoreoScheduler(ServicioMonitoreo servicioMonitoreo) {
        this.servicioMonitoreo = servicioMonitoreo;
    }

    // Cada 5 minutos (5 * 60 * 1000 = 300000 milisegundos)
    @Scheduled(fixedRate = 300000)
    public void monitorearClima() {
        this.servicioMonitoreo.registrarClimaActual();
    }
}
package ar.edu.utn.frba.ddsi.climalert.services;

import ar.edu.utn.frba.ddsi.climalert.models.entities.DatoClimatico;
import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;
import com.resend.services.emails.model.CreateEmailResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioNotificacion {

    private static final List<String> DESTINATARIOS = List.of(
            "admin@clima.com",
            "emergencias@clima.com",
            "meteorologia@clima.com"
    );

    private final Resend resend;

    public ServicioNotificacion(@Value("${resend.api-key}") String apiKey) {
        this.resend = new Resend(apiKey);
    }

    public void notificarAlerta(DatoClimatico dato) {
        CreateEmailOptions params = CreateEmailOptions.builder()
                .from("Climalert <onboarding@resend.dev>")
                .to(DESTINATARIOS)
                .subject("Alerta climática en " + dato.getUbicacion())
                .html(this.armarCuerpo(dato))
                .build();

        try {
            CreateEmailResponse response = this.resend.emails().send(params);
            System.out.println("[ALERTA] Correo enviado. ID: " + response.getId());
        } catch (ResendException e) {
            System.err.println("[ALERTA] Error al enviar el correo: " + e.getMessage());
        }
    }

    private String armarCuerpo(DatoClimatico dato) {
        return "<h2>Alerta climática detectada</h2>"
                + "<p><b>Ubicación:</b> " + dato.getUbicacion() + "</p>"
                + "<p><b>Temperatura:</b> " + dato.getTemperatura() + " °C</p>"
                + "<p><b>Humedad:</b> " + dato.getHumedad() + " %</p>"
                + "<p><b>Sensación térmica:</b> " + dato.getSensacionTermica() + " °C</p>"
                + "<p><b>Viento:</b> " + dato.getViento() + " km/h</p>"
                + "<p><b>Condición:</b> " + dato.getDescripcion() + "</p>"
                + "<p><b>Registrado:</b> " + dato.getFechaRegistro() + "</p>";
    }
}
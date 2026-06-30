package ar.edu.utn.frba.ddsi.climalert.models.repositories;

import ar.edu.utn.frba.ddsi.climalert.models.entities.DatoClimatico;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class DatoClimaticoRepository {

    private final List<DatoClimatico> datos = new ArrayList<>();

    public void guardar(DatoClimatico dato) {
        this.datos.add(dato);
    }

    public List<DatoClimatico> obtenerTodos() {
        return this.datos;
    }

    public Optional<DatoClimatico> obtenerUltimo() {
        if (this.datos.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(this.datos.get(this.datos.size() - 1));
    }
}
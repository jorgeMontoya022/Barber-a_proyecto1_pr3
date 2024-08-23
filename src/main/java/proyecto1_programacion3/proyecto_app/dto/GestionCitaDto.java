package proyecto1_programacion3.proyecto_app.dto;

import proyecto1_programacion3.proyecto_app.model.Barbero;
import proyecto1_programacion3.proyecto_app.model.Cliente;
import proyecto1_programacion3.proyecto_app.model.GestionCita;
import proyecto1_programacion3.proyecto_app.model.TipoServicio;
import proyecto1_programacion3.proyecto_app.builder.BarberoBuilder;
import proyecto1_programacion3.proyecto_app.builder.ClienteBuilder;

import java.time.LocalDate;
import java.time.LocalTime;

public record GestionCitaDto(
        String nombreCliente,
        String telefonoCliente,
        String nombreBarbero,
        String fechaCita,
        String horaCita,
        String valor,
        String tipoCita
) {
    /**
     * Convierte un objeto `GestionCita` en un objeto `GestionCitaDto`.
     *
     * @param gestionCita El objeto `GestionCita` a convertir.
     * @return Un nuevo objeto `GestionCitaDto` con los datos de `gestionCita`.
     */
    public static GestionCitaDto fromGestionCita(GestionCita gestionCita) {
        return new GestionCitaDto(
                gestionCita.getCliente().getNombre(),
                gestionCita.getCliente().getTelefono(),
                gestionCita.getBarbero().getNombre(),
                gestionCita.getFechaCita().toString(),
                gestionCita.getHoraCita().toString(),
                String.valueOf(gestionCita.getValorCita()),
                gestionCita.getTipoServicio().name()
        );
    }

    /**
     * Convierte un objeto `GestionCitaDto` en un objeto `GestionCita`.
     *
     * @return Un nuevo objeto `GestionCita` con los datos de `GestionCitaDto`.
     */
    public GestionCita toGestionCita() {
        // Construye un objeto `Cliente` a partir de los datos de `GestionCitaDto`.
        Cliente cliente = new ClienteBuilder()
                .nombre(this.nombreCliente)
                .telefono(this.telefonoCliente)
                .build();

        // Construye un objeto `Barbero` a partir de los datos de `GestionCitaDto`.
        Barbero barbero = new BarberoBuilder()
                .nombre(this.nombreCliente())
                .build();

        // Crea y devuelve un nuevo objeto `GestionCita` con los datos construidos.
        return new GestionCita(barbero, cliente, TipoServicio.valueOf(this.tipoCita), LocalDate.parse(this.fechaCita()), LocalTime.parse(this.horaCita()), Double.parseDouble(this.valor));
    }
}

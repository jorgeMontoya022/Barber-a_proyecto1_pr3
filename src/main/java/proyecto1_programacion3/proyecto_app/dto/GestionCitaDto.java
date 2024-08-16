package proyecto1_programacion3.proyecto_app.dto;

import proyecto1_programacion3.proyecto_app.model.Barbero;
import proyecto1_programacion3.proyecto_app.model.Cliente;
import proyecto1_programacion3.proyecto_app.model.GestionCita;
import proyecto1_programacion3.proyecto_app.model.TipoServicio;
import proyecto1_programacion3.proyecto_app.model.builder.BarberoBuilder;
import proyecto1_programacion3.proyecto_app.model.builder.ClienteBuilder;

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

    public GestionCita toGestionCita() {
       Cliente cliente = new ClienteBuilder()
               .nombre(this.nombreCliente)
               .telefono(this.telefonoCliente)
               .build();

        Barbero barbero = new BarberoBuilder()
                .nombre(this.nombreCliente())
                .build();

        return new GestionCita(barbero, cliente, TipoServicio.valueOf(this.tipoCita), LocalDate.parse(this.fechaCita()), LocalTime.parse(this.horaCita()), Double.parseDouble(this.valor));
    }
}

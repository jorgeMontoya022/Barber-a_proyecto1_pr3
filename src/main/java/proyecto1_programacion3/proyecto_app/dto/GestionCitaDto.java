package proyecto1_programacion3.proyecto_app.dto;

public record GestionCitaDto(
        String nombreCliente,
        String telefonoCliente,
        String nombreBarbero,
        String fechaCita,
        String horaCita,
        String valor,
        String tipoCita
) {
}

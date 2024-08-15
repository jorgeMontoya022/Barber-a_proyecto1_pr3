package proyecto1_programacion3.proyecto_app.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class GestionCita {
    private Barbero barbero;
    private Cliente cliente;
    private TipoServicio tipoServicio;
    private LocalDate fechaCita;
    private LocalTime horaCita;
    private double valorCita;


    public GestionCita(Barbero barbero, Cliente cliente, TipoServicio tipoServicio,LocalDate fechaCita,LocalTime horaCita, double valorCita ) {
        this.barbero = barbero;
        this.cliente = cliente;
        this.tipoServicio = tipoServicio;
        this.fechaCita = fechaCita;
        this.horaCita = horaCita;
        this.valorCita = valorCita;
    }



    public GestionCita() {
    }

    public Barbero getBarbero() {
        return barbero;
    }

    public void setBarbero(Barbero barbero) {
        this.barbero = barbero;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public TipoServicio getTipoServicio() {
        return tipoServicio;
    }

    public LocalDate getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(LocalDate fechaCita) {
        this.fechaCita = fechaCita;
    }

    public LocalTime getHoraCita() {
        return horaCita;
    }

    public void setHoraCita(LocalTime horaCita) {
        this.horaCita = horaCita;
    }

    public double getValorCita() {
        return valorCita;
    }

    public void setValorCita(double valorCita) {
        this.valorCita = valorCita;
    }

    public void setTipoServicio(TipoServicio tipoServicio) {
        this.tipoServicio = tipoServicio;
    }
}

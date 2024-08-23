package proyecto1_programacion3.proyecto_app.model;


public enum TipoServicio {
    CABELLO(15000),
    BARBA(10000),
    CEJAS(5000),
    MASCARILLA(20000);

    private final double valor;  // Valor asociado al tipo de servicio

    /**
     * Constructor del enum que establece el valor para el tipo de servicio.
     *
     * @param valor El costo asociado al tipo de servicio.
     */
    TipoServicio(double valor) {
        this.valor = valor;
    }


    public double getValor() {
        return valor;
    }
}

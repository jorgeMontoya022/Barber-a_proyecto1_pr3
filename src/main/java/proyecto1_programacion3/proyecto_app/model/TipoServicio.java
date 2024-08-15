package proyecto1_programacion3.proyecto_app.model;

public enum TipoServicio {
    CABELLO(15000),
    BARBA(10000),
    CEJAS(5000),
    MASCARILLA(20000);

    private final double valor;

    TipoServicio(int valor) {
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }

}

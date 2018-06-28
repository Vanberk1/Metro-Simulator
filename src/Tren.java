
public class Tren {
    private Estacion estacion;
    private int capacidadMaxima;
    private int pasajerosActuales;
    private boolean sentido;
    private int numeroDeTren;
    private static int cantidadTrenes;

    public Tren() {
        numeroDeTren = ++cantidadTrenes;
    }

    public Tren(Estacion estacion, int capacidadMaxima, int pasajerosActuales, boolean sentido) {
        this.estacion = estacion;
        this.capacidadMaxima = capacidadMaxima;
        this.pasajerosActuales = pasajerosActuales;
        this.sentido = sentido;
        this.numeroDeTren = ++cantidadTrenes;
    }

    public int getNumeroDeTren() { return numeroDeTren; }

    public Estacion getEstacion() { return estacion; }
    
    public int getPasajerosActuales() { return pasajerosActuales; }
    
    public int getCapacidadMaxima() { return capacidadMaxima; }
    
    public void setEstacion(Estacion nuevaEstacion) {
        this.estacion = nuevaEstacion;
    }
    public void setPasajerosActuales(int pasajeros) {
        this.pasajerosActuales = pasajeros;
    }

    public boolean Sentido() { return sentido; }

    public void CambiarSentido() { sentido = !sentido; }

}

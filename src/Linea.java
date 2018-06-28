import java.util.ArrayList;

public class Linea {
    private int nombre;
    private ArrayList<Estacion> estaciones;
    private ArrayList<Tren> trenes;

    public Linea(int nombre) {
        estaciones = new ArrayList<>();
        trenes = new ArrayList<>();
        this.nombre = nombre;
    }

    public int getNombre() { return nombre; }

    public void AgregarEstacion(Estacion estacion) {
        this.estaciones.add(estacion);
    }

    public void AgregarTren(Tren tren) {
        this.trenes.add(tren);
    }

    public ArrayList<Estacion> getEstaciones() { return estaciones; }

    public Estacion GetEstacion(int i) {
        return estaciones.get(i);
    }

    public ArrayList<Tren> getTrenes() { return trenes; }
}


public class Estacion {
    private String nombre;
    private Linea linea;
    private int esCombinacion = -1;
    private boolean hayTren;
    private int genteEsperando;
    private int capacidadMaxima;
    private int numeroVisitas = 0;

    public Estacion(String nombre, Linea linea, int capacidadMaxima, int genteEsperando, boolean hayTren) {
        this.nombre = nombre;
        this.linea = linea;
        this.capacidadMaxima = capacidadMaxima;
        this.genteEsperando = genteEsperando;
        this.hayTren = hayTren;
        this.numeroVisitas += genteEsperando;
    }

    public void EsCombinacion(int linea) { esCombinacion = linea; }

    public String getNombre() { return nombre; }
    
    public int getGenteEsperando() { return genteEsperando; }
    
    public int getCapacidadMaxima() { return capacidadMaxima; }
    
    public int getNumeroVisitas() { return numeroVisitas; }
    
    public Linea getLinea() { return linea; }
    
    public int getCombinacion() { return esCombinacion; }
    
    public void setGenteEsperando(int gente) {
        this.genteEsperando = gente;
    }
    
    public void AumentarVisitas(int visitas) {
        numeroVisitas += visitas;
    }
}

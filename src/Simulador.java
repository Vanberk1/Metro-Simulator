import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Simulador {
    private ArrayList<Linea> lineas;
    private static int pasajerosQueIngresaron = 0;
    private static int pasajerosTransportados = 0; 
    private static int numeroDeCiclos = 0;

    public Simulador(String ruta) throws FileNotFoundException, IOException {
        lineas = new ArrayList<>();
        BufferedReader buffer = new BufferedReader(new FileReader(ruta));
        String linea;
        String[] partes;
        String[] estaciones;
        String nombreEstacion;
        String datos;
        String datosEstacion = "";
        String datosTren = "";
        String[] aux;
        String[] aux2;
        String[] genteEst;
        String[] genteTren;
        String[] auxTrenes;
        int largoNombre;        
        int lineasAgregadas = 0;
        int genteEstMax;
        int genteEstMin;
        int genteTrenMax;
        int genteTrenMin;
        
        boolean sentidoTren;
        while((linea = buffer.readLine()) != null) {
            partes = linea.split(":");
            largoNombre = partes[0].length();
            lineas.add(new Linea(Integer.parseInt(""+partes[0].charAt(largoNombre - 2))));
            //System.out.println(nombre.charAt(largoNombre - 2));
            estaciones = partes[1].split(">");
            for(int i = 0; i < estaciones.length; i++) {
                aux = estaciones[i].split("_");
                nombreEstacion = aux[0];
                datos = aux[1];
                if(datos.contains("*")) {
                    aux2 = datos.split("\\*");
                    datosEstacion = aux2[0];
                    datosTren = aux2[1];
                    
                    genteEst = datosEstacion.split("/");
                    genteEstMax = Integer.parseInt(genteEst[0]);
                    genteEstMin = Integer.parseInt(genteEst[1]);
                    lineas.get(lineasAgregadas).AgregarEstacion(new Estacion(nombreEstacion, lineas.get(lineasAgregadas), genteEstMax, genteEstMin, true));
                    if(datosTren.contains("+") && datosTren.contains("-")) {
                        if(datosTren.charAt(0) == '+') {
                            datosTren = datosTren.substring(1, datosTren.length());
                            auxTrenes = datosTren.split("-");
                            
                            genteTren = auxTrenes[0].split("/");
                            genteTrenMax = Integer.parseInt(genteTren[0]);
                            genteTrenMin = Integer.parseInt(genteTren[1]);
                            lineas.get(lineasAgregadas).AgregarTren(new Tren(lineas.get(lineasAgregadas).GetEstacion(i), genteTrenMax, genteTrenMin, true));
                            
                            genteTren = auxTrenes[1].split("/");
                            genteTrenMax = Integer.parseInt(genteTren[0]);
                            genteTrenMin = Integer.parseInt(genteTren[1]);
                            lineas.get(lineasAgregadas).AgregarTren(new Tren(lineas.get(lineasAgregadas).GetEstacion(i), genteTrenMax, genteTrenMin, false));
                            
                        }
                        else {
                            datosTren = datosTren.substring(1, datosTren.length());
                            auxTrenes = datosTren.split("\\+");
                            
                            genteTren = auxTrenes[0].split("/");
                            genteTrenMax = Integer.parseInt(genteTren[0]);
                            genteTrenMin = Integer.parseInt(genteTren[1]);
                            lineas.get(lineasAgregadas).AgregarTren(new Tren(lineas.get(lineasAgregadas).GetEstacion(i), genteTrenMax, genteTrenMin, false));
                            
                            genteTren = auxTrenes[1].split("/");
                            genteTrenMax = Integer.parseInt(genteTren[0]);
                            genteTrenMin = Integer.parseInt(genteTren[1]);
                            lineas.get(lineasAgregadas).AgregarTren(new Tren(lineas.get(lineasAgregadas).GetEstacion(i), genteTrenMax, genteTrenMin, true));
                            
                        }
                        
                    }
                    else if(datosTren.contains("+")) {
                        sentidoTren = true;
                        datosTren = datosTren.substring(1, datosTren.length());
                        genteTren = datosTren.split("/");
                        genteTrenMax = Integer.parseInt(genteTren[0]);
                        genteTrenMin = Integer.parseInt(genteTren[1]);
                        lineas.get(lineasAgregadas).AgregarTren(new Tren(lineas.get(lineasAgregadas).GetEstacion(i), genteTrenMax, genteTrenMin, true));
                    }
                    else {
                        sentidoTren = false;
                        datosTren = datosTren.substring(1, datosTren.length());
                        genteTren = datosTren.split("/");
                        genteTrenMax = Integer.parseInt(genteTren[0]);
                        genteTrenMin = Integer.parseInt(genteTren[1]);
                        lineas.get(lineasAgregadas).AgregarTren(new Tren(lineas.get(lineasAgregadas).GetEstacion(i), genteTrenMax, genteTrenMin, false));
                    }
                    
                } 
                else {
                    genteEst = datos.split("/");
                    genteEstMax = Integer.parseInt(genteEst[0]);
                    genteEstMin = Integer.parseInt(genteEst[1]);
                    lineas.get(lineasAgregadas).AgregarEstacion(new Estacion(nombreEstacion, lineas.get(lineasAgregadas), genteEstMax, genteEstMin, false));
                }
                //System.out.println(genteEstMax + "\n" + datosTren);
            }
            lineasAgregadas++;
        }
        
        for(int i = 0; i < lineas.size(); i++) {
            for(int j = 0; j < lineas.get(i).getEstaciones().size(); j++) {
                pasajerosTransportados += lineas.get(i).GetEstacion(j).getGenteEsperando();
            }
        }
    }
    
    public ArrayList<Linea> getLineas() { return lineas; }
    
    public int getGenteQueIngreso() { return pasajerosQueIngresaron; }
    
    public int getGenteTransportada() { return pasajerosTransportados; }
    
    public int getNumeroDeCiclos() { return numeroDeCiclos; }
    
    public void NuevoCiclo(Simulador simulador) {
        numeroDeCiclos++;        
        simulador.IngresoDePersonas();
        simulador.MoverTrenes();     
        simulador.IngresoYSalidaDelTren();
        simulador.CorregirGente();
    }
    
    //// TERMINAR ////
    public void DefinirCombinaciones() {
        int numeroEstaciones1, numeroEstaciones2;
        Estacion estacion1, estacion2;
        for(int i = 0; i < lineas.size(); i++) {
            numeroEstaciones1 = lineas.get(i).getEstaciones().size();
            for(int j = i+1; j < lineas.size(); j++) {
                numeroEstaciones2 = lineas.get(j).getEstaciones().size();
                for(int n = 0; n < numeroEstaciones1; n++) {
                    estacion1 = lineas.get(i).GetEstacion(n);
                    for(int m = 0; m < numeroEstaciones2; m++) {
                        estacion2 = lineas.get(j).GetEstacion(m);
                        if(estacion1.getNombre().equals(estacion2.getNombre())) {
                            estacion1.EsCombinacion(estacion2.getLinea().getNombre());
                            estacion2.EsCombinacion(estacion1.getLinea().getNombre());
                        }
                    }
                }
            }
        }
    }

    public void MostrarLineas() {
        for(int i = 0; i < lineas.size(); ++i)
            System.out.println(lineas.get(i).getNombre());
    }

    
    public void IngresoDePersonas() {
        int largoE;
        Estacion auxE;
        int personasAleatorias;
        for(int i = 0; i < lineas.size(); i++) {
            largoE = lineas.get(i).getEstaciones().size();
            for(int j = 0; j < largoE; j++) {
                auxE = lineas.get(i).GetEstacion(j);
                personasAleatorias = (int)(Math.random() * 50);
                pasajerosQueIngresaron += personasAleatorias;
                pasajerosTransportados += personasAleatorias;
                auxE.AumentarVisitas(personasAleatorias);
                auxE.setGenteEsperando(auxE.getGenteEsperando() + personasAleatorias);
                if(auxE.getGenteEsperando() > auxE.getCapacidadMaxima()) {
                    auxE.setGenteEsperando(auxE.getCapacidadMaxima());
                } 
            }
        }
    }
    
    public void IngresoYSalidaDelTren() {
        int largoT;
        Tren auxT;
        int personasQueSalen;
        int personasCombinacion;
        int aux;
        for(int i = 0; i < lineas.size(); i++) {
            largoT = lineas.get(i).getTrenes().size();
            for(int j = 0; j < largoT; j++) {
                auxT = lineas.get(i).getTrenes().get(j);
                personasQueSalen = (int)(Math.random() * auxT.getPasajerosActuales());
                auxT.getEstacion().AumentarVisitas(personasQueSalen);
                // AGREAGAR IF COMBINACION  
                if(auxT.getEstacion().getCombinacion() != -1) {
                    personasCombinacion = personasQueSalen / 2;
                    for(int k = 0; k < lineas.size(); k++) {
                        if(lineas.get(k).getNombre() == auxT.getEstacion().getCombinacion()) {
                            for(int n = 0; n < lineas.get(k).getEstaciones().size(); n ++) {
                                if(lineas.get(k).GetEstacion(n).getNombre().equals(auxT.getEstacion().getNombre())) {
                                    aux = lineas.get(k).GetEstacion(n).getGenteEsperando();
                                    lineas.get(k).GetEstacion(n).setGenteEsperando(aux + personasCombinacion); 
                                    if(lineas.get(k).GetEstacion(n).getGenteEsperando() > lineas.get(k).GetEstacion(n).getCapacidadMaxima())
                                        lineas.get(k).GetEstacion(n).setGenteEsperando(lineas.get(k).GetEstacion(n).getCapacidadMaxima());
                                }
                            }
                        }
                    }
                }
                auxT.getEstacion().setGenteEsperando(auxT.getEstacion().getGenteEsperando() - personasQueSalen);
                auxT.setPasajerosActuales(auxT.getCapacidadMaxima());
            }           
        }
    }
    
    public void MoverTrenes() {
        Tren auxT;
        int largoT, largoE;
        int indiceEstacion;
        for(int i = 0; i < lineas.size(); i++) {
            largoT = lineas.get(i).getTrenes().size();
            for(int j = 0; j < largoT; j++) {
                auxT = lineas.get(i).getTrenes().get(j);
                indiceEstacion = -1;
                largoE = lineas.get(i).getEstaciones().size();
                //System.out.println(largoE);
                for(int k = 0; k < largoE; k++) {
                    if(auxT.getEstacion() == lineas.get(i).getEstaciones().get(k)) {
                        indiceEstacion = k;
                        //System.out.println("indice: " + indiceEstacion);
                        break;
                    }
                }

                if(auxT.getEstacion() == lineas.get(i).getEstaciones().get(0) && !auxT.Sentido()) {
                    auxT.CambiarSentido();
                }
                else if(auxT.getEstacion() == lineas.get(i).getEstaciones().get(largoE - 1) && auxT.Sentido()) {
                    auxT.CambiarSentido();
                }
                else if(auxT.Sentido()) {
                    auxT.setEstacion(lineas.get(i).GetEstacion(indiceEstacion + 1));
                }
                else {
                    auxT.setEstacion(lineas.get(i).GetEstacion(indiceEstacion - 1));
                }
            }
        }
    }

    public void MostrarEstaciones() {
        int largo;
        Estacion aux;
        for(int i = 0; i < lineas.size(); i++) {
            largo = lineas.get(i).getEstaciones().size();
            for(int j = 0; j < largo; j++) {
                aux = lineas.get(i).getEstaciones().get(j);
                System.out.print(aux.getNombre());
                System.out.println("  " + aux.getGenteEsperando() + "/"+ aux.getCapacidadMaxima());
            }
        }
    }

    public void MostrarTrenes() {
        Linea auxL;
        Tren auxT;
        int largo;
        for(int i = 0; i < lineas.size(); i++) {
            auxL = lineas.get(i);
            largo = auxL.getEstaciones().size();
            for(int j = 0; j < largo; j++) {
                auxT = auxL.getTrenes().get(j);
                System.out.print(auxT.getEstacion().getNombre());
                if(auxT.Sentido()) System.out.print(" - Sentido Norte - ");
                else               System.out.print(" - Sentido Sur - ");
                System.out.println(auxT.getPasajerosActuales()+"/"+auxT.getCapacidadMaxima());
            }
        }
    }
    
    public void MostrarGente() {
        System.out.println(pasajerosTransportados + "/" + pasajerosQueIngresaron);
    }
    
    public Estacion EstacionMasVisitada() {
        ArrayList<Estacion> aux = new ArrayList<>();
        Estacion masVisitada;
        int visitas;
        for(int i = 0; i < lineas.size(); i++) {
            for(int j = 0; j < lineas.get(i).getEstaciones().size(); j++) {
                aux.add(lineas.get(i).GetEstacion(j));
            }    
        }
        visitas = aux.get(0).getNumeroVisitas();
        masVisitada = aux.get(0);
        for(int i = 1; i < aux.size(); i++) {
            if(visitas <= aux.get(i).getNumeroVisitas()) {
                visitas = aux.get(i).getNumeroVisitas();
                masVisitada = aux.get(i);
            }
        }
        
        return masVisitada;
    }
    
    
    public Estacion EstacionMenosVisitada() {
        ArrayList<Estacion> aux = new ArrayList<>();
        Estacion menosVisitada;
        int visitas;
        for(int i = 0; i < lineas.size(); i++) {
            for(int j = 0; j < lineas.get(i).getEstaciones().size(); j++) {
                aux.add(lineas.get(i).GetEstacion(j));
            }    
        }
        visitas = aux.get(0).getNumeroVisitas();
        menosVisitada = aux.get(0);
        for(int i = 1; i < aux.size(); i++) {
            if(visitas >= aux.get(i).getNumeroVisitas()) {
                visitas = aux.get(i).getNumeroVisitas();
                menosVisitada = aux.get(i);
            }
        }
        
        return menosVisitada;
    }
    
    public void CorregirGente() {
        int diferencia;
        for(int i = 0; i < lineas.size(); i++) {
            for(int j = 0; j < lineas.get(i).getEstaciones().size(); j++) {
                if(lineas.get(i).GetEstacion(j).getGenteEsperando() > lineas.get(i).GetEstacion(j).getCapacidadMaxima()) {
                    diferencia = lineas.get(i).GetEstacion(j).getGenteEsperando() - lineas.get(i).GetEstacion(j).getCapacidadMaxima();
                    lineas.get(i).GetEstacion(j).setGenteEsperando(lineas.get(i).GetEstacion(j).getCapacidadMaxima());
                    pasajerosQueIngresaron -= diferencia;
                    pasajerosTransportados -= diferencia;
                }
            }
        }
    }
}


/**
 *
 * @author paulo
 */
public class Problema_3_Futbol {

    public static void main(String[] args) {
        Atacante atacante = new Atacante("Paulo Bustamante", 9, "11");
        atacante.registrarEstadisticas(5, 20, 8);
        System.out.println(" Atacante ");
        System.out.println(atacante);

        Defensor defensor = new Defensor("Diego Espinosa", 5, "17");
        defensor.registrarEstadisticas(2, 25, 15);
        System.out.println("\n Defensor ");
        System.out.println(defensor);

        Portero portero = new Portero("Antonio Ojeda", 1, "14");
        portero.registrarEstadisticas(0, 12);
        System.out.println("\n Portero ");
        System.out.println(portero);
    }
}

abstract class Jugador {

    protected String nombre;
    protected int dorsal;
    protected String rut;
    protected int goles;

    public Jugador(String nombre, int dorsal, String rut) {
        this.nombre = nombre;
        this.dorsal = dorsal;
        this.rut = rut;
    }

    public void registrarEstadisticas(int goles) {
        this.goles = goles;
    }

    public abstract int calcularValoracion();

    @Override
    public String toString() {
        return "Nombre: " + nombre
                + "\nDorsal: " + dorsal
                + "\nRUT: " + rut
                + "\nGoles: " + goles
                + "\nValoración: " + calcularValoracion();
    }
}   

class Atacante extends Jugador {

    protected int pases;
    protected int recuperados;

    public Atacante(String nombre, int dorsal, String rut) {
        super(nombre, dorsal, rut);
    }

    public void registrarEstadisticas(int goles, int pases, int recuperados) {
        super.registrarEstadisticas(goles);
        this.pases = pases;
        this.recuperados = recuperados;
    }

    @Override
    public int calcularValoracion() {
        return goles * 30 + recuperados * 3;
    }

    @Override
    public String toString() {
        return super.toString()
                + "\nPases: " + pases
                + "\nRecuperados: " + recuperados;
    }
}

class Defensor extends Jugador {

    protected int pases;
    protected int recuperados;

    public Defensor(String nombre, int dorsal, String rut) {
        super(nombre, dorsal, rut);
    }

    public void registrarEstadisticas(int goles, int pases, int recuperados) {
        super.registrarEstadisticas(goles);
        this.pases = pases;
        this.recuperados = recuperados;
    }

    @Override
    public int calcularValoracion() {
        return goles * 30 + recuperados * 4;
    }

    @Override
    public String toString() {
        return super.toString()
                + "\nPases: " + pases
                + "\nRecuperados: " + recuperados;
    }
}

class Portero extends Jugador {

    protected int atajadas;

    public Portero(String nombre, int dorsal, String rut) {
        super(nombre, dorsal, rut);
    }

    public void registrarEstadisticas(int goles, int atajadas) {
        super.registrarEstadisticas(goles);
        this.atajadas = atajadas;
    }

    @Override
    public int calcularValoracion() {
        return goles * 30 + atajadas * 5;
    }

    @Override
    public String toString() {
        return super.toString()
                + "\nAtajadas: " + atajadas;
    }
}

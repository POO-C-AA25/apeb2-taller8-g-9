
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *
 *
 *
 * @author paulo
 */
public class Problema_6_Conflicto {

    public static void main(String[] args) {
        Conflicto conflicto = new Conflicto();
        conflicto.iniciar();
    }
}

abstract class Nacion {

    public String nombre;
    public int poblacion;
    public double recursos;
    public int poderMilitar;
    public boolean estadoConflicto;
    public List<Nacion> aliados;

    public Nacion(String nombre, int poblacion, double recursos) {
        this.nombre = nombre;
        this.poblacion = poblacion;
        this.recursos = recursos;
        this.estadoConflicto = false;
        this.aliados = new ArrayList<>();
    }

    public abstract void agregarAliado(Nacion aliado);

    public abstract void calcularPoderMilitar();

    public String obtenerNombresAliados() {
        String resultado = "";
        for (int i = 0; i < aliados.size(); i++) {
            resultado += aliados.get(i).nombre;
            if (i < aliados.size() - 1) {
                resultado += ", ";
            }
        }
        return resultado;
    }

    @Override
    public String toString() {
        return "Nación: " + nombre
                + ", Población: " + poblacion
                + ", Recursos: " + recursos
                + ", Poder Militar: " + poderMilitar
                + ", Conflicto: " + (estadoConflicto ? "Sí" : "No")
                + ", Aliados: " + obtenerNombresAliados();
    }
}

class NacionDesarrollada extends Nacion {

    public boolean tecnologia;

    public NacionDesarrollada(String nombre, int poblacion, double recursos, boolean tecnologia) {
        super(nombre, poblacion, recursos);
        this.tecnologia = tecnologia;
    }

    public void agregarAliado(Nacion aliado) {
        aliados.add(aliado);
    }

    public void calcularPoderMilitar() {
        poderMilitar = (poblacion / 1000000) + (int) (recursos / 5000);
        if (tecnologia) {
            poderMilitar += 15;
        }
        poderMilitar += aliados.size() * 2;
        if (poderMilitar > 100) {
            poderMilitar = 100;
        }
    }
}

class NacionEnDesarrollo extends Nacion {

    public NacionEnDesarrollo(String nombre, int poblacion, double recursos) {
        super(nombre, poblacion, recursos);
    }

    public void agregarAliado(Nacion aliado) {
        aliados.add(aliado);
    }

    public void calcularPoderMilitar() {
        poderMilitar = (poblacion / 15000) + (int) (recursos / 1200) + aliados.size() * 2;
        if (poderMilitar > 100) {
            poderMilitar = 100;
        }
    }
}

class Conflicto {

    public List<Nacion> naciones;
    public Scanner scanner = new Scanner(System.in);
    public Random random = new Random();

    public Conflicto() {
        this.naciones = new ArrayList<>();
    }

    public void iniciar() {
        naciones.add(new NacionDesarrollada("EEUU", 331000, 20000, true));
        naciones.add(new NacionDesarrollada("China", 1439000, 1800, true));
        naciones.add(new NacionEnDesarrollo("Rusia", 144000, 1000));
        naciones.add(new NacionEnDesarrollo("Corea del Norte", 26000, 500));

        for (Nacion n : naciones) {
            n.calcularPoderMilitar();
        }

        int opcion;
        do {
            System.out.println("\nMENÚ PRINCIPAL");
            System.out.println("1. Mostrar Naciones");
            System.out.println("2. Simular Conflicto");
            System.out.println("3. Agregar Aliado");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    mostrarNaciones();
                    break;
                case 2:
                    simularConflicto();
                    break;
                case 3:
                    asignarAliado();
                    break;
                case 4:
                    System.out.println("Saliendo del sistema");
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        } while (opcion != 4);
    }

    public void mostrarNaciones() {
        for (Nacion n : naciones) {
            System.out.println(n);
        }
    }

    public void simularConflicto() {
        Nacion n1 = naciones.get(random.nextInt(naciones.size()));
        Nacion n2 = naciones.get(random.nextInt(naciones.size()));
        while (n1 == n2) {
            n2 = naciones.get(random.nextInt(naciones.size()));
        }

        n1.estadoConflicto = true;
        n2.estadoConflicto = true;

        System.out.println("\nConflicto entre " + n1.nombre + " y " + n2.nombre);
        if (n1.poderMilitar > n2.poderMilitar) {
            System.out.println(n1.nombre + " gana el conflicto.");
        } else if (n2.poderMilitar > n1.poderMilitar) {
            System.out.println(n2.nombre + " gana el conflicto.");
        } else {
            System.out.println("Conflicto empatado. Pérdidas en ambos lados.");
        }
    }

    public void asignarAliado() {
        System.out.print("Ingrese el nombre de la nación que desea asignar aliado: ");
        String nombre = scanner.nextLine();
        Nacion principal = null;
        for (Nacion n : naciones) {
            if (n.nombre.equalsIgnoreCase(nombre)) {
                principal = n;
                break;
            }
        }
        if (principal == null) {
            System.out.println("Nación no encontrada.");
            return;
        }

        System.out.print("Ingrese el nombre del aliado: ");
        String aliadoNombre = scanner.nextLine();
        for (Nacion aliado : naciones) {
            if (aliado.nombre.equalsIgnoreCase(aliadoNombre) && !aliado.equals(principal)) {
                principal.agregarAliado(aliado);
                principal.calcularPoderMilitar();
                System.out.println("Aliado asignado correctamente.");
                return;
            }
        }
        System.out.println("Aliado no válido.");
    }
}

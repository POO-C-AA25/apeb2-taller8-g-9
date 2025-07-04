
/**
 *
 * @author paulo
 */
import java.util.Random;

public class Problema_1_JuegoRoles {

    static Personaje guerrero;
    static Personaje mago;
    static Personaje arquero;

    public static void main(String[] args) {
        guerrero = new Guerrero("Gigante", "Guerrero", 3);
        mago = new Mago("Invisible", "Mago", 3);
        arquero = new Arquero("Precisión", "Arquero", 3);

        System.out.println("PELEA 1: GUERRERO vs MAGO ");
        pelea(guerrero, mago);

        System.out.println("PELEA 2: ARQUERO vs GUERRERO ");
        pelea(arquero, guerrero);

        System.out.println("PELEA 3: ARQUERO vs MAGO");
        pelea(arquero, mago);

        System.out.println("\nESTADO FINAL DE LOS PERSONAJES:");
        System.out.println("GUERRERO: " + guerrero);
        System.out.println("MAGO: " + mago);
        System.out.println("ARQUERO: " + arquero);
    }

    public static void pelea(Personaje atacante, Personaje defensor) {
        boolean gana = atacante.ataque(defensor);
        if (gana) {
            atacante.experiencia += 1;
            atacante.batallasGana += 1;
            defensor.vidas = defensor.defensa() - 1;
            System.out.println(atacante.getNombre() + " gana el ataque.");
        } else {
            defensor.experiencia += 1;
            defensor.batallasGana += 1;
            atacante.vidas = atacante.defensa() - 1;
            System.out.println(defensor.getNombre() + " se defendió.");
        }
    }
}

abstract class Personaje {

    protected String nombre;
    public int vidas;
    public int experiencia;
    public int batallasGana;

    public Personaje(String nombre, int vidas) {
        this.nombre = nombre;
        this.vidas = vidas;
    }

    public String getNombre() {
        return nombre;
    }

    public abstract boolean ataque(Personaje personaje);

    public abstract int defensa();

    @Override
    public String toString() {
        return "Personaje{" + "nombre=" + nombre + ", vidas=" + vidas + ", experiencia=" + experiencia + ", batallasGana=" + batallasGana + '}';
    }
}

class Guerrero extends Personaje {

    public String habilidades;

    public Guerrero(String habilidades, String nombre, int vidas) {
        super(nombre, vidas);
        this.habilidades = habilidades;
    }

    @Override
    public boolean ataque(Personaje personaje) {
        int bandera = (int) (Math.random() * 2);
        return bandera == 1;
    }

    @Override
    public int defensa() {
        return 1;
    }

    @Override
    public String toString() {
        return "Guerrero{" + "habilidades=" + habilidades + '}' + super.toString();
    }
}

class Mago extends Personaje {

    public String estrategia;

    public Mago(String estrategia, String nombre, int vidas) {
        super(nombre, vidas);
        this.estrategia = estrategia;
    }

    @Override
    public boolean ataque(Personaje personaje) {
        int probabilidad = (int) (Math.random() * 2);
        return probabilidad == 1;
    }

    @Override
    public int defensa() {
        return 1;
    }

    @Override
    public String toString() {
        return "Mago{" + "estrategia=" + estrategia + '}' + super.toString();
    }
}

class Arquero extends Personaje {

    public String atributo;

    public Arquero(String atributo, String nombre, int vidas) {
        super(nombre, vidas);
        this.atributo = atributo;
    }

    @Override
    public boolean ataque(Personaje personaje) {
        int probabilidad = (int) (Math.random() * 2);
        return probabilidad == 1;
    }

    @Override
    public int defensa() {
        return 1;
    }

    @Override
    public String toString() {
        return "Arquero{" + "atributo=" + atributo + '}' + super.toString();
    }
}

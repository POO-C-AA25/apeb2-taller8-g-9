
/**
 *
 * @author paulo
 */
import java.util.ArrayList;

public class Problema_5_Emprendimiento {

    public static void main(String[] args) {
        ArrayList<String> productosTec = new ArrayList<>();
        productosTec.add("App móvil");
        productosTec.add("Sistema web");

        ArrayList<String> productosAgro = new ArrayList<>();
        productosAgro.add("Miel orgánica");

        ArrayList<String> productosGast = new ArrayList<>();
        productosGast.add("Hamburguesas artesanales");

        ArrayList<String> productosArte = new ArrayList<>();
        productosArte.add("Cerámica hecha a mano");

        Mentor mentor1 = new Mentor("Diego Espinosa", "Tecnología");
        Mentor mentor2 = new Mentor("Paulo Bustamante", "Agronomía");
        Mentor mentor3 = new Mentor("Javier Vinan", "Cocina Gourmet");
        Mentor mentor4 = new Mentor("Antonio Ojeda", "Diseño Artesanal");

        EmprendimientoTecnologico empTec
                = new EmprendimientoTecnologico("TecNova", "Tecnológico", "Transformar digitalmente a la comunidad", "contacto@tecnova.com", productosTec);
        empTec.agregarMentor(mentor1);

        EmprendimientoAgricola empAgro
                = new EmprendimientoAgricola("AgroVida", "Agrícola", "Ofrecer productos naturales", "agro@vida.com", productosAgro);
        empAgro.agregarMentor(mentor2);

        EmprendimientoGastronomico empGast
                = new EmprendimientoGastronomico("Sabor Local", "Gastronómico", "Promover comida local saludable", "info@saborlocal.com", productosGast);
        empGast.agregarMentor(mentor3);

        EmprendimientoArtesanal empArte
                = new EmprendimientoArtesanal("ArteAndino", "Artesanal", "Difundir el arte andino", "arte@andino.com", productosArte);
        empArte.agregarMentor(mentor4);

        Feria feria = new Feria("Feria Emprende 2024", "Av. Libertad 123", 50);

        System.out.println(empTec);
        System.out.println(empTec.participarFeria(feria));
        empTec.evolucionar();

        System.out.println("\n" + empAgro);
        System.out.println(empAgro.participarFeria(feria));
        empAgro.evolucionar();

        System.out.println("\n" + empGast);
        System.out.println(empGast.participarFeria(feria));
        empGast.evolucionar();

        System.out.println("\n" + empArte);
        System.out.println(empArte.participarFeria(feria));
        empArte.evolucionar();
    }
}

abstract class Emprendimiento {

    protected String nombre;
    protected String tipo;
    protected String mision;
    protected String contacto;
    protected ArrayList<String> productoServicio;
    protected ArrayList<Mentor> mentores;

    public Emprendimiento() {
        productoServicio = new ArrayList<>();
        mentores = new ArrayList<>();
    }

    public Emprendimiento(String nombre, String tipo, String mision, String contacto, ArrayList<String> productoServicio) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.mision = mision;
        this.contacto = contacto;
        this.productoServicio = productoServicio;
        this.mentores = new ArrayList<>();
    }

    public void agregarMentor(Mentor m) {
        mentores.add(m);
    }

    public abstract String participarFeria(Feria feria);

    public abstract void evolucionar();

    @Override
    public String toString() {
        return "\n--- Emprendimiento ---"
                + "\nNombre: " + nombre
                + "\nTipo: " + tipo
                + "\nMisión: " + mision
                + "\nContacto: " + contacto
                + "\nProductos o Servicios: " + productoServicio
                + "\nMentores: " + mentores;
    }
}

class EmprendimientoTecnologico extends Emprendimiento {

    public EmprendimientoTecnologico(String nombre, String tipo, String mision, String contacto, ArrayList<String> productoServicio) {
        super(nombre, tipo, mision, contacto, productoServicio);
    }

    @Override
    public String participarFeria(Feria feria) {
        return "El emprendimiento tecnológico \"" + nombre + "\" participará en la feria \"" + feria.nombre + "\".";
    }

    @Override
    public void evolucionar() {
        System.out.println("Evolución tecnológica: actualización de plataformas digitales.");
    }
}

class EmprendimientoAgricola extends Emprendimiento {

    public EmprendimientoAgricola(String nombre, String tipo, String mision, String contacto, ArrayList<String> productoServicio) {
        super(nombre, tipo, mision, contacto, productoServicio);
    }

    @Override
    public String participarFeria(Feria feria) {
        return "El emprendimiento agrícola \"" + nombre + "\" participará en la feria \"" + feria.nombre + "\".";
    }

    @Override
    public void evolucionar() {
        System.out.println("Evolución agrícola: incorporación de técnicas sostenibles.");
    }
}

class EmprendimientoGastronomico extends Emprendimiento {

    public EmprendimientoGastronomico(String nombre, String tipo, String mision, String contacto, ArrayList<String> productoServicio) {
        super(nombre, tipo, mision, contacto, productoServicio);
    }

    @Override
    public String participarFeria(Feria feria) {
        return "El emprendimiento gastronómico \"" + nombre + "\" participará en la feria \"" + feria.nombre + "\".";
    }

    @Override
    public void evolucionar() {
        System.out.println("Evolución gastronómica: ampliación del menú e inclusión de platos regionales.");
    }
}

class EmprendimientoArtesanal extends Emprendimiento {

    public EmprendimientoArtesanal(String nombre, String tipo, String mision, String contacto, ArrayList<String> productoServicio) {
        super(nombre, tipo, mision, contacto, productoServicio);
    }

    @Override
    public String participarFeria(Feria feria) {
        return "El emprendimiento artesanal \"" + nombre + "\" participará en la feria \"" + feria.nombre + "\".";
    }

    @Override
    public void evolucionar() {
        System.out.println("Evolución artesanal: modernización de técnicas de producción.");
    }
}

class Feria {

    String nombre;
    String direccion;
    int empleados;

    public Feria(String nombre, String direccion, int empleados) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.empleados = empleados;
    }

    @Override
    public String toString() {
        return "Feria: " + nombre + ", Dirección: " + direccion + ", Empleados: " + empleados;
    }
}

class Mentor {

    String nombre;
    String areaTrabajo;

    public Mentor(String nombre, String areaTrabajo) {
        this.nombre = nombre;
        this.areaTrabajo = areaTrabajo;
    }

    @Override
    public String toString() {
        return nombre + " (" + areaTrabajo + ")";
    }
}

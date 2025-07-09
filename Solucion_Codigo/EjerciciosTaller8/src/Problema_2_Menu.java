
/**
 *
 * @author paulo
 */
import java.util.ArrayList;
import java.util.Arrays;

public class Problema_2_Menu {

    public static void main(String[] args) {

        Menu menuCarta = new menuCarta("Atún al filete", 16.0, 2.0, 2.5, 10);
        menuCarta.calcularValorTotal();

        Menu menuDia = new menuDia("Seco de res", 10.0, 2.0, 3.0);
        menuDia.calcularValorTotal();

        Menu menuNino = new menuNino("Papas fritas", 7.0, 4.2, 5.3);
        menuNino.calcularValorTotal();

        Menu menuEconomico = new menuEconomico("Ensalada Caesar", 4.5, 25);
        menuEconomico.calcularValorTotal();

        Cuenta cuenta = new Cuenta("Diego Espinosa");
        cuenta.agregarMenu(menuCarta);
        cuenta.agregarMenu(menuDia);
        cuenta.agregarMenu(menuNino);
        cuenta.agregarMenu(menuEconomico);
        cuenta.calcularTotal();

        System.out.println(cuenta);
    }

    public static abstract class Menu {

        public String nombrePlato;
        public double valorInicial;
        public double valorTotal;

        public Menu(String nombrePlato, double valorInicial) {
            this.nombrePlato = nombrePlato;
            this.valorInicial = valorInicial;
        }

        public abstract void calcularValorTotal();

        @Override
        public abstract String toString();
    }

    public static class menuCarta extends Menu {

        public double valorPorcionGuarnicion;
        public double valorBebida;
        public int adicional;

        public menuCarta(String nombrePlato, double valorInicial,
                double valorPorcionGuarnicion, double valorBebida, int adicional) {
            super(nombrePlato, valorInicial);
            this.valorPorcionGuarnicion = valorPorcionGuarnicion;
            this.valorBebida = valorBebida;
            this.adicional = adicional;
        }

        @Override
        public void calcularValorTotal() {
            valorTotal = valorInicial + valorPorcionGuarnicion + valorBebida
                    + (valorInicial * adicional / 100);
        }

        @Override
        public String toString() {
            return "Menú Carta:\n"
                    + "Plato: " + nombrePlato + "\n"
                    + "Valor Inicial: $" + valorInicial + "\n"
                    + "Guarnición: $" + valorPorcionGuarnicion + "\n"
                    + "Bebida: $" + valorBebida + "\n"
                    + "Adicional: " + adicional + "%\n"
                    + "Valor Total: $" + valorTotal + "\n";
        }
    }

    public static class menuDia extends Menu {

        public double valorPostre;
        public double valorBebida;

        public menuDia(String nombrePlato, double valorInicial, double valorPostre, double valorBebida) {
            super(nombrePlato, valorInicial);
            this.valorPostre = valorPostre;
            this.valorBebida = valorBebida;
        }

        @Override
        public void calcularValorTotal() {
            valorTotal = valorInicial + valorPostre + valorBebida;
        }

        @Override
        public String toString() {
            return "Menú Día:\n"
                    + "Plato: " + nombrePlato + "\n"
                    + "Valor Inicial: $" + valorInicial + "\n"
                    + "Postre: $" + valorPostre + "\n"
                    + "Bebida: $" + valorBebida + "\n"
                    + "Valor Total: $" + valorTotal + "\n";
        }
    }

    public static class menuNino extends Menu {

        public double valorHelado;
        public double valorPastel;

        public menuNino(String nombrePlato, double valorInicial, double valorHelado, double valorPastel) {
            super(nombrePlato, valorInicial);
            this.valorHelado = valorHelado;
            this.valorPastel = valorPastel;
        }

        @Override
        public void calcularValorTotal() {
            valorTotal = valorInicial + valorHelado + valorPastel;
        }

        @Override
        public String toString() {
            return "Menú Niño:\n"
                    + "Plato: " + nombrePlato + "\n"
                    + "Valor Inicial: $" + valorInicial + "\n"
                    + "Helado: $" + valorHelado + "\n"
                    + "Pastel: $" + valorPastel + "\n"
                    + "Valor Total: $" + valorTotal + "\n";
        }
    }

    public static class menuEconomico extends Menu {

        public int descuento;

        public menuEconomico(String nombrePlato, double valorInicial, int descuento) {
            super(nombrePlato, valorInicial);
            this.descuento = descuento;
        }

        @Override
        public void calcularValorTotal() {
            valorTotal = valorInicial - (valorInicial * descuento / 100);
        }

        @Override
        public String toString() {
            return "Menú Económico:\n"
                    + "Plato: " + nombrePlato + "\n"
                    + "Valor Inicial: $" + valorInicial + "\n"
                    + "Descuento: " + descuento + "%\n"
                    + "Valor Total: $" + valorTotal + "\n";
        }
    }

    public static class Cuenta {

        public String nombreCliente;
        public ArrayList<Menu> cartasSolicitadas = new ArrayList<>();
        public double subtotal;
        public int iva = 12;
        public double valorTotal;

        public Cuenta(String nombreCliente) {
            this.nombreCliente = nombreCliente;
        }

        public void agregarMenu(Menu menu) {
            cartasSolicitadas.add(menu);
        }

        public void calcularTotal() {
            subtotal = 0;
            for (Menu m : cartasSolicitadas) {
                subtotal += m.valorTotal;
            }
            valorTotal = subtotal + (subtotal * iva / 100);
        }

        @Override
        public String toString() {
            String resultado = "CLIENTE: " + nombreCliente + "\n";
            resultado += "---------- MENÚS ---------\n";
            for (Menu m : cartasSolicitadas) {
                resultado += m.toString() + "\n";
            }
            resultado += "Subtotal: $" + subtotal + "\n";
            resultado += "IVA: " + iva + "%\n";
            resultado += "Total a cancelar: $" + valorTotal + "\n";
            return resultado;
        }
    }
}

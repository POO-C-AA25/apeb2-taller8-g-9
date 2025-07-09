
import java.util.ArrayList;

/**
 *
 * @author paulo
 */
public class Problema_4_DispositivoMonitoreo {

    public static void main(String[] args) {
        GestionReporte controlador = new GestionReporte();

        DispositivoCosta costa
                = new DispositivoCosta(75, 10, 30, 5, "Mala", 20, "Costa");
        DispositivoSierra sierra
                = new DispositivoSierra("Muy alta", 60, 18, 3, "Buena", 15, "Sierra");
        DispositivoOriente oriente
                = new DispositivoOriente("Alta", 80, 22, 6, "Regular", 30, "Oriente");

        controlador.agregarDispositivo(costa);
        controlador.agregarDispositivo(sierra);
        controlador.agregarDispositivo(oriente);

        controlador.generarReportes();
        controlador.mostrarDispositivos(controlador.obtenerDispositivos());
    }
}

abstract class DispositivoMonitoreo {

    public double temperatura;
    public int precipitacion;
    public String calidadAire;
    public double humedadSuelo;
    public String region;
    public String mensaje;

    public DispositivoMonitoreo() {
    }

    public DispositivoMonitoreo(double temperatura, int precipitacion, String calidadAire, double humedadSuelo, String region) {
        this.temperatura = temperatura;
        this.precipitacion = precipitacion;
        this.calidadAire = calidadAire;
        this.humedadSuelo = humedadSuelo;
        this.region = region;
    }

    public abstract String procesarDatos();

    @Override
    public String toString() {
        return "Temperatura: " + temperatura + "°C\n"
                + "Precipitación: " + precipitacion + " mm\n"
                + "Calidad del Aire: " + calidadAire + "\n"
                + "Humedad del Suelo: " + humedadSuelo + "%\n"
                + "Región: " + region + "\n"
                + "Mensaje: " + mensaje;
    }
}

class DispositivoCosta extends DispositivoMonitoreo {

    public double humedadAmbiente;
    public double intensidadLluvia;

    public DispositivoCosta() {
    }

    public DispositivoCosta(double humedadAmbiente, double intensidadLluvia,
            double temperatura, int precipitacion, String calidadAire,
            double humedadSuelo, String region) {
        super(temperatura, precipitacion, calidadAire, humedadSuelo, region);
        this.humedadAmbiente = humedadAmbiente;
        this.intensidadLluvia = intensidadLluvia;
    }

    @Override
    public String procesarDatos() {
        if (humedadAmbiente >= 60 && humedadAmbiente < 70) {
            calidadAire = "Baja";
            mensaje = "La humedad está deteriorando la calidad del aire en la región " + region;
        } else if (humedadAmbiente > 70) {
            calidadAire = "Muy baja";
            mensaje = "La humedad excesiva genera condiciones críticas de calidad del aire en la región " + region;
        } else {
            calidadAire = "Buena";
            mensaje = "Condiciones estables de la region " + region;
        }
        return mensaje;
    }

    @Override
    public String toString() {
        return "Dispositivo Costa\n"
                + "Humedad Ambiente: " + humedadAmbiente + "%\n"
                + "Intensidad de Lluvia: " + intensidadLluvia + " mm/h\n"
                + super.toString();
    }
}

class DispositivoSierra extends DispositivoMonitoreo {

    public String intensidadLluvia;
    public double probabilidadLluvia;

    public DispositivoSierra() {
    }

    public DispositivoSierra(String intensidadLluvia, double probabilidadLluvia,
            double temperatura, int precipitacion, String calidadAire,
            double humedadSuelo, String region) {
        super(temperatura, precipitacion, calidadAire, humedadSuelo, region);
        this.intensidadLluvia = intensidadLluvia;
        this.probabilidadLluvia = probabilidadLluvia;
    }

    @Override
    public String procesarDatos() {
        if (intensidadLluvia.equalsIgnoreCase("Muy alta") && probabilidadLluvia >= 50) {
            mensaje = "Probabilidades de lluvia muy altas en la Sierra. Precaución.";
        } else if (intensidadLluvia.equalsIgnoreCase("Normal") && (probabilidadLluvia <= 50 && probabilidadLluvia >= 15)) {
            mensaje = "Lluvias moderadas esperadas en la Sierra.";
        } else {
            mensaje = "Condiciones estables en la región Sierra.";
        }
        return mensaje;
    }

    @Override
    public String toString() {
        return "Dispositivo Sierra\n"
                + "Intensidad de Lluvia: " + intensidadLluvia + "\n"
                + "Probabilidad de Lluvia: " + probabilidadLluvia + "%\n"
                + super.toString();
    }
}

class DispositivoOriente extends DispositivoMonitoreo {

    public String cantidadPrecipitacion;
    public int probabilidadDerrumbe;

    public DispositivoOriente() {
    }

    public DispositivoOriente(String cantidadPrecipitacion, int probabilidadDerrumbe,
            double temperatura, int precipitacion, String calidadAire,
            double humedadSuelo, String region) {
        super(temperatura, precipitacion, calidadAire, humedadSuelo, region);
        this.cantidadPrecipitacion = cantidadPrecipitacion;
        this.probabilidadDerrumbe = probabilidadDerrumbe;
    }

    @Override
    public String procesarDatos() {
        if (cantidadPrecipitacion.equalsIgnoreCase("Alta") && probabilidadDerrumbe > 70) {
            mensaje = "Alerta de posibles derrumbes en la región " + region + ".";
        } else if (cantidadPrecipitacion.equalsIgnoreCase("Moderada") && probabilidadDerrumbe > 40) {
            mensaje = "Monitoreo activo en la región " + region + " por riesgo medio de derrumbes.";
        } else {
            mensaje = "Sin riesgos detectados en la región " + region + ".";
        }
        return mensaje;
    }

    @Override
    public String toString() {
        return "Dispositivo Oriente\n"
                + "Cantidad de Precipitación: " + cantidadPrecipitacion + "\n"
                + "Probabilidad de Derrumbe: " + probabilidadDerrumbe + "%\n"
                + super.toString();
    }
}

class GestionReporte {

    ArrayList<DispositivoMonitoreo> listaDispositivos = new ArrayList<>();

    public void agregarDispositivo(DispositivoMonitoreo dispositivo) {
        listaDispositivos.add(dispositivo);
    }

    public void generarReportes() {
        for (DispositivoMonitoreo d : listaDispositivos) {
            d.procesarDatos();
        }
    }

    public ArrayList<DispositivoMonitoreo> obtenerDispositivos() {
        return listaDispositivos;
    }

    public void mostrarDispositivos(ArrayList<DispositivoMonitoreo> lista) {
        for (DispositivoMonitoreo d : lista) {
            System.out.println(d);
            System.out.println("------------------");
        }
    }
}

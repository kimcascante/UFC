package Tareas;

/**
 * Nombre del programa: Caso UFC
 * Descripcion: Caso UFC
    Usted fue contratado por la Ultimate Fighting Championship (UFC) para realizar un sistema
    que maneja la información acerca de sus peleadores, rankings y las ganancias que estos
    generan. 
 * Autor: Kimberly C.
 * Fecha de creacion: 18-07-2020
 * Modificado por: Kimberly C.
 * fecha de modificacion: 18-07-2020
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class CasoUFCTareaCuatro {
    public static BufferedReader leer = new BufferedReader(new InputStreamReader(System.in));
    public static PrintStream escribir = System.out;

    public static void main(String[] args) throws IOException {
        boolean salir = false;

        do {
            mostrarMenu();
            char opcion = leerOpcion();
            salir = ejecutar(opcion);
        } while (!salir);
    }

    static void mostrarMenu() {
        escribir.println();
        escribir.println("- UFC Menú -");
        escribir.println();
        escribir.println("A- Encontrar peleador mejor rankeado");
        escribir.println("B- Calcular ganancias totales de un peleador");
        escribir.println("Q - Salir");
        escribir.println();
    }

    static char leerOpcion() {
        try {
            escribir.println();
            escribir.println("Seleccione una opción:");
            char opcion = leer.readLine().charAt(0);
            escribir.println();
            return opcion;
        } catch (Exception iOException) {
            return 'x';
        }
    }

    static boolean ejecutar(char pOpcion) throws IOException {
        boolean salir = false;
        String nombre ="", bonoPerformance="", bonoNoche="";
        int ranking=0, peleas=0, vendidas=0, resultado=0;
        int peleadores=0, puntosRanking=0;
        String nombrePeleador="";

        if (pOpcion == 'q') {
            salir = true;
            escribir.println();
            escribir.println("Fin del programa");
        } else if (pOpcion == 'a') {
            escribir.println();
            escribir.println("Iniciamos.. para encontrar el jugador mejor rankeado");
            escribir.println();
            opcionA(peleadores, nombrePeleador, puntosRanking);
        } else if (pOpcion == 'b') {
            escribir.println();
            escribir.println("Iniciamos.. para calcular ganancias totales de un jugador");
            escribir.println();
            opcionB(nombre, ranking, peleas, vendidas, resultado, bonoPerformance, bonoNoche);
        }

        return salir;
    }

    public static String opcionA(int pPeleadores, String pNombrePeleador, int pPuntosRanking) throws IOException {
        String mejorRankeado = null;
        int puntosMejorR = 0;
    
        escribir.println("Digite cuantos peleadores va a ingresar:");
        pPeleadores = Integer.parseInt(leer.readLine());

        for (int count = 0; count < pPeleadores; count++) {
            escribir.println("Digite el nombre del peleador " + (count + 1));
            pNombrePeleador = leer.readLine();

            escribir.println("Digite cuantos puntos tiene el jugador " + pNombrePeleador + " en el ranking:");
            pPuntosRanking = Integer.parseInt(leer.readLine());

            if (pPuntosRanking > puntosMejorR) {
                puntosMejorR = pPuntosRanking;
                mejorRankeado = pNombrePeleador;
            }
        }

        escribir.println("El mejor peleador rankeado fue " + mejorRankeado + " con " + puntosMejorR + " puntos");
        
        escribir.println("Presione cualquier tecla para continuar…");
        String tecla = leer.readLine();
        tecla = " ";
        escribir.println(tecla);
        
        return mejorRankeado;
    }

    public static double opcionB(String pNombre, int pRanking, int pPeleas, int pVendidas, int pResultado,
        String pBonoPerformance, String pBonoNoche) throws IOException {
        int salario;
        double salarioTotal;
        int salarioPeleas = 0;
        int ppvs = 0;
        double taquilla;
        int totalPagoPerformance = 0;
        int totalPagoNoche = 0;

        escribir.println("Digite el nombre del peleador: ");
        pNombre = leer.readLine();

        escribir.println("Digite el numero de raking: ");
        pRanking = Integer.parseInt(leer.readLine());

        escribir.println("Digite el numero de peleas: ");
        pPeleas = Integer.parseInt(leer.readLine());

        for (int count = 0; count < pPeleas; count++) {
            escribir.println("Digite la cantidad de PPVs vendidas: ");
            pVendidas = Integer.parseInt(leer.readLine());

            escribir.println("Digite 1. Si Ganó 2. Si Perdió 3. Empató 4. Descalificado ");
            pResultado = Integer.parseInt(leer.readLine());

            if (pResultado == 1){
                escribir.println("¿El jugador recibió el bono “Performance de la noche”: (Si/No)");
                pBonoPerformance = leer.readLine();
                if (pBonoPerformance.equalsIgnoreCase("Si")) {
                    totalPagoPerformance = totalPagoPerformance + 1;
                } // Fin del contador de bonos performance
            } // Fin del if para cononcer si recibio el bono

            escribir.println("¿El jugador recibió el bono “Pelea de la noche”: (Si/No)");
            pBonoNoche = leer.readLine();
            if (pBonoNoche.equalsIgnoreCase("Si")) {
                totalPagoNoche = totalPagoNoche + 1;
            } // Fin del contactor de bonos de noche

            if (pResultado == 1) {
                salario = 40000;
            } else if (pResultado == 2) {
                salario = 20000;
            } else if (pResultado == 3) {
                salario = 30000;
            } else {
                salario = 0;
            } // Fin del if para el salario

            salarioPeleas = salarioPeleas + salario;
            ppvs = ppvs + pVendidas;

        } // Fin del for de cada pelea

        if (pRanking <= 10) {
            salarioTotal = salarioPeleas * 3;
        } else if (pRanking > 10 && pRanking < 20) {
            salarioTotal = salarioPeleas * 2;
        } else {
            salarioTotal = salarioPeleas;
        }

        taquilla = (ppvs * 60) * 0.04;

        salarioTotal = salarioTotal + taquilla;
        salarioTotal = salarioTotal + (totalPagoPerformance * 50000);
        salarioTotal = salarioTotal + (totalPagoNoche * 25000);

        escribir.println("Salario pagado al peleador " + pNombre + " es de " + salarioTotal + " dolares");

        escribir.println("Presione cualquier tecla para continuar…");
        String tecla = leer.readLine();
        tecla = " ";
        escribir.println(tecla);

        return salarioTotal;
    }
}
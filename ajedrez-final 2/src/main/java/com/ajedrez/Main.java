package com.ajedrez;

import java.util.List;
import java.util.Scanner;

public class Main {

    // Lector de teclado para leer los movimientos del jugador
    private static final Scanner scanner = new Scanner(System.in);

    // Instancia del juego actual
    private static Juego juego = new Juego();

    // Punto de entrada del programa
    public static void main(String[] args) {
        ejecutar_pruebas();
        mostrar_bienvenida();
        bucle_partida();
        System.out.println("\n¡Hasta pronto!");
    }

    // Ejecuta pruebas básicas al arrancar para verificar que el juego funciona
    private static void ejecutar_pruebas() {
        System.out.println("  Verificando funcionalidad...");
        Juego prueba = new Juego();

        ResultadoMovimiento r1 = prueba.realizar_movimiento("e2", "e3");
        if (!r1.es_exitoso()) {
            System.out.println("  ERROR: El peón debería poder avanzar e2→e3");
        }

        ResultadoMovimiento r2 = prueba.realizar_movimiento("e7", "e5");
        if (r2.es_exitoso()) {
            System.out.println("  ERROR: No debería mover pieza negra en turno de blancas");
        }

        Juego prueba2 = new Juego();
        ResultadoMovimiento r3 = prueba2.realizar_movimiento("g1", "f3");
        if (!r3.es_exitoso()) {
            System.out.println("  ERROR: El caballo debería poder moverse g1→f3");
        }

        Juego prueba3 = new Juego();
        ResultadoMovimiento r4 = prueba3.realizar_movimiento("h1", "h5");
        if (r4.es_exitoso()) {
            System.out.println("  ERROR: La torre no debería poder saltar el peón");
        }

        System.out.println("  ✔ Todo correcto.");
    }

    // Bucle principal: muestra el tablero y procesa los turnos
    private static void bucle_partida() {
        while (true) {

            // Dibujar el tablero actualizado
            juego.get_tablero().mostrar();

            // Mostrar piezas capturadas
            mostrar_capturas();

            // Mostrar de quién es el turno
            String prompt = juego.get_turno_actual().equals("BLANCO")
                          ? "Turno BLANCAS ♙ > "
                          : "Turno NEGRAS  ♟ > ";
            System.out.print(prompt);

            // Leer la entrada del usuario
            String linea = scanner.nextLine().trim();
            if (linea.isEmpty()) continue;

            procesar_entrada(linea);
        }
    }

    // Interpreta la entrada del usuario: comando o movimiento
    private static void procesar_entrada(String linea) {
        String[] partes = linea.split("\\s+");
        String comando  = partes[0].toLowerCase();

        switch (comando) {

            // Salir del programa
            case "salir":
                System.out.println("Saliendo del juego...");
                System.exit(0);
                break;

            // Reiniciar la partida
            case "nuevo":
                juego.iniciar_nueva_partida();
                System.out.println("Nueva partida iniciada.");
                break;

            // Ver historial de movimientos
            case "historial":
                mostrar_historial();
                break;

            // Intentar realizar un movimiento
            default:
                procesar_movimiento(partes);
                break;
        }
    }

    // Procesa un intento de movimiento con origen y destino
    private static void procesar_movimiento(String[] partes) {

        // Verificar que se escribieron origen y destino
        if (partes.length < 2) {
            System.out.println("Escribe el origen y el destino. Ejemplo: e2 e4");
            return;
        }

        String origen  = partes[0];
        String destino = partes[1];

        // Realizar el movimiento y mostrar el resultado
        ResultadoMovimiento resultado = juego.realizar_movimiento(origen, destino);

        if (resultado.es_exitoso()) {
            System.out.println("✔ " + resultado.get_mensaje());
        } else {
            System.out.println("❌ " + resultado.get_mensaje());
        }
    }

    // Muestra las piezas capturadas por cada bando
    private static void mostrar_capturas() {
        List<Pieza> cap_blancas = juego.get_tablero().get_capturas_blancas();
        List<Pieza> cap_negras  = juego.get_tablero().get_capturas_negras();

        if (!cap_blancas.isEmpty()) {
            System.out.print("  Capturadas por blancas: ");
            cap_blancas.forEach(p -> System.out.print(p.get_simbolo() + " "));
            System.out.println();
        }

        if (!cap_negras.isEmpty()) {
            System.out.print("  Capturadas por negras:  ");
            cap_negras.forEach(p -> System.out.print(p.get_simbolo() + " "));
            System.out.println();
        }
    }

    // Muestra el historial de movimientos realizados
    private static void mostrar_historial() {
        List<String> hist = juego.get_historial();

        if (hist.isEmpty()) {
            System.out.println("  (Sin movimientos aún)");
            return;
        }

        System.out.println("\n  Historial de movimientos:");
        for (int i = 0; i < hist.size(); i++) {
            System.out.printf("  %2d. %s%n", i + 1, hist.get(i));
        }
        System.out.println();
    }

    // Muestra la pantalla de bienvenida al iniciar el programa
    private static void mostrar_bienvenida() {
        System.out.println();
        System.out.println("  ╔══════════════════════════════════════════════════╗");
        System.out.println("  ║            ♔  AJEDREZ EN JAVA  ♚               ║");
        System.out.println("  ║                                                  ║");
        System.out.println("  ║  Comandos disponibles:                          ║");
        System.out.println("  ║   e2 e4       Mover pieza                       ║");
        System.out.println("  ║   historial   Ver movimientos realizados         ║");
        System.out.println("  ║   nuevo       Reiniciar la partida               ║");
        System.out.println("  ║   salir       Salir del programa                 ║");
        System.out.println("  ╚══════════════════════════════════════════════════╝");
        System.out.println();
    }
}
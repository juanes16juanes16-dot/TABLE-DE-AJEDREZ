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
        Pruebas.ejecutar();
        mostrar_bienvenida();
        bucle_partida();
        System.out.println("Hasta pronto");
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

            // Reiniciar la partida
            case "nuevo":
                juego.iniciar_nueva_partida();
                System.out.println("Nueva partida iniciada.");
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

            // Si el Rey fue capturado, avisar que pueden reiniciar
            if (resultado.get_mensaje().contains("ganan")) {
                System.out.println("\n  Escribe 'nuevo' para jugar otra partida.");
            }
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

    // Muestra la pantalla de bienvenida al iniciar el programa
    private static void mostrar_bienvenida() {
        System.out.println();
        System.out.println("  ╔══════════════════════════════════════════════════╗");
        System.out.println("  ║            ♔  AJEDREZ EN JAVA  ♚               ║");
        System.out.println("  ║                                                  ║");
        System.out.println("  ║  Comandos disponibles:                          ║");
        System.out.println("  ║   e2 e4       Mover pieza                       ║");
        System.out.println("  ║   nuevo       Reiniciar la partida               ║");
        System.out.println("  ╚══════════════════════════════════════════════════╝");
        System.out.println();
    }
}
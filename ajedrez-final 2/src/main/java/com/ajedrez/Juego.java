package com.ajedrez;

import java.util.ArrayList;
import java.util.List;

public class Juego {

    // Tablero actual de la partida
    private Tablero tablero;

    // Turno actual: "BLANCO" o "NEGRO"
    private String turno_actual;

    // Historial de movimientos realizados
    private List<String> historial;

    // Constructor: inicia una nueva partida
    public Juego() {
        iniciar_nueva_partida();
    }

    // Reinicia la partida al estado inicial
    public void iniciar_nueva_partida() {
        tablero      = new Tablero();
        turno_actual = "BLANCO";   // las blancas siempre empiezan
        historial    = new ArrayList<>();
    }

    public Tablero get_tablero()        { 
        return tablero; 
    }

    public String get_turno_actual()    { 
        return turno_actual; 
    }

    public List<String> get_historial() { 
        return historial; 
    }

    // Intenta realizar un movimiento en notación algebraica (ejemplo:"e2" → "e4")
    public ResultadoMovimiento realizar_movimiento(String origen, String destino) {

        // Parsear las casillas a coordenadas internas
        int[] pos_o, pos_d;
        try {
            pos_o = parsear_casilla(origen);
            pos_d = parsear_casilla(destino);
        } catch (IllegalArgumentException e) {
            return new ResultadoMovimiento(false, "Notación inválida: " + e.getMessage());
        }

        int fila_o = pos_o[0], col_o = pos_o[1];
        int fila_d = pos_d[0], col_d = pos_d[1];

        Pieza pieza = tablero.get_pieza(fila_o, col_o);

        // Comprobar que hay una pieza en el origen
        if (pieza == null) {
            return new ResultadoMovimiento(false,
                "No hay ninguna pieza en la casilla " + origen + ".");
        }

        // Comprobar que la pieza es del jugador actual
        if (!pieza.get_color().equals(turno_actual)) {
            String bando = turno_actual.equals("BLANCO") ? "BLANCAS" : "NEGRAS";
            return new ResultadoMovimiento(false,
                "Es el turno de las " + bando + ".");
        }

        // Validar el movimiento con la lógica de la pieza
        if (!pieza.es_movimiento_valido(fila_o, col_o, fila_d, col_d, tablero)) {
            return new ResultadoMovimiento(false,
                "Movimiento no válido para el " + pieza.get_nombre() + ".");
        }

        // Ejecutar el movimiento
        String descripcion = ejecutar_movimiento(fila_o, col_o, fila_d, col_d);
        cambiar_turno(origen + " → " + destino + " (" + descripcion + ")");

        return new ResultadoMovimiento(true, descripcion);
    }

    // Ejecuta el movimiento ya validado: captura si hay pieza enemiga y mueve la pieza
    private String ejecutar_movimiento(int fila_o, int col_o, int fila_d, int col_d) {
        Pieza pieza     = tablero.get_pieza(fila_o, col_o);
        Pieza capturada = tablero.get_pieza(fila_d, col_d);
        String descripcion;

        // Si hay una pieza enemiga en el destino, capturarla
        if (capturada != null) {
            tablero.agregar_captura(capturada);
            descripcion = pieza.get_nombre() + " captura " + capturada.get_nombre();
        } else {
            descripcion = pieza.get_nombre() + ": " + a_notacion(fila_o, col_o)
                        + " → " + a_notacion(fila_d, col_d);
        }

        // Mover la pieza al destino y vaciar el origen
        tablero.set_pieza(fila_d, col_d, pieza);
        tablero.set_pieza(fila_o, col_o, null);
        pieza.set_se_movio(true);

        return descripcion;
    }

    // Alterna el turno y guarda el movimiento en el historial
    private void cambiar_turno(String descripcion_movimiento) {
        String bando = turno_actual.equals("BLANCO") ? "Blancas" : "Negras";
        historial.add(bando + ": " + descripcion_movimiento);
        turno_actual = turno_actual.equals("BLANCO") ? "NEGRO" : "BLANCO";
    }

    // Convierte notación algebraica ("e2") a coordenadas internas [fila, col]
    public int[] parsear_casilla(String casilla) {
        if (casilla == null || casilla.length() != 2) {
            throw new IllegalArgumentException("Formato inválido (ejemplo correcto: e2).");
        }

        char letra = Character.toLowerCase(casilla.charAt(0));
        char rango = casilla.charAt(1);

        if (letra < 'a' || letra > 'h') {
            throw new IllegalArgumentException("Columna '" + letra + "' fuera de rango (a-h).");
        }
        if (rango < '1' || rango > '8') {
            throw new IllegalArgumentException("Fila '" + rango + "' fuera de rango (1-8).");
        }

        int fila = 8 - Character.getNumericValue(rango);
        int col  = letra - 'a';

        return new int[]{fila, col};
    }

    // Convierte coordenadas internas [fila, col] a notación algebraica ("e2")
    public String a_notacion(int fila, int col) {
        return "" + (char)('a' + col) + (8 - fila);
    }
}
package com.ajedrez;

public class Peon extends Pieza {

    // Llama al constructor de Pieza y guarda el color
    public Peon(String color) {
        super(color);
    }

    // Devuelve el símbolo según el color
    @Override
    public String get_simbolo() {
        if (color.equals("BLANCO")) {
            return "♙";
        } else {
            return "♟";
        }
    }

    // Devuelve el nombre de la pieza
    @Override
    public String get_nombre() {
        return "Peón";
    }

    // Los blancos avanzan hacia filas menores (-1) y los negros hacia filas mayores (+1)
    public int get_direccion() {
        return color.equals("BLANCO") ? -1 : 1;
    }

    // Devuelve la fila desde la que el peón puede avanzar dos casillas
    public int get_fila_inicial() {
        return color.equals("BLANCO") ? 6 : 1;
    }

    // Verifica si el movimiento es válido para el peón
    @Override
    public boolean es_movimiento_valido(int fila_o, int col_o, int fila_d, int col_d, Tablero tablero) {

        // Comprueba que las coordenadas están entre 0 y 7
        if (!es_casilla_valida(fila_d, col_d)) {
            return false;
        }

        int dir = get_direccion();

        // Avance de 1 casilla hacia adelante: misma columna y destino vacío
        if (col_d == col_o && fila_d == fila_o + dir) {
            return casilla_libre(fila_d, col_d, tablero);
        }

        // Avance de 2 casillas desde la fila inicial: ambas casillas deben estar vacías
        if (col_d == col_o && fila_o == get_fila_inicial() && fila_d == fila_o + 2 * dir) {
            return casilla_libre(fila_o + dir, col_o, tablero)
                && casilla_libre(fila_d, col_d, tablero);
        }

        // Captura diagonal: 1 columna de diferencia y 1 fila adelante
        if (Math.abs(col_d - col_o) == 1 && fila_d == fila_o + dir) {

            // Captura normal: hay pieza enemiga en el destino
            if (puede_capturar(fila_d, col_d, tablero)) {
                return true;
            }

            // Captura al paso: el destino coincide con la casilla de en passant del tablero
            int[] casilla_ep = tablero.get_casilla_en_passant();
            if (casilla_ep != null && casilla_ep[0] == fila_d && casilla_ep[1] == col_d) {
                return true;
            }
        }

        return false;
    }
}
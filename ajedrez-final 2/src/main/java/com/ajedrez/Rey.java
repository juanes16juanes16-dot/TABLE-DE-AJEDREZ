package com.ajedrez;

// Entidad hija de Pieza
public class Rey extends Pieza {

    // Llama al constructor de Pieza y guarda el color
    public Rey(String color) {
        super(color);
    }

    // Devuelve el símbolo según el color
    @Override
    public String get_simbolo() {
        if (color.equals("BLANCO")) {
            return "♔";
        } else {
            return "♚";
        }
    }

    // Devuelve el nombre de la pieza
    @Override
    public String get_nombre() {
        return "Rey";
    }

    // Verifica si el movimiento es válido para el rey
    @Override
    public boolean es_movimiento_valido(int fila_o, int col_o, int fila_d, int col_d, Tablero tablero) {

        // Comprueba si se está moviendo a una casilla diferente
        if (fila_o == fila_d && col_o == col_d) {
            return false;
        }

        // Comprueba que las coordenadas están entre 0 y 7
        if (!es_casilla_valida(fila_d, col_d)) {
            return false;
        }

        // Devuelve si una casilla está vacía o contiene una pieza enemiga
        if (!casilla_libre_o_enemiga(fila_d, col_d, tablero)) {
            return false;
        }

        int dif_fila = Math.abs(fila_d - fila_o);
        int dif_col  = Math.abs(col_d  - col_o);

        // El rey solo puede moverse una casilla en cualquier dirección
        return dif_fila <= 1 && dif_col <= 1;
    }
}
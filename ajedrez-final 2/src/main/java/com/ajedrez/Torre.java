package com.ajedrez;

// Entidad hija de Pieza
public class Torre extends Pieza {

    // Llama al constructor de Pieza y guarda el color
    public Torre(String color) {
        super(color);
    }

    // Devuelve el símbolo según el color
    @Override
    public String get_simbolo() {
        if (color.equals("BLANCO")) {
            return "♖";
        } else {
            return "♜";
        }
    }

    // Devuelve el nombre de la pieza
    @Override
    public String get_nombre() {
        return "Torre";
    }

    // Verifica si el movimiento es válido para la torre
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

        // Movimiento horizontal: misma fila, recorre las columnas intermedias
        if (fila_o == fila_d) {
            int col_min = Math.min(col_o, col_d);
            int col_max = Math.max(col_o, col_d);
            for (int c = col_min + 1; c < col_max; c++) {
                if (!casilla_libre(fila_o, c, tablero)) return false;
            }
            return true;
        }

        // Movimiento vertical: misma columna, recorre las filas intermedias
        if (col_o == col_d) {
            int fila_min = Math.min(fila_o, fila_d);
            int fila_max = Math.max(fila_o, fila_d);
            for (int f = fila_min + 1; f < fila_max; f++) {
                if (!casilla_libre(f, col_o, tablero)) return false;
            }
            return true;
        }

        // Si no es ni horizontal ni vertical, movimiento inválido
        return false;
    }
}
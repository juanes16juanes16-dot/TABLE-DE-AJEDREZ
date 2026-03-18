package com.ajedrez;

public class Caballo extends Pieza {

    // Llama al constructor de Pieza y guarda el color
    public Caballo(String color) {
        super(color);
    }

    // Devuelve el símbolo según el color
    @Override
    public String get_simbolo() {
        if (color.equals("BLANCO")) {
            return "♘";
        } else {
            return "♞";
        }
    }

    // Devuelve el nombre de la pieza
    @Override
    public String get_nombre() {
        return "Caballo";
    }

    // Verifica si el movimiento es válido para el caballo
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

        // Diferencia de filas y columnas en valor absoluto
        int dif_fila = Math.abs(fila_d - fila_o);
        int dif_col  = Math.abs(col_d  - col_o);

        // Movimiento en L: (2,1) o (1,2). El caballo salta, no hay casillas intermedias que comprobar
        boolean movimiento_L = (dif_fila == 2 && dif_col == 1) || (dif_fila == 1 && dif_col == 2);

        return movimiento_L;
    }
}
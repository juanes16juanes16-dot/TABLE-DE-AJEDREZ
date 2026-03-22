package com.ajedrez;

// Entidad hija de Pieza
public class Reina extends Pieza {

    // La reina delega su movimiento en Torre y Alfil para no repetir lógica
    private final Torre torre_delegada;
    private final Alfil alfil_delegado;

    // Llama al constructor de Pieza y crea los delegados del mismo color
    public Reina(String color) {
        super(color);
        torre_delegada = new Torre(color);
        alfil_delegado = new Alfil(color);
    }

    // Devuelve el símbolo según el color
    @Override
    public String get_simbolo() {
        if (color.equals("BLANCO")) {
            return "♕";
        } else {
            return "♛";
        }
    }

    // Devuelve el nombre de la pieza
    @Override
    public String get_nombre() {
        return "Reina";
    }

    // El movimiento es válido si lo es para la Torre (recto) o para el Alfil (diagonal)
    @Override
    public boolean es_movimiento_valido(int fila_o, int col_o, int fila_d, int col_d, Tablero tablero) {
        return torre_delegada.es_movimiento_valido(fila_o, col_o, fila_d, col_d, tablero) || alfil_delegado.es_movimiento_valido(fila_o, col_o, fila_d, col_d, tablero);
    }
}
package com.ajedrez;

// Clase abstracta base de todas las piezas del juego
public abstract class Pieza implements Movible {

    // Color de la pieza: "BLANCO" o "NEGRO"
    protected final String color;

    // Indica si la pieza ya se ha movido al menos una vez
    protected boolean se_movio;

    // Crea una pieza del color indicado, aún sin moverse
    public Pieza(String color) {
        this.color    = color;
        this.se_movio = false;
    }

    // Devuelve el color de la pieza
    public String get_color() {
        return color;
    }

    // Indica si la pieza ya se movió
    public boolean get_se_movio() {
        return se_movio;
    }

    // Marca la pieza como movida o no movida
    public void set_se_movio(boolean valor) {
        this.se_movio = valor;
    }

    // Cada subclase devuelve su símbolo Unicode para mostrar en consola
    public abstract String get_simbolo();

    // Cada subclase devuelve su nombre en español
    public abstract String get_nombre();

    // Comprueba que las coordenadas están dentro del tablero (0-7)
    protected boolean es_casilla_valida(int fila, int col) {
        return fila >= 0 && fila < 8 && col >= 0 && col < 8;
    }

    // Comprueba si hay una pieza enemiga en la casilla (capturable)
    protected boolean puede_capturar(int fila, int col, Tablero tablero) {
        Pieza objetivo = tablero.get_pieza(fila, col);
        return objetivo != null && !objetivo.get_color().equals(this.color);
    }

    // Comprueba si la casilla está vacía
    protected boolean casilla_libre(int fila, int col, Tablero tablero) {
        return tablero.get_pieza(fila, col) == null;
    }

    // Comprueba si la casilla está vacía o contiene una pieza enemiga
    protected boolean casilla_libre_o_enemiga(int fila, int col, Tablero tablero) {
        Pieza objetivo = tablero.get_pieza(fila, col);
        return objetivo == null || !objetivo.get_color().equals(this.color);
    }

    // Devuelve el símbolo de la pieza al imprimir el tablero
    @Override
    public String toString() {
        return get_simbolo();
    }
}
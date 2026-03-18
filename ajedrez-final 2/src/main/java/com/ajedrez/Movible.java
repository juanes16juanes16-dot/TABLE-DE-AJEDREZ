package com.ajedrez;

// Interfaz que deben implementar todas las piezas del juego
public interface Movible {

    // Comprueba si el movimiento desde el origen hasta el destino es válido para la pieza
    boolean es_movimiento_valido(int fila_o, int col_o,
                                 int fila_d, int col_d,
                                 Tablero tablero);
}
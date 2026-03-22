package com.ajedrez;

import java.util.ArrayList;
import java.util.List;

public class Tablero {

    // Cuadrícula del tablero: casillas[fila][col] contiene la pieza o null si está vacía
    private final Pieza[][] casillas;

    // Listas dinámicas de piezas capturadas por cada bando (ArrayList de tamaño variable)
    private final List<Pieza> capturas_blancas;
    private final List<Pieza> capturas_negras;

    // Casilla objetivo para la captura al paso, null si no hay en passant disponible
    private int[] casilla_en_passant;

    // Crea el tablero con todas las piezas en la posición inicial
    public Tablero() {
        casillas           = new Pieza[8][8];
        capturas_blancas   = new ArrayList<>();
        capturas_negras    = new ArrayList<>();
        casilla_en_passant = null;
        colocar_piezas_iniciales();
    }

    // Coloca todas las piezas en su posición inicial
    private void colocar_piezas_iniciales() {

        // Piezas mayores negras (fila 0 = rango 8)
        casillas[0][0] = new Torre("NEGRO");
        casillas[0][1] = new Caballo("NEGRO");
        casillas[0][2] = new Alfil("NEGRO");
        casillas[0][3] = new Reina("NEGRO");
        casillas[0][4] = new Rey("NEGRO");
        casillas[0][5] = new Alfil("NEGRO");
        casillas[0][6] = new Caballo("NEGRO");
        casillas[0][7] = new Torre("NEGRO");

        // Peones negros (fila 1 = rango 7)
        for (int c = 0; c < 8; c++) {
            casillas[1][c] = new Peon("NEGRO");
        }

        // Peones blancos (fila 6 = rango 2)
        for (int c = 0; c < 8; c++) {
            casillas[6][c] = new Peon("BLANCO");
        }

        // Piezas mayores blancas (fila 7 = rango 1)
        casillas[7][0] = new Torre("BLANCO");
        casillas[7][1] = new Caballo("BLANCO");
        casillas[7][2] = new Alfil("BLANCO");
        casillas[7][3] = new Reina("BLANCO");
        casillas[7][4] = new Rey("BLANCO");
        casillas[7][5] = new Alfil("BLANCO");
        casillas[7][6] = new Caballo("BLANCO");
        casillas[7][7] = new Torre("BLANCO");
    }

    // Devuelve la pieza en la casilla indicada, o null si está vacía
    public Pieza get_pieza(int fila, int col) {
        if (fila < 0 || fila > 7 || col < 0 || col > 7) return null;
        return casillas[fila][col];
    }

    // Coloca o elimina una pieza en la casilla indicada
    public void set_pieza(int fila, int col, Pieza pieza) {
        casillas[fila][col] = pieza;
    }

    // Devuelve la casilla objetivo de captura al paso, o null si no hay
    public int[] get_casilla_en_passant() {
        return casilla_en_passant;
    }

    // Establece la casilla objetivo para la captura al paso
    public void set_casilla_en_passant(int[] casilla) {
        this.casilla_en_passant = casilla;
    }

    // Devuelve la lista de piezas capturadas por las blancas
    public List<Pieza> get_capturas_blancas() {
        return capturas_blancas;
    }

    // Devuelve la lista de piezas capturadas por las negras
    public List<Pieza> get_capturas_negras() {
        return capturas_negras;
    }

    // Registra una pieza capturada en la lista del bando capturador
    public void agregar_captura(Pieza pieza) {
        if (pieza.get_color().equals("BLANCO")) {
            // Una pieza blanca fue capturada, la guarda el bando negro
            capturas_negras.add(pieza);
        } else {
            // Una pieza negra fue capturada, la guarda el bando blanco
            capturas_blancas.add(pieza);
        }
    }

    // Muestra el tablero en consola con notación algebraica
    public void mostrar() {
        System.out.println();
        System.out.println("     a    b    c    d    e    f    g    h");
        System.out.println("   +----+----+----+----+----+----+----+----+");

        for (int f = 0; f < 8; f++) {
            int rango = 8 - f;
            System.out.print(" " + rango + " |");

            for (int c = 0; c < 8; c++) {
                Pieza pieza = casillas[f][c];

                if (pieza != null) {
                    System.out.print(" " + pieza.get_simbolo() + "  |");
                } else {
                    boolean es_clara = (f + c) % 2 == 0;
                    System.out.print(es_clara ? "    |" : "  · |");
                }
            }

            System.out.println(" " + rango);
            System.out.println("   +----+----+----+----+----+----+----+----+");
        }

        System.out.println("     a    b    c    d    e    f    g    h");
        System.out.println();
    }
}
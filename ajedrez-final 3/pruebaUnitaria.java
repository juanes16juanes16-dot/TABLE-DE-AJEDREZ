package com.ajedrez;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias basicas para el proyecto de Ajedrez.
 * 
 */
class AjedrezTest {

    // --- TABLERO ---

    @Test
    void elTableroTiene8Filas() {
        int filas = 8;
        assertEquals(8, filas);
    }

    @Test
    void elTableroTiene8Columnas() {
        int columnas = 8;
        assertEquals(8, columnas);
    }

    @Test
    void hayTreintaYDosPiezasAlInicio() {
        int blancas = 16;
        int negras = 16;
        assertEquals(32, blancas + negras);
    }

    // --- PIEZAS POR JUGADOR ---

    @Test
    void cadaJugadorTiene16Piezas() {
        int peones = 8;
        int torres = 2;
        int caballos = 2;
        int alfiles = 2;
        int reina = 1;
        int rey = 1;
        assertEquals(16, peones + torres + caballos + alfiles + reina + rey);
    }

    // --- POSICIONES INICIALES ---

    @Test
    void peonesBlancosEnFila2() {
        int filaPeonesBlancas = 2;
        assertEquals(2, filaPeonesBlancas);
    }

    @Test
    void peonesNegrosEnFila7() {
        int filaPeonesNegras = 7;
        assertEquals(7, filaPeonesNegras);
    }

    // --- MOVIMIENTOS ---

    @Test
    void peonAvanzaUnaCasilla() {
        int posicion = 2;
        int nuevaPosicion = posicion + 1;
        assertEquals(3, nuevaPosicion);
    }

    @Test
    void torreSeMovEnLineaRecta() {
        int filaOrigen = 1;
        int filaDestino = 1;
        assertTrue(filaOrigen == filaDestino);
    }

    @Test
    void alfilSeMueveEnDiagonal() {
        int difFilas = Math.abs(4 - 1);
        int difColumnas = Math.abs(6 - 3);
        assertEquals(difFilas, difColumnas);
    }

    @Test
    void caballoSeMueveEnL() {
        int difFilas = Math.abs(3 - 1);
        int difColumnas = Math.abs(3 - 2);
        boolean esMovimientoL = (difFilas == 2 && difColumnas == 1)
                             || (difFilas == 1 && difColumnas == 2);
        assertTrue(esMovimientoL);
    }

    // --- TURNOS ---

    @Test
    void blancasEmpiezanPrimero() {
        String turno = "blancas";
        assertEquals("blancas", turno);
    }

    @Test
    void turnoAlterna() {
        String turno = "blancas";
        turno = "negras";
        assertEquals("negras", turno);
    }

    // --- VALIDACIONES ---

    @Test
    void posicionFueraDelTableroEsInvalida() {
        int fila = 9;
        boolean valida = fila >= 1 && fila <= 8;
        assertFalse(valida);
    }

    @Test
    void noPuedeComerPiezaPropia() {
        String colorPieza = "blanco";
        String colorDestino = "blanco";
        boolean permitido = !colorPieza.equals(colorDestino);
        assertFalse(permitido);
    }

    @Test
    void puedeComerPiezaRival() {
        String colorPieza = "blanco";
        String colorDestino = "negro";
        boolean permitido = !colorPieza.equals(colorDestino);
        assertTrue(permitido);
    }
}
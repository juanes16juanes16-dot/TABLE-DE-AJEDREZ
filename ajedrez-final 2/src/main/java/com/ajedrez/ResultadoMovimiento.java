package com.ajedrez;

// Guarda el resultado de intentar realizar un movimiento
public class ResultadoMovimiento {

    // true si el movimiento fue válido y se ejecutó, false si fue rechazado
    private final boolean exitoso;

    // Mensaje descriptivo del resultado para mostrar al jugador
    private final String mensaje;

    // Crea el resultado con su estado y mensaje
    public ResultadoMovimiento(boolean exitoso, String mensaje) {
        this.exitoso = exitoso;
        this.mensaje = mensaje;
    }

    // Indica si el movimiento se ejecutó correctamente
    public boolean es_exitoso() {
        return exitoso;
    }

    // Devuelve el mensaje del resultado
    public String get_mensaje() {
        return mensaje;
    }
}
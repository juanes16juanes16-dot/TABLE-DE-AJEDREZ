package com.ajedrez;

public class Pruebas {

    // Ejecuta todas las pruebas y muestra el resultado en consola
    public static void ejecutar() {
        System.out.println("  Verificando funcionalidad...");

        int errores = 0;

        // El peón blanco debe poder avanzar una casilla
        Juego prueba1 = new Juego();
        if (!prueba1.realizar_movimiento("e2", "e3").es_exitoso()) {
            System.out.println("  ERROR: El peon deberia poder avanzar e2 a e3");
            errores++;
        }

        // El peón blanco debe poder avanzar dos casillas desde la posición inicial
        Juego prueba2 = new Juego();
        if (!prueba2.realizar_movimiento("e2", "e4").es_exitoso()) {
            System.out.println("  ERROR: El peon deberia poder avanzar e2 a e4");
            errores++;
        }

        // No se puede mover una pieza del bando incorrecto
        Juego prueba3 = new Juego();
        if (prueba3.realizar_movimiento("e7", "e5").es_exitoso()) {
            System.out.println("  ERROR: No deberia poder mover pieza negra en turno de blancas");
            errores++;
        }

        // El caballo debe poder moverse en L
        Juego prueba4 = new Juego();
        if (!prueba4.realizar_movimiento("g1", "f3").es_exitoso()) {
            System.out.println("  ERROR: El caballo deberia poder moverse g1 a f3");
            errores++;
        }

        // La torre no puede saltar por encima del peón
        Juego prueba5 = new Juego();
        if (prueba5.realizar_movimiento("h1", "h5").es_exitoso()) {
            System.out.println("  ERROR: La torre no deberia poder saltar el peon");
            errores++;
        }

        // Los turnos deben alternarse correctamente
        Juego prueba6 = new Juego();
        prueba6.realizar_movimiento("e2", "e4");
        if (!prueba6.get_turno_actual().equals("NEGRO")) {
            System.out.println("  ERROR: Tras mover blancas deberia ser turno de negras");
            errores++;
        }

        // La captura debe registrarse en la lista de capturas
        Juego prueba7 = new Juego();
        prueba7.realizar_movimiento("e2", "e4");
        prueba7.realizar_movimiento("d7", "d5");
        prueba7.realizar_movimiento("e4", "d5");
        if (prueba7.get_tablero().get_capturas_blancas().isEmpty()) {
            System.out.println("  ERROR: La lista de capturas deberia tener una pieza");
            errores++;
        }

        // Mostrar resultado final
        if (errores == 0) {
            System.out.println("  Todo correcto. (7/7 pruebas pasadas)");
        } else {
            System.out.println("  " + errores + " prueba(s) fallaron.");
        }
    }
}
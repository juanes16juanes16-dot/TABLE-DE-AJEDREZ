package com.ajedrez;

//Entidad Hija de pieza 
public class Alfil extends Pieza {

    //Aqui llama al constructor de pieza y guarde el color
    public Alfil(String color) {
        super(color);
    }
    
    //Se utliza para que devuelva el mismo simbolo en caso blanco o negro 
    @Override
    public String get_simbolo() {
        if (color.equals("BLANCO")) {
            return "♗";
        } else {
            return "♝";
        }   
    }

    //Devuelve el nombre de la pieza 
    @Override
    public String get_nombre() {
        return "Alfil";
    }

    //Esta parte es para verificar si elo movimiento es valido o no es valido
    //los primeros dos int es para la posicion actual del alfil y los dos siguientes es para donde quiere moverse
    //tablero para ver si existen piezas en el camino 
    @Override
    public boolean es_movimiento_valido(int fila_o, int col_o, int fila_d, int col_d, Tablero tablero) {

        //Comprueba si se esta moviendo a una casilla diferentes 
        if (fila_o == fila_d && col_o == col_d){
            return false;    
        }

        //Comprueba que las coordenadas estan entre el 0 y 7 del tablero      
        if (!es_casilla_valida(fila_d, col_d)){
            return false;
        }

        //Devuelve si una casilla esta vacia o contiene una pieza enemiga
        if (!casilla_libre_o_enemiga(fila_d, col_d, tablero)){
            return false;
        }
        
        // Condición de movimiento diagonal: el desplazamiento en filas y columnas
        if (Math.abs(fila_d - fila_o) != Math.abs(col_d - col_o)){
            return false;   
        }
          
        // Determinar la dirección de avance (+1 o -1) en cada eje 
        int dir_fila = (fila_d > fila_o) ? 1 : -1;
        int dir_col  = (col_d  > col_o)  ? 1 : -1;

        // Recorrer la diagonal desde el origen (sin incluirlo) hasta el destino (sin incluirlo)
        int f = fila_o + dir_fila;
        int c = col_o  + dir_col;

        // Si cualquier casilla intermedia está ocupada, el movimiento es inválido
        while (f != fila_d) {
            if (!casilla_libre(f, c, tablero)) {
                return false;
            }
            f += dir_fila;
            c += dir_col;
        }

        return true;
    }

}

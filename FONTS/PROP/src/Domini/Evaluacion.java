package Domini;



public abstract class Evaluacion {

    /* Pre: Cierto
     * Post: Devuelve un nuevo objeto Evaluacion
     */
    public Evaluacion() {
    }

    /* Pre: Cierto
     * Post: Devuelve un valor int de la puntuacion del nodo en cuestion
     */
    public abstract int evaluar(Tablero tablero, int depth);

}
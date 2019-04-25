package Domini;

public class Movimiento {
    private int fromX;
    private int fromY;
    private int toX;
    private int toY;
    private char p;

    /* Pre: Cierto
     * Post: Crea un nuevo objeto Movimiento con los parametros fromX, fromY, toX, toY
     * */
    Movimiento(int fromX, int fromY, int toX, int toY) {
        this.fromX = fromX;
        this.fromY = fromY;
        this.toX = toX;
        this.toY = toY;
        this.p = '-';
    }

    /* Pre: Cierto
     * Post: Crea un nuevo objeto Movimiento con los parametros fromX, fromY, toX, toY y un char p
     * */
    Movimiento(int fromX, int fromY, int toX, int toY, char p) {
        this.fromX = fromX;
        this.fromY = fromY;
        this.toX = toX;
        this.toY = toY;
        this.p = p;
    }

    /* Pre: Cierto
     * Post: Devuelve un Tablero editado con el nuevo movimiento
     */
    public Tablero intentar(final Tablero iniTablero) {
        Tablero temp = new Tablero(iniTablero);
        temp.ejecutarMovimiento(this);
        temp.setTurnoBlancas(!iniTablero.getTurnoBlancas());
        return temp;
    }

    /* Pre: Cierto
     * Post: Devuelve un boolean que es true si el Movimiento es nulo
     */
    public boolean esNada() {
        if(this.toY == -1 && this.toX == -1 && this.fromY == -1 && this.fromX == -1) return true;
        else return false;
    }

    /* Pre: Cierto
     * Post: Devuelve el valor de Y del origen
     */
    public int getFromY() {
        return fromY;
    }

    /* Pre: Cierto
     * Post: Devuelve el valor de X del origen
     */
    public int getFromX() {
        return fromX;
    }

    /* Pre: Cierto
     * Post: Devuelve el valor de p del destino
     */
    public char getP() {
        return p;
    }

    /* Pre: Cierto
     * Post: Devuelve el valor de X del destino
     */
    public int getToX() {
        return toX;
    }

    /* Pre: Cierto
     * Post: Devuelve el valor de Y del destino
     */
    public int getToY() {
        return toY;
    }

    /* Pre: Cierto
     * Post: Asigna el valor de X del origen
     */
    public void setFromX(int fromX) {
        this.fromX = fromX;
    }

    /* Pre: Cierto
     * Post: Asigna el valor de Y del origen
     */
    public void setFromY(int fromY) {
        this.fromY = fromY;
    }

    /* Pre: Cierto
     * Post: Asigna el valor de p del destino
     */
    public void setP(char p) {
        this.p = p;
    }

    /* Pre: Cierto
     * Post: Asigna el valor de X del destino
     */
    public void setToX(int toX) {
        this.toX = toX;
    }

    /* Pre: Cierto
     * Post: Asigna el valor de Y del destino
     */
    public void setToY(int toY) {
        this.toY = toY;
    }
}
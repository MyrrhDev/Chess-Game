package Domini;

public class Movimiento {
    private int fromX;
    private int fromY;
    private int toX;
    private int toY;
    private char p;

    //Copied from Movimiento Prueba:
    private Tablero aTablero;
    private boolean sePuede;


    //Para el sort:
    private Pieza pieza;

    public Pieza getPieza() {
        return pieza;
    }

    public boolean isEsUnAtaque() {
        return esUnAtaque;
    }

    private boolean esUnAtaque;

    /* Pre: Cierto
     * Post: Crea un nuevo objeto Movimiento con los parametros fromX, fromY, toX, toY
     * */
    Movimiento(Pieza pieza, int fromX, int fromY, int toX, int toY, Tablero tablero) {
        this.pieza = pieza;
        this.fromX = fromX;
        this.fromY = fromY;
        this.toX = toX;
        this.toY = toY;
        this.p = '-';

        this.esUnAtaque = false;
        this.aTablero = tablero;
    }

    /* Pre: Cierto
     * Post: Crea un nuevo objeto Movimiento con los parametros fromX, fromY, toX, toY y un char p
     * */
    Movimiento(Pieza pieza, int fromX, int fromY, int toX, int toY, char p, Tablero tablero) {
        this.pieza = pieza;
        this.fromX = fromX;
        this.fromY = fromY;
        this.toX = toX;
        this.toY = toY;
        this.p = p;

        this.esUnAtaque = true;
        this.aTablero = tablero;

    }

    //Copied from Movimiento Prueba:
    /* Pre: Cierto
     * Post: Devuelve el Tablero al que el movimiento hecho nos lleva del parametro implicito
     */
    public Tablero getaTablero() {
        return aTablero;
    }

    /* Pre: Cierto
     * Post: Devuelve el boolean si el Movimiento del parametro implicito dado se puede hacer
     */
    public boolean isSePuede() {
        return sePuede;
    }

    //MovimientoPrueba
    Movimiento(int fromX, int fromY, int toX, int toY, Tablero aTablero, boolean sePuede) {
        this.fromX = fromX;
        this.fromY = fromY;
        this.toX = toX;
        this.toY = toY;
        this.p = '-';

        this.sePuede = sePuede;
        this.aTablero = aTablero;
    }

//
//    Movimiento(int fromX, int fromY, int toX, int toY, char p, Tablero aTablero) {
//        this.fromX = fromX;
//        this.fromY = fromY;
//        this.toX = toX;
//        this.toY = toY;
//        this.p = p;
//
//        this.aTablero = aTablero;
//    }





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
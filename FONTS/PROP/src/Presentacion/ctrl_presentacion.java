package Presentacion;

public class ctrl_presentacion {
    private static ctrl_presentacion ctrl_presentacion = null;

    private ctrl_presentacion() {

    }

    public static ctrl_presentacion getInstance() {
        if(ctrl_presentacion == null) {
            ctrl_presentacion = new ctrl_presentacion();
            return ctrl_presentacion;
        }
        else return ctrl_presentacion;
    }

    public void controlarPartidaMaquinas(JugarPartidaGUI partidaGUI) { //llamaremos esta función cuando jueguen dos máquinas en el tablero
        while(partidaGUI.getnJugador1() >= 0 || partidaGUI.getnJugador2() >=0) partidaGUI.jugar();
    }

    public static void main(String args[]) {
        new ctrl_presentacion();
        //@TODO: LLAMAR VISTA LOGIN
    }
}

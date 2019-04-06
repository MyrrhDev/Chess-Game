package Domini;

import java.util.ArrayList;

public class Persona extends Jugador {
    String nombre;
    String contrasena;
    int pts;

    static private ArrayList<Persona> jugadores;

    public Persona() {

    }

    /* Pre: Cierto
     * Post: Se crea un objeto Persona con los parámetros nombre y contrasena
     */

    public Persona(String nombre, String contrasena) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.pts = 0;
    }


    /* Pre: Cierto
     * Post: Se crea un objeto Persona con los parámetros
     */
    public Persona(int id, boolean esMaquina, String nombre, String contrasena, int pts) {
        setId(id);
        setEsMaquina(esMaquina);

        this.nombre = nombre;
        this.contrasena = contrasena;
        this.pts = pts;
    }

    /* Función: devuelve true si la contraseña del objeto es el mismo que la contraseña que se
    le pasa por parámetro. Sino devuelve false.*/

    boolean contrasenaIsEqual(String contrasena) {
        if(contrasena == this.contrasena) return true;
        else return false;
    }


    /* Función: devuelve true si el nombre del objeto es el mismo que el nombre que se le
    pasa por parámetro. Sino devuelve false. */

    boolean nombreIsEqual(String nombre) {
        if(nombre == this.nombre) return true;
        else return false;
    }


/*
    boolean verificarLoginPersona() {

        return false;
    }*/

    public void cambiarNombrePersona(String newName) {
        this.nombre = newName;
    }


    public int verificarDatosUsuario(String nombre, String contrasena) {
            for(int i = 0; i < jugadores.size(); ++i) {
                if (jugadores.get(i).nombreIsEqual(nombre)) {
                    if (jugadores.get(i).contrasenaIsEqual(contrasena)) return 1;
                    else return -2;
                } else return -1;
            }
        return 0;
    }
/*
    public void verificarLoginH2(String nombre, String contrasena) {
        //ver si existe un usuario con el mismo nombre en la lista
        int c = (nombre, contrasena);
        switch(c) {
            case 1:
                login = true;
                for(int i = 0; i < jugadores.size(); ++i)
                    if(jugadores.get(i).nombreIsEqual(nombre)) personaLogueada = jugadores.get(i);
                break;
            case -1:
                System.out.println("Nombre no valido");
                break;
            default:
                System.out.println("Contrasena no valida");
                break;
        }
    }
*/


    public void actualizarPuntuacionPersona(int newPoints) {
        this.pts += newPoints;
    }

    /*
    void nuevoProblema() {

    }

    void listarProblemas() {

    }

    void abandonarPartida() {}

    void editarProblema() {

    }

    void borrarProblema() {

    }*/



}

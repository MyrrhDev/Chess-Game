package Domini;

import java.util.ArrayList;

public class Persona extends Jugador {
    String nombre;
    String contrasena;
    int pts;
    ArrayList<Problema> tieneProblema;

    //creadora
    public Persona(String nombre, String contrasena) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        tieneProblema = new ArrayList<Problema>();
    }

    //creadora
    public Persona() {

    }

    //En esta funcion vamos a comparar el objeto actual con el objeto que nos pasan por parámetro para
    //verificar que el nombre de usuario es el mismo
    private boolean compararNombreUsuario(Persona p) {
        return false;
    }

    //En esta funcion vamos a comparar el objeto actual con el objeto que nos pasan por parámetro para
    //verificar que la contraseña es la misma
    private boolean compararContrasenaUsuario(Persona p) {
        return false;
    }

    //Se crea un nuevo problema vacio y se le añade a la arrayList de problemas que tiene la persona
    public void nuevoProblema() {

    }

    //Se listan los problemas disponibles y se borra el problema que el jugador ha seleccionado si
    //previamente no ha sido jugado
    public void borrarProblema() {
        //ver seHaJugadoProblema en la clase Domini.Problema
    }

    //se listan los problemas y se elije que problema se quiere editar. Se edita cuando el sistema verifica
    //que el problema no ha sido jugado
    public void editarProblema() {
        //ver seHaJugadoProblema en la clase Domini.Problema
    }

    //se listan los problemas de la persona
    public void listarProblema() {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public int getPts() {
        return pts;
    }

    public void setPts(int pts) {
        this.pts = pts;
    }

    public ArrayList<Problema> getTieneProblema() {
        return tieneProblema;
    }

    public void setTieneProblema(ArrayList<Problema> tieneProblema) {
        this.tieneProblema = tieneProblema;
    }

}

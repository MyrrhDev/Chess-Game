package Domini;

import java.util.ArrayList;
import java.util.HashMap;

public class ctrl_dominio {
    static private ArrayList<Persona> jugadores;
    static private Persona personaLogueada;
    static private HashMap<String, Problema> problemasExistentes;
    static private Ranking ranking;
    static private boolean login;
    private static ctrl_dominio singleInstance = null;

    private ctrl_dominio() {
        login = false;
        jugadores = new ArrayList<>();
        problemasExistentes = new HashMap<>();
        ranking = new Ranking();
    }

    public static ctrl_dominio getInstance() {
        if(singleInstance == null) singleInstance = new ctrl_dominio();
        return singleInstance;
    }

    public int jugadoresSize() {
        return jugadores.size();
    }

    /*
      Pre: Cierto
      Post: La funcion retorna:
          1 Si nombre usuario y contraseña OK
         -1 Si el nombre no es valido
         -2 Si la contrasea no es valido
     */
    private int verificarDatosUsuario(String nombre, String contrasena) {
        for(int i = 0; i < jugadores.size(); ++i) {
            if (jugadores.get(i).nombreIsEqual(nombre)) {
                if (jugadores.get(i).contrasenaIsEqual(contrasena)) return 1;
                else return -2;
            }
            else return -1;
        }
        return -1;
    }

    /* Pre: Cierto
     * Post: Si la persona se encontraba registrada en el sistema y el usuario y contraseña coinciden
     * la funcion cambia el atributo login de la clase a cierto. En caso que introduzca incorrectamente
     * la contraseña o la persona no este registrada, la funcion le informara del problema
     */
    //@TODO Cambiar los system out. El controlador no puede hacerlos. Debe informar a la capa de presentación del error
    public void loginPersona(String nombre, String contrasena) {
        //ver si existe un usuario con el mismo nombre en la lista
        int c = verificarDatosUsuario(nombre, contrasena);
        switch(c) {
            case 1:
                login = true;
                for(int i = 0; i < jugadores.size(); ++i)
                    if(jugadores.get(i).nombreIsEqual(nombre)) personaLogueada = jugadores.get(i);
                break;
            case -1:
                System.out.println("Nombre no valido");
                break;
            case -2:
                System.out.println("Contrasena no valida");
                break;
        }
    }

    /* Pre: Cierto
     * Post: El usuario es deslogueado del sistema
     */
    public void logoutPersona() {
        login = false;
        personaLogueada = null;
    }

    /* Pre: Cierto
     * Post: Si no existe ningun otro usuario con el mismo nombre, el usuario se registra en el sistema y la funcion retorna 1
     * En caso que no se pueda registrar, la funcion devuelve -1
     */
    public int nuevaPersona(String nombre, String contrasena) {
        int c = verificarDatosUsuario(nombre, contrasena);
        switch(c) {
            case 1:
                System.out.println("Error! Nombre de usuario ya en uso");
                break;
            default:
                Persona p = new Persona(nombre, contrasena);
                jugadores.add(p);
                return 1;
        }
        return -1;
    }

    /* Pre: La persona está logueada en el sistema
     * Post: Se da de baja la persona en el sistema. Se actualiza:
     *       1. El Ranking
     *       2. Todos los problemas ganados por la persona pasan a estar sin ganador
     */
    /*public void bajaPersona() {
        eliminarPersonaRanking(personaLogueada);
        //hacer relación problema y persona en uml para poder acceder al ganador de dicho problema y poder eliminarlo fácilmente
        for(int i = 0; i < problemasExistentes.size(); ++i) {
            problemasExistentes.get(i).eliminarPersonaGanadora(personaLogueada);
        }
        login = false;
    }*/

    public static void main(String[] args) {
        DriverCaballo dc = new DriverCaballo();
        dc.main(args);
    }

    /*
    =================================== FUNCIONES A IMPLEMENTAR PARA SEGUNDA ENTREGA ===================================
     */
}
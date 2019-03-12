package Domini;

public abstract class Jugador {
    private int id;
    private boolean esMaquina;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isEsMaquina() {
        return esMaquina;
    }

    public void setEsMaquina(boolean esMaquina) {
        this.esMaquina = esMaquina;
    }
}

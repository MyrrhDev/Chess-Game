package Domini;

import java.util.Date;

public class Partida {
    Date fecha; //fecha y hora de la partida
    Jugador ganador;

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Jugador getGanador() {
        return ganador;
    }

    public void setGanador(Jugador ganador) {
        this.ganador = ganador;
    }
}

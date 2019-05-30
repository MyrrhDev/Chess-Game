package Domini.DriversPrimeraEntrega;

public abstract class Estrategia {
    public boolean esSimple;

    void setEsSimple(boolean esSimple) {
        this.esSimple = esSimple;
    }

    boolean getEsSimple() {
        return this.esSimple;
    }

    abstract void estrategiaOfensiva();

    abstract void estrategiaDefensiva();
}

package modelo;

/**
 * Representa una fila de la tabla de division de votos, usada para el informe
 * correspondiente. Incluye todos los cocientes de todos los partidos, indicando
 * si cada uno entra al porcentaje minimo y si ocupo un cargo.
 */
public class DivisionVotos {
    private int id;
    private int fk_idPartido;
    private int orden;
    private double votosPorCargo;
    private int indice;
    private boolean entraPorcentaje;
    private boolean ocupaCargo;
    
    /**
     * @param fk_idPartido identificador del partido al que pertenece esta fila
     * @param orden posicion en el ranking global, 0 si no entra al ranking
     * @param votosPorCargo resultado de dividir los votos del partido por el indice
     * @param indice divisor usado en el calculo (1, 2, 3...)
     * @param entraPorcentaje true si el partido supero el umbral minimo de porcentaje
     * @param ocupaCargo true si este cociente especifico obtuvo un cargo
     */
    public DivisionVotos(int fk_idPartido, int orden, double votosPorCargo, int indice, boolean entraPorcentaje, boolean ocupaCargo) {
        this.fk_idPartido = fk_idPartido;
        this.orden = orden;
        this.votosPorCargo = votosPorCargo;
        this.indice = indice;
        this.entraPorcentaje = entraPorcentaje;
        this.ocupaCargo = ocupaCargo;
    }

    public int getId() {
        return id;
    }

    public int getFk_idPartido() {
        return fk_idPartido;
    }

    public int getOrden() {
        return orden;
    }

    public double getVotosPorCargo() {
        return votosPorCargo;
    }

    public int getIndice() {
        return indice;
    }

    public boolean isEntraPorcentaje() {
        return entraPorcentaje;
    }

    public boolean isOcupaCargo() {
        return ocupaCargo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFk_idPartido(int fk_idPartido) {
        this.fk_idPartido = fk_idPartido;
    }

    public void setOrden(int nroOrden) {
        this.orden = nroOrden;
    }

    public void setVotosPorCargo(double votosPorCargo) {
        this.votosPorCargo = votosPorCargo;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    public void setEntraPorcentaje(boolean entraPorcentaje) {
        this.entraPorcentaje = entraPorcentaje;
    }

    public void setOcupaCargo(boolean ocupaCargo) {
        this.ocupaCargo = ocupaCargo;
    }
}
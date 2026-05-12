package modelo;

/**
 * Representa un cociente del calculo D'Hondt que ocupo un cargo.
 * Almacena el resultado de dividir los votos de un partido por un indice,
 * junto con su posicion en el ranking global.
 */
public class ResultadoVotos {
    private int id;
    private int fk_idPartido;
    private int cantidadVotos;
    private int orden;
    private int indice;
    private double porcentaje;
    private int totalVotos;
    
    /**
     * @param fk_idPartido identificador del partido al que pertenece este cociente
     * @param cantidadVotos resultado de dividir los votos del partido por el indice
     * @param orden posicion en el ranking global de cocientes
     * @param indice divisor usado en el calculo (1, 2, 3...)
     * @param porcentaje porcentaje del partido sobre el total de votos
     * @param totalVotos suma de votos de todos los partidos
     */
    public ResultadoVotos(int fk_idPartido, int cantidadVotos, int orden, int indice, double porcentaje, int totalVotos) {
        this.fk_idPartido = fk_idPartido;
        this.cantidadVotos = cantidadVotos;
        this.orden = orden;
        this.indice = indice;
        this.porcentaje = porcentaje;
        this.totalVotos = totalVotos;
    }

    public int getId() {
        return id;
    }

    public int getFk_idPartido() {
        return fk_idPartido;
    }

    public int getCantidadVotos() {
        return cantidadVotos;
    }

    public int getOrden() {
        return orden;
    }

    public int getIndice() {
        return indice;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public int getTotalVotos() {
        return totalVotos;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFk_idPartido(int fk_idPartido) {
        this.fk_idPartido = fk_idPartido;
    }

    public void setCantidadVotos(int cantidadVotos) {
        this.cantidadVotos = cantidadVotos;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    public void setTotalVotos(int totalVotos) {
        this.totalVotos = totalVotos;
    }

}
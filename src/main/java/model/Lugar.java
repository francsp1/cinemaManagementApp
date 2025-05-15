package model;

public class Lugar {
    private final String designacao;
    private final int fila; //Linha
    private final int numeroLugar; //Coluna
    private boolean isAcessivel;

    public Lugar( int fila, int numeroLugar) {
        this.designacao = ((char) ('A' + fila) + "" + (numeroLugar + 1));
        this.fila = fila;
        this.numeroLugar = numeroLugar;
        this.isAcessivel = false;
    }

    public String getDesignacao() {
        return designacao;
    }


    public int getFila() {
        return fila;
    }

    public int getNumeroLugar() {
        return numeroLugar;
    }

    public boolean isAcessivel() {
        return isAcessivel;
    }

}

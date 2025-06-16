package model;

import java.io.Serializable;

public class Lugar implements Serializable {
    private static final long serialVersionUID = 1L;
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

    public void setAcessivel(boolean acessivel) {
        isAcessivel = acessivel;
    }
}

package model;

import java.io.Serializable;

public class Bilhete implements Serializable {
    private Sessao sessao;
    private Lugar lugar;
    private String tipo;

    public Bilhete(Sessao sessao, Lugar lugar, String tipo) {
        this.sessao = sessao;
        this.lugar = lugar;
        this.tipo = tipo;
    }

    public Sessao getSessao() {
        return sessao;
    }

    public Lugar getLugar() {
        return lugar;
    }

    public String getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return "Bilhete{" +
                "sessao=" + sessao +
                ", lugar=" + lugar +
                ", tipo=" + tipo +
                '}';
    }
}

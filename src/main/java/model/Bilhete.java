package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Bilhete implements Serializable {
    private Sessao sessao;
    private Lugar lugar;
    private double preco;

    public Bilhete(Sessao sessao, Lugar lugar, double preco) {
        this.sessao = sessao;
        this.lugar = lugar;
        this.preco = preco;

    }

    public Sessao getSessao() {
        return sessao;
    }

    public Lugar getLugar() {
        return lugar;
    }

    public double getPreco() {
        return preco;
    }


    public Filme getFilme() {
        return sessao.getFilme();
    }
    @Override
    public String toString() {
        return "Bilhete{" +
                "sessao=" + sessao +
                ", lugar=" + lugar +
                ", preco=" + preco +
                '}';
    }
}

package model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Sessao implements Serializable {
    private Sala sala;
    private int duracao;
    private LocalDateTime dataHora;
    private Filme filme;


    public Sessao(Sala sala, int duracao, LocalDateTime dataHora, Filme filme) {
        this.sala = sala;
        this.duracao = duracao;
        this.dataHora = dataHora;
        this.filme = filme;
    }

    public Sala getSala() {
        return sala;
    }

    public int getDuracao() {
        return duracao;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public Filme getFilme() {
        return filme;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
}

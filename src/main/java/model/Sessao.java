//Rodrigo Correia 22318565
package model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Sessao implements Serializable {
    private Sala sala;
    private int duracao;
    private LocalDateTime dataHora;
    private Filme filme;
    private int numeroLugaresDisponivel;


    public Sessao(Sala sala, int duracao, LocalDateTime dataHora, Filme filme) {
        this.sala = sala;
        this.duracao = duracao;
        this.dataHora = dataHora;
        this.filme = filme;
        setNumeroLugaresDisponivel(numeroLugaresDisponivel);
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

    public void setNumeroLugaresDisponivel(int numeroLugaresDisponivel) {
        this.numeroLugaresDisponivel = sala.getNumeroTotalLugares();
    }

    public int getNumeroLugaresDisponivel() {
        return numeroLugaresDisponivel;
    }

    public void diminuiNumeroLugaresDisponivel() {
        numeroLugaresDisponivel--;
    }

    @Override
    public String toString() {
        return String.format(
                "%s | Sala: %s | %s | Lugares: %d",
                filme.getTitulo(),
                sala.getNome(),
                dataHora.toString(), // podes formatar se quiseres
                numeroLugaresDisponivel

        );
    }
}

package model;

public class Sala {
    private int numeroFilas;
    private int numeroLugaresPorFila;
    private int numeroTotalLugares;
    private int numeroLugaresAcessiveis;
    private int numeroLugaresOcupados;
    private int numeroSala;
    private TipoSala tipoSala;
    private TipoSistemaSom tipoSistemaSom;
    private String nome;

    public Sala(int numeroFilas, int numeroLugaresPorFila, int numeroSala, TipoSala tipoSala, TipoSistemaSom tipoSistemaSom, String nome) {
        this.numeroFilas = numeroFilas;
        this.numeroLugaresPorFila = numeroLugaresPorFila;
        this.numeroTotalLugares = numeroFilas * numeroLugaresPorFila;
        this.numeroLugaresAcessiveis = (int) Math.floor(numeroTotalLugares * 0.05);
        this.numeroLugaresOcupados = 0;
        this.numeroSala = numeroSala;
        this.tipoSala = tipoSala;
        this.tipoSistemaSom = tipoSistemaSom;
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Sala " + numeroSala + " (" + nome + ")" + " - Tipo de Sala: " + tipoSala + ", Sistema de Som: " + tipoSistemaSom;
    }

}

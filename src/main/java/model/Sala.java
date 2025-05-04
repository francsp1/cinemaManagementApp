package model;

public class Sala {
    private int numeroFilas;
    private int numeroLugaresPorFila;
    private int numeroTotalLugares;
    private int numeroLugaresAcessiveis;
    private int numeroSala;
    private TipoSala tipoSala;
    private TipoSistemaSom tipoSistemaSom;
    private String nome;
    private boolean isAtiva;

    public Sala(int numeroFilas, int numeroLugaresPorFila, int numeroSala, TipoSala tipoSala, TipoSistemaSom tipoSistemaSom, String nome) {
        this.numeroFilas = numeroFilas;
        this.numeroLugaresPorFila = numeroLugaresPorFila;
        this.numeroTotalLugares = numeroFilas * numeroLugaresPorFila;
        this.numeroLugaresAcessiveis = (int) Math.ceil(numeroTotalLugares * 0.10);
        this.numeroSala = numeroSala;
        this.tipoSala = tipoSala;
        this.tipoSistemaSom = tipoSistemaSom;
        this.nome = nome;
        this.isAtiva = true;
    }

    @Override
    public String toString() {
        return "Sala " + numeroSala + " (Nome: " + nome + ")" + " | Tipo de Sala: " + tipoSala + " | Sistema de Som: " + tipoSistemaSom + " | Estado: " + (isAtiva ? "Ativa" : "Inativa");
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isAtiva() {
        return isAtiva;
    }

    public void setEstado(boolean ativa) {
        isAtiva = ativa;
    }

    public TipoSistemaSom getTipoSistemaSom() {
        return tipoSistemaSom;
    }

    public void setTipoSistemaSom(TipoSistemaSom tipoSistemaSom) {
        this.tipoSistemaSom = tipoSistemaSom;
    }

    public TipoSala getTipoSala() {
        return tipoSala;
    }

    public void setTipoSala(TipoSala tipoSala) {
        this.tipoSala = tipoSala;
    }

    public int getNumeroSala() {
        return numeroSala;
    }

    public void setNumeroSala(int numeroSala) {
        this.numeroSala = numeroSala;
    }

    public int getNumeroLugaresAcessiveis() {
        return numeroLugaresAcessiveis;
    }

    public void setNumeroLugaresAcessiveis(int numeroLugaresAcessiveis) {
        this.numeroLugaresAcessiveis = numeroLugaresAcessiveis;
    }

    public int getNumeroTotalLugares() {
        return numeroTotalLugares;
    }

    public void setNumeroTotalLugares(int numeroTotalLugares) {
        this.numeroTotalLugares = numeroTotalLugares;
    }

    public int getNumeroLugaresPorFila() {
        return numeroLugaresPorFila;
    }

    public void setNumeroLugaresPorFila(int numeroLugaresPorFila) {
        this.numeroLugaresPorFila = numeroLugaresPorFila;
    }

    public int getNumeroFilas() {
        return numeroFilas;
    }

    public void setNumeroFilas(int numeroFilas) {
        this.numeroFilas = numeroFilas;
    }
}

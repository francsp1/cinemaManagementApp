package model;

import java.io.Serializable;

public class Filme implements Serializable {

    private String titulo;
    private String sinopse;
    private int duracao;
    private Categoria categoria;
    private String realizador;
    private String ator1;
    private String ator2;
    private String ator3;
    private String ator4;
    private Tipo tipo;

    public Filme(String titulo, String sinopse, int duracao, Categoria categoria, String realizador, String ator1, String ator2, String ator3, String ator4, Tipo tipo) {
        this.titulo = titulo;
        this.sinopse = sinopse;
        this.duracao = duracao;
        this.categoria = categoria;
        this.realizador = realizador;
        this.ator1 = ator1;
        this.ator2 = ator2;
        this.ator3 = ator3;
        this.ator4 = ator4;
        this.tipo = tipo;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getSinopse() {
        return sinopse;
    }

    public int getDuracao() {
        return duracao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public String getRealizador() {
        return realizador;
    }

    public String getAtor1() {
        return ator1;
    }

    public String getAtor2() {
        return ator2;
    }

    public String getAtor3() {
        return ator3;
    }

    public String getAtor4() {
        return ator4;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public enum Tipo {
        _2D("2D"), _3D("3D"), IMAX("IMAX");

        private final String label;
        Tipo(String label) { this.label = label; }

        @Override
        public String toString() {
            return label;
        }
    }

    public enum Categoria {
        ACAO("Ação"), AVENTURA("Aventura"), COMEDIA("Comédia"), DANCA("Dança"),
        DOCUMENTARIO("Documentário"), DRAMA("Drama"), ESPIONAGEM("Espionagem"),
        FAROESTE("Faroeste"), FANTASIA("Fantasia"), MISTERIO("Mistério"),
        MUSICAL("Musical"), ROMANCE("Romance"), TERROR("Terror"),
        THRILLER("Thriller"), OUTRO("Outro");

        private final String label;
        Categoria(String label) { this.label = label; }

        @Override
        public String toString() {
            return label;
        }
    }


    @Override
    public String toString() {
        return titulo + " (" + duracao + " min)";
    }





}

package model;

public class Funcionario {
    private String nome;
    private String cartaoCidadao;
    private String numeroContribuinte;
    private String numeroSegurancaSocial;
    private String email;
    private String morada;
    private String telemovel;
    private String username;
    private String password;
    private boolean isGestor;

    public Funcionario(String nome, String username, String password, boolean isAdmin, String cartaoCidadao, String numeroContribuinte, String numeroSegurancaSocial, String email, String morada, String telemovel) {
        this.nome = nome;
        this.username = username;
        this.password = password;
        this.isGestor = isAdmin;
        this.cartaoCidadao = cartaoCidadao;
        this.numeroContribuinte = numeroContribuinte;
        this.numeroSegurancaSocial = numeroSegurancaSocial;
        this.email = email;
        this.morada = morada;
        this.telemovel = telemovel;
    }

    public String getNome() {
        return nome;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isGestor() {
        return isGestor;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + " | Username: " + username + " | Gestor: " + (isGestor ? "Sim" : "Não");
    }
}

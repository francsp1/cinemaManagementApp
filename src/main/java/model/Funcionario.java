package model;

public class Funcionario {
    private String nome;
    private String cartaoCidadao;
    private String email;
    private String morada;
    private String telemovel;
    private String username;
    private String password;
    private boolean isGestor;

    public Funcionario(String nome, String cartaoCidadao, String email, String morada, String telemovel, String username, String password, boolean isGestor) {
        this.nome = nome;
        this.cartaoCidadao = cartaoCidadao;
        this.email = email;
        this.morada = morada;
        this.telemovel = telemovel;
        this.username = username;
        this.password = password;
        this.isGestor = isGestor;
    }


    @Override
    public String toString() {
        return "Nome: " + nome + " | Username: " + username + " | Gestor: " + (isGestor ? "Sim" : "Não");
    }
}

package model;

import java.io.Serial;
import java.io.Serializable;

public class Funcionario implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isGestor() {
        return isGestor;
    }

    public void setGestor(boolean gestor) {
        isGestor = gestor;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTelemovel() {
        return telemovel;
    }

    public void setTelemovel(String telemovel) {
        this.telemovel = telemovel;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCartaoCidadao() {
        return cartaoCidadao;
    }

    public void setCartaoCidadao(String cartaoCidadao) {
        this.cartaoCidadao = cartaoCidadao;
    }
}

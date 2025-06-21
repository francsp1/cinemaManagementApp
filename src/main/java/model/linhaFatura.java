package model;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class linhaFatura implements Serializable {

    private Bilhete bilhete;
    private Produto produto;
    private int quantidade;
    private double precoTotal;

    public linhaFatura(Bilhete bilhete) {
        this.bilhete = bilhete;
        this.produto = null;
        this.quantidade = 1; // Bilhete é considerado como uma unidade
        this.precoTotal = DadosApp.getInstance().getPrecoBilhete(bilhete.getTipo()); // Preço do bilhete
    }

    public linhaFatura(Produto produto, int quantidade, double precoTotal) {
        this.bilhete = null;
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoTotal = precoTotal;
    }

    public double getPrecoTotal() {
        return precoTotal;
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }
    public Bilhete getBilhete() {
        return bilhete;
    }

    @Override
    public String toString() {
        StringBuilder sb = null;
        if (bilhete != null) {
            sb = new StringBuilder("Bilhete" + bilhete.getTipo() + "\n");
        } else if (produto != null) {
            sb = new StringBuilder(produto.getNome() + " x " + quantidade + "\n");
        }
        return sb.toString();
    }
}

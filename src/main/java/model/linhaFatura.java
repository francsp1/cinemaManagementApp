package model;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class linhaFatura implements Serializable {

    //TODO adicionar bilhete
    private Produto produto;
    private int quantidade;
    private double precoTotal;

    public linhaFatura(Produto produto, int quantidade, double precoTotal) {
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(produto.getNome() + " x " + quantidade + "\n");
        return sb.toString();
    }
}

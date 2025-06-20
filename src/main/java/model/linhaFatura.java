package model;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class linhaFatura implements Serializable {

    //TODO adicionar bilhete
    private Produto produto;
    private int quantidade;
    private String data;
    private double precoTotal;

    public linhaFatura(Produto produto, int quantidade, double precoTotal) {
        this.produto = produto;
        this.quantidade = quantidade;

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.data = now.format(formatter);

        this.precoTotal = precoTotal;
    }

    public double getPrecoTotal() {
        return precoTotal;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(produto.getNome() + " x " + quantidade + "\n");
        return sb.toString();
    }
}

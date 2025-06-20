package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Bundle implements Serializable {
    private String nome;
    private double preco;
    private ArrayList<Produto> produtos;
    private ArrayList<Bilhete> bilhetes;

    public Bundle(String nome, double preco, ArrayList<Produto> produtos, ArrayList<Bilhete> bilhetes) {
        this.nome = nome;
        this.preco = preco;
        this.produtos = produtos;
        this.bilhetes = bilhetes;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public ArrayList<Bilhete> getBilhetes() {
        return bilhetes;
    }

    public int getQuantidadeBilhetes(String tipoBilhete) {
        int count = 0;
        for (Bilhete bilhete : bilhetes) {
            if (bilhete.getTipo().equals(tipoBilhete)) {
                count++;
            }
        }
        return count;
    }

    public int getQuantidadeProdutos(String nomeProduto) {
        int count = 0;
        for (Produto produto : produtos) {
            if (produto.getNome().equals(nomeProduto)) {
                count++;
            }
        }
        return count;
    }

}

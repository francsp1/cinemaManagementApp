//Lourenço Ferreira 2230972

package model;

import java.io.Serializable;

public class StockProduto implements Serializable {
    private Produto produto;
    private int quantidade;

    public StockProduto(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void adicionar(int quantidade) {
        this.quantidade += quantidade;
    }

    public void remover(int quantidade) {
        this.quantidade -= quantidade;
        if (this.quantidade < 0) this.quantidade = 0;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}

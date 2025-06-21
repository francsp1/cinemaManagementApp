//Lourenço Ferreira 2230972

package model;

import java.io.Serializable;
import java.time.LocalDate;

public class VendaBilhete implements Serializable {
    private static final long serialVersionUID = 1L;

    private Filme filme;
    private String tipoBilhete;
    private LocalDate dataVenda;
    private int quantidade;

    public VendaBilhete(Filme filme, String tipoBilhete, LocalDate dataVenda, int quantidade) {
        this.filme = filme;
        this.tipoBilhete = tipoBilhete;
        this.dataVenda = dataVenda;
        this.quantidade = quantidade;
    }

    public Filme getFilme() { return filme; }
    public String getTipoBilhete() { return tipoBilhete; }
    public LocalDate getDataVenda() { return dataVenda; }
    public int getQuantidade() { return quantidade; }
}

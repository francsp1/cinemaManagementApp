package model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Fornecedor implements Serializable {
    private String nome;
    private Map<Produto, Double> tabelaPrecos;

    public Fornecedor(String nome) {
        this.nome = nome;
        this.tabelaPrecos = new HashMap<>();
    }

    public String getNome() {
        return nome;
    }

    public void adicionarProduto(Produto produto, double precoFornecedor) {
        tabelaPrecos.put(produto, precoFornecedor);
    }

    public void alterarPreco(Produto produto, double novoPreco) {
        if (tabelaPrecos.containsKey(produto)) {
            tabelaPrecos.put(produto, novoPreco);
        }
    }

    public Double getPrecoDoProduto(Produto produto) {
        return tabelaPrecos.get(produto);
    }

    public Map<Produto, Double> getTabelaPrecos() {
        return tabelaPrecos;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Fornecedor: " + nome + "\nProdutos e Preços:\n");
        for (Map.Entry<Produto, Double> entry : tabelaPrecos.entrySet()) {
            sb.append("  - ").append(entry.getKey().getNome())
                    .append(": €").append(String.format("%.2f", entry.getValue())).append("\n");
        }
        return sb.toString();
    }
}

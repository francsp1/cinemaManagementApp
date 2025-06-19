package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

public class FaturaFornecedor implements Serializable {
    private static final long serialVersionUID = 1L;

    private static int contadorFaturas = 1; // para gerar números de fatura únicos

    private final int numeroFatura;
    private final Fornecedor fornecedor;
    private final LocalDate data;
    private final Map<Produto, Integer> produtos; // Produto e quantidade
    private final double valorTotal;

    public FaturaFornecedor(Fornecedor fornecedor, Map<Produto, Integer> produtos, double valorTotal) {
        this.numeroFatura = contadorFaturas++;
        this.fornecedor = fornecedor;
        this.data = LocalDate.now();
        this.produtos = new LinkedHashMap<>(produtos);
        this.valorTotal = valorTotal;
    }

    public int getNumeroFatura() {
        return numeroFatura;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public LocalDate getData() {
        return data;
    }

    public Map<Produto, Integer> getProdutos() {
        return produtos;
    }

    public double getValorTotal() {
        return valorTotal;
    }
}

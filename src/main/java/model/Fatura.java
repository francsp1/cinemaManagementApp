package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Fatura implements Serializable {

    private List<linhaFatura> linhasFatura = new ArrayList<>();
    private double valorTotal;
    private Date data;
    private Funcionario funcionario;

    public Fatura(List<linhaFatura> linhasFatura, double valorTotal, Funcionario funcionario) {
        this.linhasFatura = linhasFatura;
        this.valorTotal = valorTotal;
        this.data = new Date(); // Define a data atual como data da fatura
        this.funcionario = funcionario;
    }

    public Date getData() {
        return data;
    }
    public List<linhaFatura> getLinhasFatura() {
        return linhasFatura;
    }
    public double getValorTotal() {
        return valorTotal;
    }
    public Funcionario getFuncionario() {
        return funcionario;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(linhaFatura linha: linhasFatura) {
            sb.append(linha.toString()).append(" ");
        }
        return sb.toString();
    }
}

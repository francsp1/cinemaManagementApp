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

}

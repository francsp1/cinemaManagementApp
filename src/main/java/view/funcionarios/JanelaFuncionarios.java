package view.funcionarios;

import model.DadosApp;
import model.Funcionario;
import view.Janela;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class JanelaFuncionarios extends Janela {
    private JFrame parentFrame;
    private JPanel pnlFuncionarios;
    private JScrollPane sclFuncionarios;
    private JList lstFuncionarios;
    private JButton btnAdicionarFuncionario;
    private JButton btnSair;
    private JButton btnVerDetalhesEditarFuncionario;

    public static void main(String[] args) {
        JanelaFuncionarios janela = new JanelaFuncionarios(null);
    }

    public JanelaFuncionarios(JFrame parentFrame) {
        super("Lista de Funcionários");
        this.parentFrame = parentFrame;
        setContentPane(pnlFuncionarios);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);

        addListeners();

        preencherListaFuncionarios();

        setVisible(true);
    }

    private void preencherListaFuncionarios() {
        DefaultListModel<Funcionario> modeloLista = new DefaultListModel<>();
        lstFuncionarios.setModel(modeloLista);

        var lista = DadosApp.getInstance().getFuncionarios();

        for (Funcionario funcionario : lista) {
            modeloLista.addElement(funcionario);
        }
    }

    private void addListeners() {
        btnSair.addActionListener(this::btnSairActionPerformed);
        btnAdicionarFuncionario.addActionListener(this::btnAdicionarFuncionarioActionPerformed);
        btnVerDetalhesEditarFuncionario.addActionListener(this::btnVerDetalhesEditarFuncionarioActionPerformed);

    }

    private void btnSairActionPerformed(ActionEvent e) {
        dispose();
        if (parentFrame != null) {
            parentFrame.setVisible(true);
        }
    }

    private void btnAdicionarFuncionarioActionPerformed(ActionEvent e) {
        //TODO
        mostrarAviso("Funcionalidade não implementada.");
    }

    private void btnVerDetalhesEditarFuncionarioActionPerformed(ActionEvent e) {
        //TODO
        mostrarAviso("Funcionalidade não implementada.");
    }

}

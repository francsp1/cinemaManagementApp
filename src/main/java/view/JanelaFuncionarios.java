package view;

import model.DadosApp;
import model.Funcionario;
import model.Sala;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class JanelaFuncionarios extends JFrame {
    private JFrame parentFrame;
    private JPanel pnlFuncionarios;
    private JScrollPane sclFuncionarios;
    private JList lstFuncionarios;
    private JButton btnAdicionarFuncionario;
    private JButton btnSair;

    public static void main(String[] args) {
        JanelaFuncionarios janela = new JanelaFuncionarios(null);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public JanelaFuncionarios(JFrame parentFrame) {
        super("Lista de Funcionários");
        this.parentFrame = parentFrame;
        setContentPane(pnlFuncionarios);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);

        btnSair.addActionListener(this::btnSairActionPerformed);

        preencherListaFuncionarios();

        setVisible(true);
    }

    private void preencherListaFuncionarios() {
        DefaultListModel<Funcionario> modeloLista = new DefaultListModel<>();
        lstFuncionarios.setModel(modeloLista);

        var lista = DadosApp.INSTANCIA.getFuncionarios();

        for (Funcionario funcionario : lista) {
            modeloLista.addElement(funcionario);
        }
    }

    private void btnSairActionPerformed(ActionEvent e) {
        dispose();
    }

}

package view.funcionarios;

import view.Janela;

import javax.swing.*;

public class JanelaDetalhesFuncionario extends Janela {
    private JFrame parentFrame;
    private JPanel pnlDetalhesFuncionario;
    private JButton btnSair;


    public static void main(String[] args) {
        JanelaDetalhesFuncionario janela = new JanelaDetalhesFuncionario(null);
    }

    public JanelaDetalhesFuncionario(JFrame parentFrame) {
        super("Detalhes do Funcionário");
        this.parentFrame = parentFrame;
        setContentPane(pnlDetalhesFuncionario);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);

        addListeners();

        preencherDetalhesFUncionario();

        setVisible(true);
    }

    private void preencherDetalhesFUncionario() {
    }

    private void addListeners() {
        btnSair.addActionListener(this::btnSairActionPerformed);
    }

    private void btnSairActionPerformed(java.awt.event.ActionEvent e) {
        dispose();
        if (parentFrame != null) {
            parentFrame.setVisible(true);
        }
    }
}

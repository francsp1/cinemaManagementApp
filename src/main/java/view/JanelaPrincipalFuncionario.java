package view;


import javax.swing.*;
import java.awt.event.ActionEvent;

public class JanelaPrincipalFuncionario extends JFrame {
    private JFrame parentFrame;
    private JButton btnAdicionarFilme;
    private JPanel pnlPrincipal;
    private JButton btnVerListaFilmes;
    private JButton btnVendaBilhetes;
    private JButton btnVendaBar;
    private JButton btnAdicionarSessao;
    private JButton btnProcurarSessoes;
    private JPanel pnl1;
    private JLabel lblBemVindoFuncionario;
    private JButton btnSair;

    public static void main(String[] args) {
        JanelaPrincipalFuncionario janela = new JanelaPrincipalFuncionario(null);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public JanelaPrincipalFuncionario(JFrame parentFrame) {
        super("Janela Principal");
        this.parentFrame = parentFrame;
        setContentPane(pnlPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);

        addActionListeners();

        setVisible(true);
    }

    private void addActionListeners() {
        btnSair.addActionListener(this::btnSairActionPerformed);
        btnVerListaFilmes.addActionListener(this::btnVerListaFilmesActionPerformed);
        btnAdicionarSessao.addActionListener(this::btnAdicionarSessaoActionPerformed);
        btnVendaBilhetes.addActionListener(this::btnVendaBilhetesActionPerformed);
        btnVendaBar.addActionListener(this::btnVendaBarActionPerformed);
        btnProcurarSessoes.addActionListener(this::btnProcurarSessoesActionPerformed);
    }

    private void btnSairActionPerformed(ActionEvent e) {
        dispose();
        parentFrame.setVisible(true);
    }

    private void btnVerListaFilmesActionPerformed(ActionEvent e) {
        //TODO
    }

    private void btnAdicionarSessaoActionPerformed(ActionEvent e) {
        //TODO
    }

    private void btnVendaBilhetesActionPerformed(ActionEvent e) {
        //TODO
    }

    private void btnVendaBarActionPerformed(ActionEvent e) {
        //TODO
    }

    private void btnProcurarSessoesActionPerformed(ActionEvent e) {
        //TODO
    }

}




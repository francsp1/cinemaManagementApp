package view;


import view.salas.JanelaSalas;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class JanelaPrincipalFuncionario extends JFrame {
    private JFrame parentFrame;
    private JPanel pnlPrincipal;
    private JButton btnAdicionarFilme;
    private JButton btnVerListaFilmes;
    private JButton btnVendaBilhetes;
    private JButton btnVendaBar;
    private JButton btnAdicionarSessao;
    private JButton btnProcurarSessoes;
    private JPanel pnl1;
    private JLabel lblBemVindoFuncionario;
    private JButton btnSair;
    private JButton btnVerSalas;

    public static void main(String[] args) {
        JanelaPrincipalFuncionario janela = new JanelaPrincipalFuncionario(null);
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
        btnAdicionarFilme.addActionListener(this::btnAdicionarFilmeActionPerformed);
        btnVerListaFilmes.addActionListener(this::btnVerListaFilmesActionPerformed);
        btnAdicionarSessao.addActionListener(this::btnAdicionarSessaoActionPerformed);
        btnVendaBilhetes.addActionListener(this::btnVendaBilhetesActionPerformed);
        btnVendaBar.addActionListener(this::btnVendaBarActionPerformed);
        btnProcurarSessoes.addActionListener(this::btnProcurarSessoesActionPerformed);
        btnVerSalas.addActionListener(this::btnVerSalasActionPerformed);
    }

    private void btnSairActionPerformed(ActionEvent e) {
        if (parentFrame != null) {
            parentFrame.setVisible(true);
        }
        dispose();
    }

    private void btnAdicionarFilmeActionPerformed(ActionEvent e) {
        //TODO
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

    private void btnVerSalasActionPerformed(ActionEvent e) {
        JanelaSalas janelaSalas = new JanelaSalas(this, false);
        setVisible(false);
    }

}




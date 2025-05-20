package view;

import view.salas.JanelaSalas;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class JanelaPrincipalGestor extends JFrame {
    private final JFrame parentFrame;
    private JPanel pnlPrincipal;
    private JButton btnAdicionarFilme;
    private JButton btnAdicionarSessao;
    private JButton btnVendasBilhetes;
    private JButton btnProcurarSessoes;
    private JButton btnGerirBundles;
    private JButton btnHistoricoVendas;
    private JButton btnEstatistacasFilmes;
    private JButton btnVerSalas;
    private JButton btnVendasBar;
    private JButton btnReporStocksFornecedores;
    private JButton btnAdicionarSala;
    private JButton btnVerFilmes;
    private JPanel pnl1;
    private JLabel lblBemVindoGestor;
    private JButton btnSair;

    public static void main(String[] args) {
        JanelaPrincipalGestor janela = new JanelaPrincipalGestor(null);
    }

    public JanelaPrincipalGestor(JFrame parentFrame) {
        super("Janela Principal");
        this.parentFrame = parentFrame;
        setContentPane(pnlPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);

        addListeners();

        setVisible(true);
    }

    private void addListeners() {
        btnSair.addActionListener(this::btnSairActionPerformed);
        btnVendasBilhetes.addActionListener(this::btnVendasBilhetesActionPerformed);
        btnAdicionarFilme.addActionListener(this::btnAdicionarFilmeActionPerformed);
        btnHistoricoVendas.addActionListener(this::btnHistoricoVendasActionPerformed);
        btnEstatistacasFilmes.addActionListener(this::btnEstatisticasFilmesActionPerformed);
        btnVerSalas.addActionListener(this::btnVerSalasActionPerformed);
        btnProcurarSessoes.addActionListener(this::btnProcurarSessoesActionPerformed);
        btnVendasBar.addActionListener(this::btnVendasBarActionPerformed);
        btnAdicionarSessao.addActionListener(this::btnAdicionarSessaoActionPerformed);
        btnReporStocksFornecedores.addActionListener(this::btnReporStocksFornecedoresActionPerformed);
        btnAdicionarSala.addActionListener(this::btnAdicionarSalaActionPerformed);
        btnVerFilmes.addActionListener(this::btnVerFilmesActionPerformed);
        btnGerirBundles.addActionListener(this::btnGerirBundlesActionPerformed);
    }

    private void btnSairActionPerformed(ActionEvent e) {
        if (parentFrame != null) {
            parentFrame.setVisible(true);
        }
        dispose();
    }

    private void btnVendasBilhetesActionPerformed(ActionEvent e) {
        //TODO
    }

    private void btnAdicionarFilmeActionPerformed(ActionEvent e) {
        //TODO
    }

    private void btnHistoricoVendasActionPerformed(ActionEvent e) {
        //TODO
    }

    private void btnEstatisticasFilmesActionPerformed(ActionEvent e) {
        //TODO
    }

    private void btnVerSalasActionPerformed(ActionEvent e) {
        setVisible(false);
        JanelaSalas janelaSalas = new JanelaSalas(this, true);
    }

    private void btnProcurarSessoesActionPerformed(ActionEvent e) {
        //TODO
    }

    private void btnVendasBarActionPerformed(ActionEvent e) {
        //TODO
    }

    private void btnAdicionarSessaoActionPerformed(ActionEvent e) {
        //TODO
    }

    private void btnReporStocksFornecedoresActionPerformed(ActionEvent e) {
        //TODO
    }

    private void btnAdicionarSalaActionPerformed(ActionEvent e) {
    }

    private void btnVerFilmesActionPerformed(ActionEvent e) {
        //TODO
    }

    private void btnGerirBundlesActionPerformed(ActionEvent e) {
        //TODO
    }

}

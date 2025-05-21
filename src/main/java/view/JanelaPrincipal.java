package view;

import model.DadosApp;
import model.Funcionario;
import view.salas.JanelaSalas;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class JanelaPrincipal extends JFrame {
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
    private JLabel lblBemVindo;
    private JButton btnSair;

    private Funcionario funcionario;

    public static void main(String[] args) {
        var gestor = DadosApp.INSTANCIA.getFuncionarios().get(5);
        var funcionario = DadosApp.INSTANCIA.getFuncionarios().get(6);

        JanelaPrincipal janela = new JanelaPrincipal(null, gestor);
    }

    public JanelaPrincipal(JFrame parentFrame, Funcionario funcionario) {
        super("Janela Principal");
        this.parentFrame = parentFrame;
        this.funcionario = funcionario;
        setContentPane(pnlPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);

        configurarCampos();

        addListeners();

        setVisible(true);
    }

    private void configurarCampos() {
        if (funcionario.isGestor()) {
            lblBemVindo.setText("Bem Vindo Sr(a). Gestor(a)");
            return;
        }

        lblBemVindo.setText("Bem Vindo Sr(a). Funcionário(a)");
        btnHistoricoVendas.setEnabled(false);
        btnEstatistacasFilmes.setEnabled(false);
        btnReporStocksFornecedores.setEnabled(false);
        btnAdicionarSala.setEnabled(false);
        btnGerirBundles.setEnabled(false);

    }

    private void addListeners() {
        btnSair.addActionListener(this::btnSairActionPerformed);


        btnVendasBilhetes.addActionListener(this::btnVendasBilhetesActionPerformed);
        btnAdicionarFilme.addActionListener(this::btnAdicionarFilmeActionPerformed);
        btnProcurarSessoes.addActionListener(this::btnProcurarSessoesActionPerformed);
        btnVendasBar.addActionListener(this::btnVendasBarActionPerformed);
        btnAdicionarSessao.addActionListener(this::btnAdicionarSessaoActionPerformed);
        btnVerFilmes.addActionListener(this::btnVerFilmesActionPerformed);
        btnVerSalas.addActionListener(this::btnVerSalasActionPerformed);

        if (funcionario.isGestor()) {
            btnHistoricoVendas.addActionListener(this::btnHistoricoVendasActionPerformed); //
            btnEstatistacasFilmes.addActionListener(this::btnEstatisticasFilmesActionPerformed);//
            btnReporStocksFornecedores.addActionListener(this::btnReporStocksFornecedoresActionPerformed);//
            btnAdicionarSala.addActionListener(this::btnAdicionarSalaActionPerformed);//
            btnGerirBundles.addActionListener(this::btnGerirBundlesActionPerformed);//
        }
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
        JanelaSalas janelaSalas = new JanelaSalas(this, funcionario.isGestor());
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

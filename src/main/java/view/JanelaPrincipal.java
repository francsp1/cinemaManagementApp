package view;

import model.DadosApp;
import model.Funcionario;
import view.funcionarios.JanelaFuncionarios;
import view.salas.JanelaSalas;
import view.JanelaVenda;
import view.JanelaStockBar;
import view.JanelaEstatisticasFilmes;


import javax.swing.*;
import java.awt.event.ActionEvent;

public class JanelaPrincipal extends Janela {
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
    private JButton btnVerFilmes;
    private JPanel pnl1;
    private JLabel lblBemVindo;
    private JButton btnSair;
    private JButton btnVerFuncionarios;
    private JButton btnGuardarDados;

    private Funcionario funcionario;

    public static void main(String[] args) {
        var gestor = DadosApp.getInstance().getFuncionarios().get(5);
        var funcionario = DadosApp.getInstance().getFuncionarios().get(6);

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
        btnGerirBundles.setEnabled(false);
        btnVerFuncionarios.setEnabled(false);

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
        btnGuardarDados.addActionListener(this::btnGuardarDadosActionPerformed);

        if (funcionario.isGestor()) {
            btnHistoricoVendas.addActionListener(this::btnHistoricoVendasActionPerformed);
            btnEstatistacasFilmes.addActionListener(this::btnEstatisticasFilmesActionPerformed);
            btnReporStocksFornecedores.addActionListener(this::btnReporStocksFornecedoresActionPerformed);
            btnGerirBundles.addActionListener(this::btnGerirBundlesActionPerformed);
            btnVerFuncionarios.addActionListener(this::btnVerFuncionariosActionPerformed);
        }
    }

    private void btnSairActionPerformed(ActionEvent e) {
        dispose();
        if (parentFrame != null) {
            parentFrame.setVisible(true);
        }
    }

    private void btnVendasBilhetesActionPerformed(ActionEvent e) {
        setVisible(false);
        JanelaVenda janelaVenda = new JanelaVenda(this);
    }

    private void btnAdicionarFilmeActionPerformed(ActionEvent e) {
        setVisible(false);
        new JanelaAdicionarFilme(this, funcionario.isGestor());
    }

    private void btnHistoricoVendasActionPerformed(ActionEvent e) {
        //TODO
    }

    private void btnEstatisticasFilmesActionPerformed(ActionEvent e) {
        setVisible(false);
        JanelaEstatisticasFilmes janelaEstatisticasFilmes = new JanelaEstatisticasFilmes(this);
    }

    private void btnVerSalasActionPerformed(ActionEvent e) {
        setVisible(false);
        JanelaSalas janelaSalas = new JanelaSalas(this, funcionario.isGestor());
    }

    private void btnProcurarSessoesActionPerformed(ActionEvent e) {
        setVisible(false);
        new JanelaPesquisaSessoes(this, funcionario.isGestor());

    }

    private void btnVendasBarActionPerformed(ActionEvent e) {
        //TODO
    }

    private void btnAdicionarSessaoActionPerformed(ActionEvent e) {
        setVisible(false);
        new JanelaAdicionarSessao(this, funcionario.isGestor());
    }

    private void btnReporStocksFornecedoresActionPerformed(ActionEvent e) {
        setVisible(false);
        JanelaStockBar janelaStockBar = new JanelaStockBar(this);
    }

    private void btnVerFilmesActionPerformed(ActionEvent e) {
        //TODO
    }

    private void btnGerirBundlesActionPerformed(ActionEvent e) {
        //TODO
    }

    private void btnVerFuncionariosActionPerformed(ActionEvent e) {
        setVisible(false);
        JanelaFuncionarios janelaFuncionarios = new JanelaFuncionarios(this);
    }

    private void btnGuardarDadosActionPerformed(ActionEvent e) {
        DadosApp.gravarDados();
        mostrarSucesso("Dados guardados com sucesso!");
    }

}

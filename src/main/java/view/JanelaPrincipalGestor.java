package view;

import javax.swing.*;

public class JanelaPrincipalGestor extends JFrame {
    private JFrame parentFrame;
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

    public static void main(String[] args) {
        JanelaPrincipalGestor janela = new JanelaPrincipalGestor(null);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public JanelaPrincipalGestor(JFrame parentFrame) {
        super("Janela Principal");
        this.parentFrame = parentFrame;
        setContentPane(pnlPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);

        setVisible(true);
    }

}

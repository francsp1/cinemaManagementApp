package view;


import javax.swing.*;

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

        setVisible(true);
    }
}




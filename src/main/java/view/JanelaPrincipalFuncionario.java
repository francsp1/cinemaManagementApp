package view;


import javax.swing.*;

public class JanelaPrincipalFuncionario extends JFrame {
    private JFrame parentFrame;
    private JButton adicionarFilmeButton;
    private JPanel pnlPrincipal;
    private JButton verListaFilmesButton;
    private JButton vendaBilhetesButton;
    private JButton vendaBarButton;
    private JButton adicionarSessaoButton;
    private JButton procurarSessoesButton;

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




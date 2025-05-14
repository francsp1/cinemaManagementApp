package view;

import javax.swing.*;

public class JanelaPrincipalGestor extends JFrame {
    private JFrame parentFrame;
    private JPanel pnlPrincipal;
    private JButton adicionarFilmeButton;
    private JButton adicionarSessaoButton;
    private JButton vendasBilhetesButton;
    private JButton procurarSessoesButton;
    private JButton gerirBundlesButton;

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

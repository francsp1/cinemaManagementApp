package view;

import javax.swing.*;

public class JanelaPrincipalGestor extends JFrame {
    private JPanel pnlPrincipal;
    private JButton adicionarFilmeButton;
    private JButton adicionarSessaoButton;
    private JButton vendasBilhetesButton;

    public static void main(String[] args) {
        JanelaPrincipalGestor janela = new JanelaPrincipalGestor();
    }

    public JanelaPrincipalGestor() {
        super("Janela Principal");
        setContentPane(pnlPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);

        setVisible(true);
    }

}

package view;

import javax.swing.*;

public class JanelaPrincipal extends JFrame {
    private JPanel pnlPrincipal;

    public static void main(String[] args) {
        JanelaPrincipal janela = new JanelaPrincipal();
    }

    public JanelaPrincipal() {
        super("Janela Principal");
        setContentPane(pnlPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);

        setVisible(true);
    }

}

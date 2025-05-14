package view;

import javax.swing.*;

public class JanelaLogin extends JFrame {
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JPanel pnlLogin;
    private JLabel lblLogin;
    private JLabel lblFuncionario;
    private JPanel pnl1;
    private JPanel pnl2;
    private JPanel pnl3;
    private JTextField lblUsername;
    private JPanel pnl4;
    private JLabel lblPassword;

    public static void main(String[] args) {
        JanelaLogin janela = new JanelaLogin();
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public JanelaLogin() {
        super("Lista de Salas");
        setContentPane(pnlSalas);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);

        btnSair.addActionListener(this::btnSairActionPerformed);

        preencherListaSalas();

        setVisible(true);
    }
}

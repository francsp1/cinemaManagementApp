package view;

import model.DadosApp;
import model.Funcionario;

import javax.swing.*;
import java.awt.event.ActionEvent;

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
    private JButton btnSair;

    public static void main(String[] args) {
        JanelaLogin janela = new JanelaLogin();
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public JanelaLogin() {
        super("Lista de Salas");
        setContentPane(pnlLogin);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);

        btnSair.addActionListener(this::btnSairActionPerformed);
        btnLogin.addActionListener(this::btnLoginActionPerformed);

        setVisible(true);
    }

    private void btnSairActionPerformed(ActionEvent e) {
        System.exit(0);
    }

    private void btnLoginActionPerformed(ActionEvent e) {
        String username = lblUsername.getText();
        String password = new String(txtPassword.getPassword());

        boolean funcionarioValido = false;
        for (Funcionario funcionario : DadosApp.INSTANCIA.getFuncionarios()) {
            if (funcionario.getUsername().equals(username) && funcionario.getPassword().equals(password)) {
                if (funcionario.isGestor()) {
                    JanelaPrincipalGestor janelaPrincipalGestor = new JanelaPrincipalGestor(this);
                } else {
                    JanelaPrincipalFuncionario janelaPrincipalFuncionario = new JanelaPrincipalFuncionario(this);
                }
                funcionarioValido = true;
                break;
            }
        }

        if (!funcionarioValido) {
            JOptionPane.showMessageDialog(this, "Username e Password inválidos", "Erro", JOptionPane.ERROR_MESSAGE);
            lblUsername.setText("");
            txtPassword.setText("");
            return;
        }
        setVisible(false);
    }
}

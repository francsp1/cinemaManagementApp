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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);

        addListeners();

        setVisible(true);
    }

    private void addListeners() {
        btnSair.addActionListener(this::btnSairActionPerformed);
        btnLogin.addActionListener(this::btnLoginActionPerformed);
    }

    private void btnSairActionPerformed(ActionEvent e) {
        System.exit(0);
    }

    private void btnLoginActionPerformed(ActionEvent e) {
        String username = lblUsername.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Os campos Username e Password não devem estar vazios ou conter apenas espaços", "Erro", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Funcionario funcionario = procurarFuncionario(username, password);

        if (funcionario == null) {
            JOptionPane.showMessageDialog(this, "Username e Password inválidos", "Erro", JOptionPane.ERROR_MESSAGE);
            lblUsername.setText("");
            txtPassword.setText("");
            return;
        }

        new JanelaPrincipal(this, funcionario);

        setVisible(false);
    }

    private Funcionario procurarFuncionario(String username, String password) {
        for (Funcionario funcionario : DadosApp.INSTANCIA.getFuncionarios()) {
            if (funcionario.getUsername().equals(username) && funcionario.getPassword().equals(password)) {
                return funcionario;
            }
        }
        return null;
    }
}

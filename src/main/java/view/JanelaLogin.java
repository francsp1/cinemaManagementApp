package view;

import model.DadosApp;
import model.Funcionario;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class JanelaLogin extends Janela {
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

    private static final String ERRO_1 = "O campo Username não deve estar vazio ou conter apenas espaços.";
    private static final String ERRO_2 = "O campo Password não deve estar vazio.";
    private static final String ERRO_3 = "Não existe nenhum funcionário com o username introduzido.";
    private static final String ERRO_4 = "A password introduzida não está correta.";

    public static void main(String[] args) {
        JanelaLogin janela = new JanelaLogin();
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public JanelaLogin() {
        super("Login");
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
        dispose();
        // end the application
        System.exit(0);
    }

    private void btnLoginActionPerformed(ActionEvent e) {
        String username = lblUsername.getText();
        String password = new String(txtPassword.getPassword());

        if (username.trim().isEmpty()) {
            mostrarErro(ERRO_1);
            return;
        }

        if (password.isEmpty()){
            mostrarErro(ERRO_2);
            return;
        }

        Funcionario funcionario = procurarFuncionario(username);

        if (funcionario == null) {
            mostrarErro(ERRO_3);
            lblUsername.setText("");
            txtPassword.setText("");
            return;
        }

        if (!funcionario.getPassword().equals(password)) {
            mostrarErro(ERRO_4);
            txtPassword.setText("");
            return;
        }

        setVisible(false);

        new JanelaPrincipal(this, funcionario);
    }

    private Funcionario procurarFuncionario(String username) {
        for (Funcionario funcionario : DadosApp.getInstance().getFuncionarios()) {
            if (funcionario.getUsername().equals(username)) {
                return funcionario;
            }
        }
        return null;
    }
}

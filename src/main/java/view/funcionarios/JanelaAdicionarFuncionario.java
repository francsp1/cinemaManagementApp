package view.funcionarios;

import view.Janela;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class JanelaAdicionarFuncionario extends Janela {
    private JFrame parentFrame;
    private JPanel pnlAdicionarFuncionario;
    private JTextField txtNome;
    private JLabel lblNome;
    private JTextField txtCartaoCidadao;
    private JLabel lblCartaoCidadao;
    private JLabel lblEmail;
    private JTextField txtEmail;
    private JTextField txtMorada;
    private JLabel lblMorada;
    private JLabel lblTelemovel;
    private JTextField txtTelemovel;
    private JTextField txtUsername;
    private JLabel lblUsername;
    private JLabel lblPassword;
    private JPasswordField txtPassword;
    private JCheckBox chkGestor;
    private JButton btnSair;
    private JButton btnAdicionarFuncionario;

    public static void main(String[] args) {
        JanelaAdicionarFuncionario janela = new JanelaAdicionarFuncionario(null);
    }

    public JanelaAdicionarFuncionario(JFrame parentFrame) {
        super("Adicionar Funcionário");
        this.parentFrame = parentFrame;
        setContentPane(pnlAdicionarFuncionario);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);

        btnSair.addActionListener(this::btnSairActionPerformed);

        setVisible(true);
    }

    private void btnSairActionPerformed(ActionEvent e) {
        dispose();
    }
}

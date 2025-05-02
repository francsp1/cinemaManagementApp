package view.salas;

import model.TipoSala;
import model.TipoSistemaSom;

import javax.swing.*;

public class JanelaAdicionarSala extends JFrame {
    private JPanel pnlAdicionarSala;
    private JButton btnAdicionarSala;
    private JButton btnCancelar;
    private JScrollPane sclPnAdicionarSala;
    private JLabel lblNumeroSala;
    private JTextField txtNumeroSala;
    private JTextField txtNomeSala;
    private JLabel lblFilas;
    private JLabel lblLugaresFila;
    private JLabel lblNomeSala;
    private JPanel panel1;
    private JLabel lblTipoSala;
    private JComboBox cmbTipoSala;
    private JComboBox cmbTipoSistemaSom;
    private JLabel lblTipoSistemaSom;
    private JLabel lblEstadoSala;
    private JComboBox cmbEstadoSala;
    private JPanel pnlConfiguracaoSala;
    private JTextField númeroDeFilasTextField;
    private JTextField textField1;

    public static void main(String[] args) {
        JanelaAdicionarSala janela = new JanelaAdicionarSala();
    }

    public JanelaAdicionarSala() {
        super("Adicionar Sala");
        setContentPane(pnlAdicionarSala);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();

        preencherCampos();

        setVisible(true);
    }

    private void preencherCampos() {
        cmbTipoSala.removeAllItems();
        for (TipoSala tipo : TipoSala.values()) {
            cmbTipoSala.addItem(tipo);
        }

        cmbTipoSistemaSom.removeAllItems();
        for (TipoSistemaSom tipo : TipoSistemaSom.values()) {
            cmbTipoSistemaSom.addItem(tipo);
        }
    }

}

package view.salas;

import model.TipoSala;
import model.TipoSistemaSom;

import javax.swing.*;

public class JanelaAdicionarSala extends JFrame {
    private JFrame parentFrame;
    private JPanel pnlAdicionarSala;
    private JButton btnAdicionarSala;
    private JButton btnCancelar;
    private JScrollPane sclAdicionarSala;
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
    private JTextField txtNumeroFilas;
    private JTextField txtNumeroLugaresFila;

    public static void main(String[] args) {
        JanelaAdicionarSala janela = new JanelaAdicionarSala(null);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public JanelaAdicionarSala(JFrame parentFrame) {
        super("Adicionar Sala");
        this.parentFrame = parentFrame;
        setContentPane(pnlAdicionarSala);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);

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

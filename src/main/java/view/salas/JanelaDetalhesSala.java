package view.salas;

import model.DadosApp;
import model.Sala;
import model.TipoSala;
import model.TipoSistemaSom;

import javax.swing.*;

public class JanelaDetalhesSala extends JFrame {
    private JFrame parentFrame;
    private JPanel pnlDetalhesSala;
    private JButton btnGuardar;
    private JButton btnCancelar;
    private JScrollPane sclPnDetalhesSala;
    private JLabel lblNumeroSala;
    private JTextField txtNumeroSala;
    private JTextField txtNomeSala;
    private JLabel lblFilas;
    private JLabel lblLugaresFila;
    private JLabel lblNumeroFIlas;
    private JLabel lblNumeroLugaresFila;
    private JLabel lblNomeSala;
    private JLabel lblTotalLugares;
    private JPanel panel1;
    private JLabel lblLugaresAcessiveis;
    private JLabel lblNumeroLugaresAcessiveis;
    private JLabel lblTipoSala;
    private JComboBox cmbTipoSala;
    private JComboBox cmbTipoSistemaSom;
    private JLabel lblNumeroTotalLugares;
    private JLabel lblTipoSistemaSom;
    private JLabel lblEstadoSala;
    private JComboBox cmbEstadoSala;
    private JPanel pnlConfiguracaoSala;

    public static void main(String[] args) {
        JanelaDetalhesSala janela = new JanelaDetalhesSala(null, DadosApp.INSTANCIA.getSalas().getFirst());
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public JanelaDetalhesSala(JFrame parentFrame, Sala sala) {
        super("Detalhes da Sala " + sala.getNumeroSala());
        this.parentFrame = parentFrame;
        setContentPane(pnlDetalhesSala);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);

        preencherDetalhesSala(sala);

        setVisible(true);
    }

    private void preencherDetalhesSala(Sala sala) {
        txtNumeroSala.setText(sala.getNumeroSala() + "");
        txtNomeSala.setText(sala.getNome());
        lblNumeroFIlas.setText(sala.getNumeroFilas() + "");
        lblNumeroLugaresFila.setText(sala.getNumeroLugaresPorFila() + "");
        lblNumeroTotalLugares.setText(sala.getNumeroTotalLugares() + "");
        lblNumeroLugaresAcessiveis.setText(sala.getNumeroLugaresAcessiveis() + "");

        cmbTipoSala.removeAllItems();
        for (TipoSala tipo : TipoSala.values()) {
            cmbTipoSala.addItem(tipo);
        }
        cmbTipoSala.setSelectedItem(sala.getTipoSala());

        cmbTipoSistemaSom.removeAllItems();
        for (TipoSistemaSom tipo : TipoSistemaSom.values()) {
            cmbTipoSistemaSom.addItem(tipo);
        }
        cmbTipoSistemaSom.setSelectedItem(sala.getTipoSistemaSom());

        cmbEstadoSala.setSelectedItem(sala.isAtiva() ? "Ativa" : "Inativa");
    }

}

package view.salas;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class JanelaDetalhesSala extends JFrame {
    private final JFrame parentFrame;
    private JPanel pnlDetalhesSala;
    private JButton btnGuardar;
    private JButton btnSair;
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
    private JButton btnSessoesSala;
    private JLabel lblConfiguracaoSala;

    private final boolean isGestor;

    private final Sala sala;

    public static void main(String[] args) {
        JanelaDetalhesSala janela = new JanelaDetalhesSala(null, DadosApp.INSTANCIA.getSalas().getLast(), true);
    }

    public JanelaDetalhesSala(JFrame parentFrame, Sala sala, boolean isGestor) {
        super("Detalhes da Sala " + sala.getNumeroSala());
        this.parentFrame = parentFrame;
        this.sala = sala;
        this.isGestor = isGestor;
        setContentPane(pnlDetalhesSala);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);

        addListeners();

        configurarCampos();

        preencherDetalhesSala();

        setVisible(true);
    }

    private void configurarCampos() {
        cmbTipoSala.removeAllItems();
        for (TipoSala tipo : TipoSala.values()) {
            cmbTipoSala.addItem(tipo);
        }

        cmbTipoSistemaSom.removeAllItems();
        for (TipoSistemaSom tipo : TipoSistemaSom.values()) {
            cmbTipoSistemaSom.addItem(tipo);
        }

        cmbEstadoSala.removeAllItems();
        cmbEstadoSala.addItem("Ativa");
        cmbEstadoSala.addItem("Inativa");


        if (!isGestor) {
            btnGuardar.setVisible(false);
            txtNumeroSala.setEditable(false);
            txtNomeSala.setEditable(false);
            cmbTipoSistemaSom.setEnabled(false);
            cmbTipoSala.setEnabled(false);
            cmbEstadoSala.setEnabled(false);
        }

    }

    private void preencherDetalhesSala() {
        txtNumeroSala.setText(sala.getNumeroSala() + "");
        txtNomeSala.setText(sala.getNome());
        lblNumeroFIlas.setText(sala.getNumeroFilas() + "");
        lblNumeroLugaresFila.setText(sala.getNumeroLugaresPorFila() + "");
        lblNumeroTotalLugares.setText(sala.getNumeroTotalLugares() + "");
        lblNumeroLugaresAcessiveis.setText(sala.getNumeroLugaresAcessiveis() + "");
        cmbTipoSala.setSelectedItem(sala.getTipoSala());
        cmbTipoSistemaSom.setSelectedItem(sala.getTipoSistemaSom());
        cmbEstadoSala.setSelectedItem(sala.isAtiva() ? "Ativa" : "Inativa");

        desenharConfiguracaoSala();
    }

    private void desenharConfiguracaoSala() {
        //pnlConfiguracaoSala.removeAll();

        var nrLinhas = sala.getNumeroFilas();
        var nrColunas = sala.getNumeroLugaresPorFila();

        var botoes = new JButton[nrLinhas][nrColunas];

        var lugares = sala.getLugares();

        pnlConfiguracaoSala.setLayout(new GridLayout(nrLinhas, nrColunas));

        // Criar e adicionar os botões à janela

        for (int linha = 0; linha < nrLinhas; ++linha) {
            for (int coluna = 0; coluna < nrColunas; ++coluna) {
                var botao = botoes[linha][coluna] = new JButton();

                Lugar lugar = lugares[linha][coluna];

                botao.setText(lugar.getDesignacao());

                if (lugar.isAcessivel()) {
                    botao.setBackground(Color.GREEN);
                    botao.setText(lugar.getDesignacao() + " (Acessível)");
                }else {
                    botao.setText(lugar.getDesignacao());
                }

                pnlConfiguracaoSala.add(botao);
            }
        }


    }

    private void addListeners() {
        btnSair.addActionListener(this::btnSairActionPerformed);

        if (isGestor) {
            btnGuardar.addActionListener(this::btnGuardarActionPerformed);
        }
    }

    private void btnSairActionPerformed(ActionEvent e) {
        if (parentFrame != null) {
            parentFrame.setVisible(true);
        }
        dispose();
    }

    private void btnGuardarActionPerformed(ActionEvent e) {
        //TODO
        JOptionPane.showMessageDialog(this, "TO DO", "", JOptionPane.INFORMATION_MESSAGE);
    }
}

package view.salas;

import model.TipoSala;
import model.TipoSistemaSom;

import javax.swing.*;
import java.awt.*;

public class JanelaAdicionarSala extends JFrame {
    private JFrame parentFrame;
    private JPanel pnlAdicionarSala;
    private JButton btnAdicionarSala;
    private JButton btnSair;
    private JScrollPane sclAdicionarSala;
    private JLabel lblNumeroSala;
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
    private JSpinner sprNumeroSala;
    private JSpinner sprNumeroFilas;
    private JSpinner sprNumeroLugaresFIla;
    private JLabel lblTotalLugares;
    private JLabel lblLugaresAcessiveis;
    private JLabel lblNumeroLugaresAcessiveis;

    public static void main(String[] args) {
        JanelaAdicionarSala janela = new JanelaAdicionarSala(null);

        janela.sprNumeroSala.setValue(2);
        janela.sprNumeroFilas.setValue(5);
        janela.sprNumeroLugaresFIla.setValue(5);
    }

    public JanelaAdicionarSala(JFrame parentFrame) {
        super("Adicionar Sala");
        this.parentFrame = parentFrame;
        setContentPane(pnlAdicionarSala);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);

        addActionListeners();

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

    private void addActionListeners() {
        btnSair.addActionListener(this::btnSairActionPerformed);
    }

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {
        if (parentFrame != null) {
            parentFrame.setVisible(true);
        }
        this.dispose();
    }

}

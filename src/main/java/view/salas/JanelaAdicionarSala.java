package view.salas;

import model.DadosApp;
import model.TipoSala;
import model.TipoSistemaSom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class JanelaAdicionarSala extends JFrame {
    private final JFrame parentFrame;
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

    private static final String ERRO_1 = "O campo 'Número da Sala' não pode estar vazio.";
    private static final String ERRO_2 = "O campo 'Número da Sala' deve ser um número inteiro.";
    private static final String ERRO_3 = "O campo 'Número da Sala' deve ser um número inteiro positivo.";
    private static final String ERRO_4 = "Já existe uma sala com o número ";
    private static final String ERRO_5 = "O campo 'Nome da Sala' não pode estar vazio.";
    private static final String ERRO_6 = "O campo 'Número de Filas' não pode estar vazio.";
    private static final String ERRO_7 = "O campo 'Número de Filas' deve ser um número inteiro.";
    private static final String ERRO_8 = "O campo 'Número de Filas' deve ser um número inteiro positivo.";
    private static final String ERRO_9 = "O campo 'Número de Lugares por Fila' não pode estar vazio.";
    private static final String ERRO_10 = "O campo 'Número de Lugares por Fila' deve ser um número inteiro.";
    private static final String ERRO_11 = "O campo 'Número de Lugares por Fila' deve ser um número inteiro positivo.";

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
        btnAdicionarSala.addActionListener(this::btnAdicionarSalaActionPerformed);
    }

    private void btnSairActionPerformed(ActionEvent evt) {
        if (parentFrame != null) {
            parentFrame.setVisible(true);
        }
        this.dispose();
    }

    private void btnAdicionarSalaActionPerformed(ActionEvent evt) {

        if (validarDetalhesSala()) {

        }

    }

    private boolean validarDetalhesSala() {
        // Verificar Número da Sala
        String numeroSala =  sprNumeroSala.getValue().toString();
        if (numeroSala.trim().isEmpty()) {
            mostrarErro(ERRO_1);
            return false;
        }

        int numeroSalaInt;
        try {
            numeroSalaInt = Integer.parseInt(numeroSala);
        } catch (Exception e) {
            mostrarErro(ERRO_2);
            return false;
        }

        if (numeroSalaInt < 0) {
            mostrarErro(ERRO_3);
            return false;
        }

        if (DadosApp.INSTANCIA.existeNumeroSala(numeroSalaInt)) {
            mostrarErro(ERRO_4 + numeroSalaInt);
            return false;
        }

        // Verificar Nome da Sala
        String nomeSala = txtNomeSala.getText();
        if (nomeSala.trim().isEmpty()) {
            mostrarErro(ERRO_5);
            return false;
        }

        // Verificar Número de Filas
        String numeroFilas = sprNumeroFilas.getValue().toString();
        if (numeroFilas.trim().isEmpty()) {
            mostrarErro(ERRO_6);
            return false;
        }

        int numeroFilasInt;
        try {
            numeroFilasInt = Integer.parseInt(numeroFilas);
        } catch (Exception e) {
            mostrarErro(ERRO_7);
            return false;
        }

        if (numeroFilasInt < 0) {
            mostrarErro(ERRO_8);
            return false;
        }

        // Verificar Número de Lugares por Fila
        String numeroLugaresFila = sprNumeroLugaresFIla.getValue().toString();
        if (numeroLugaresFila.trim().isEmpty()) {
            mostrarErro(ERRO_9);
            return false;
        }

        int numeroLugaresFilaInt;
        try {
            numeroLugaresFilaInt = Integer.parseInt(numeroLugaresFila);
        } catch (Exception e) {
            mostrarErro(ERRO_10);
            return false;
        }

        if (numeroLugaresFilaInt < 0) {
            mostrarErro(ERRO_11);
            return false;
        }

        return true;
    }

    private void mostrarErro(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
    }
}

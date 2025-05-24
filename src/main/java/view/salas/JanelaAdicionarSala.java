package view.salas;

import model.DadosApp;
import model.Sala;
import model.TipoSala;
import model.TipoSistemaSom;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionEvent;

import static model.DadosApp.*;

public class JanelaAdicionarSala extends JFrame {
    private final JanelaSalas parentFrame;
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
    private JSpinner sprNumeroSala;
    private JSpinner sprNumeroFilas;
    private JSpinner sprNumeroLugaresFila;
    private JLabel lblTotalLugares;
    private JLabel lblLugaresAcessiveis;
    private JLabel lblNumeroLugaresAcessiveis;
    private JLabel lblNumeroTotalLugares;

    private static final String ERRO_1 = "O campo 'Número da Sala' não pode estar vazio.";
    private static final String ERRO_2 = "Não foi possível converter o campo 'Número da Sala' para um número inteiro maior ou igual a 1.";
    private static final String ERRO_3 = "O campo 'Número da Sala' deve ser um número inteiro maior ou igual a 1.";
    private static final String ERRO_4 = "Já existe uma sala com o número ";
    private static final String ERRO_5 = "O campo 'Nome da Sala' não pode estar vazio.";
    private static final String ERRO_6 = "O campo 'Número de Filas' não pode estar vazio.";
    private static final String ERRO_7 = "Não foi possível converter o campo 'Número de Filas' para um número inteiro maior ou igual a 1.";
    private static final String ERRO_8 = "O campo 'Número de Filas' deve ser um número inteiro maior ou igual a 1.";
    private static final String ERRO_9 = "Uma sala não pode ter mais de " + MAX_FILAS + " filas.";
    private static final String ERRO_10 = "O campo 'Número de Lugares por Fila' não pode estar vazio.";
    private static final String ERRO_11 = "Não foi possível converter o campo 'Número de Lugares por Fila' para um número inteiro maior ou igual a 1.";
    private static final String ERRO_12 = "O campo 'Número de Lugares por Fila' deve ser um número inteiro maior ou igual a 1.";
    private static final String ERRO_13 = "Uma fila não pode ter mais de " + MAX_LUGARES_POR_FILA + " lugares.";

    public static void main(String[] args) {
        JanelaAdicionarSala janela = new JanelaAdicionarSala(null);

//        janela.sprNumeroSala.setValue(2);
//        janela.sprNumeroFilas.setValue(5);
//        janela.sprNumeroLugaresFila.setValue(5);
    }

    public JanelaAdicionarSala(JanelaSalas parentFrame) {
        super("Adicionar Sala");
        this.parentFrame = parentFrame;
        setContentPane(pnlAdicionarSala);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);

        addListeners();

        configurarCampos();

        setVisible(true);
    }

    private void configurarCampos() {

        sprNumeroSala.setModel(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
        sprNumeroFilas.setModel(new SpinnerNumberModel(1, 1, MAX_FILAS, 1));
        sprNumeroLugaresFila.setModel(new SpinnerNumberModel(1, 1, MAX_LUGARES_POR_FILA, 1));

        lblTotalLugares.setVisible(false);
        lblNumeroLugaresAcessiveis.setVisible(false);
        lblLugaresAcessiveis.setVisible(false);
        lblNumeroTotalLugares.setVisible(false);

        cmbTipoSala.removeAllItems();
        for (TipoSala tipo : TipoSala.values()) {
            cmbTipoSala.addItem(tipo);
        }

        cmbTipoSistemaSom.removeAllItems();
        for (TipoSistemaSom tipo : TipoSistemaSom.values()) {
            cmbTipoSistemaSom.addItem(tipo);
        }
    }

    private void addListeners() {
        btnSair.addActionListener(this::btnSairActionPerformed);
        btnAdicionarSala.addActionListener(this::btnAdicionarSalaActionPerformed);
        //sprNumeroFilas.addChangeListener(this::sprChangeListener);
        //sprNumeroLugaresFila.addChangeListener(this::sprChangeListener);
    }

    private void btnSairActionPerformed(ActionEvent e) {
        dispose();
        if (parentFrame != null) {
            parentFrame.setVisible(true);
        }
    }

    private void btnAdicionarSalaActionPerformed(ActionEvent e) {

        // Verificar Número da Sala
        String numeroSala = sprNumeroSala.getValue().toString();
        if (numeroSala.trim().isEmpty()) {
            mostrarErro(ERRO_1);
            return;
        }

        int numeroSalaInt;
        try {
            numeroSalaInt = Integer.parseInt(numeroSala);
        } catch (Exception exception) {
            mostrarErro(ERRO_2);
            return;
        }

        if (numeroSalaInt < 1) {
            mostrarErro(ERRO_3);
            return;
        }

        if (DadosApp.INSTANCIA.existeNumeroSala(numeroSalaInt)) {
            mostrarErro(ERRO_4 + numeroSalaInt);
            return;
        }

        // Verificar Nome da Sala
        String nomeSala = txtNomeSala.getText();
        if (nomeSala.trim().isEmpty()) {
            mostrarErro(ERRO_5);
            return;
        }

        // Verificar Número de Filas
        String numeroFilas = sprNumeroFilas.getValue().toString();
        if (numeroFilas.trim().isEmpty()) {
            mostrarErro(ERRO_6);
            return;
        }

        int numeroFilasInt;
        try {
            numeroFilasInt = Integer.parseInt(numeroFilas);
        } catch (Exception exception) {
            mostrarErro(ERRO_7);
            return;
        }

        if (numeroFilasInt < 1) {
            mostrarErro(ERRO_8);
            return;
        }

        if (numeroFilasInt > MAX_FILAS) {
            mostrarErro(ERRO_9);
            return;
        }

        // Verificar Número de Lugares por Fila
        String numeroLugaresFila = sprNumeroLugaresFila.getValue().toString();
        if (numeroLugaresFila.trim().isEmpty()) {
            mostrarErro(ERRO_10);
            return;
        }

        int numeroLugaresFilaInt;
        try {
            numeroLugaresFilaInt = Integer.parseInt(numeroLugaresFila);
        } catch (Exception exception) {
            mostrarErro(ERRO_11);
            return;
        }

        if (numeroLugaresFilaInt < 1) {
            mostrarErro(ERRO_12);
            return;
        }

        if (numeroLugaresFilaInt > MAX_LUGARES_POR_FILA) {
            mostrarErro(ERRO_13);
            return;
        }
        // --

        dispose();

        Sala sala = new Sala(
                numeroFilasInt,
                numeroLugaresFilaInt,
                numeroSalaInt,
                (TipoSala) cmbTipoSala.getSelectedItem(),
                (TipoSistemaSom) cmbTipoSistemaSom.getSelectedItem(),
                nomeSala
        );



        JanelaSelecinarLugaresAcesseveis janelaSelecinarLugaresAcesseveis = new JanelaSelecinarLugaresAcesseveis(parentFrame, sala);

        /*
        DadosApp.INSTANCIA.adicionarSala(sala);

        JOptionPane.showMessageDialog(this, "Sala adicionada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

        if (parentFrame != null) {
            parentFrame.setVisible(true);
        }

        dispose();

        parentFrame.preencherListaSalas();
        */

    }

    private void sprChangeListener(ChangeEvent e) {
        spinnerChange();
    }

    private void spinnerChange() {
        int totalLugares = (int) sprNumeroFilas.getValue() * (int) sprNumeroLugaresFila.getValue();
        lblNumeroTotalLugares.setText(Integer.toString(totalLugares));
        lblNumeroLugaresAcessiveis.setText(Integer.toString((int) Math.ceil(totalLugares * PERCENTAGEM_LUGARES_ACESSIVEIS)));
    }

    private void mostrarErro(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
    }
}

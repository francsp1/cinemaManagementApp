//Francisco Pedrosa - 2181248
package view.salas;

import model.DadosApp;
import model.Sala;
import model.TipoSala;
import model.TipoSistemaSom;
import view.Janela;

import javax.swing.*;
import java.awt.event.ActionEvent;

import static model.DadosApp.*;

public class JanelaAdicionarSala extends Janela {
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
        Object numeroSala = sprNumeroSala.getValue();

        if (numeroSala == null) {
            mostrarErro(ERRO_1);
            return;
        }

        if (!(numeroSala instanceof Integer)) {
            mostrarErro(ERRO_2);
            return;
        }

        int numeroSalaInt = (Integer) numeroSala;

        if (numeroSalaInt < 1) {
            mostrarErro(ERRO_3);
            return;
        }

        if (DadosApp.getInstance().existeNumeroSala(numeroSalaInt)) {
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
        Object numeroFilas = sprNumeroFilas.getValue();

        if (numeroFilas == null) {
            mostrarErro(ERRO_6);
            return;
        }

        if (!(numeroFilas instanceof Integer)) {
            mostrarErro(ERRO_7);
            return;
        }

        int numeroFilasInt = (Integer) numeroFilas;

        if (numeroFilasInt < 1) {
            mostrarErro(ERRO_8);
            return;
        }

        if (numeroFilasInt > MAX_FILAS) {
            mostrarErro(ERRO_9);
            return;
        }

        // Verificar Número de Lugares por Fila
        Object numeroLugaresFila = sprNumeroLugaresFila.getValue();

        if (numeroLugaresFila == null) {
            mostrarErro(ERRO_10);
            return;
        }

        if (!(numeroLugaresFila instanceof Integer)) {
            mostrarErro(ERRO_11);
            return;
        }

        int numeroLugaresFilaInt = (Integer) numeroLugaresFila;

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

    }

    public JPanel getMainPanel() {
        return pnlAdicionarSala;
    }

    public JSpinner getSprNumeroSala() {
        return sprNumeroSala;
    }

    public JButton getBtnAdicionarSala() {
        return btnAdicionarSala;
    }

    public JTextField getTxtNomeSala() {
        return txtNomeSala;
    }

    public JSpinner getSprNumeroFilas() {
        return sprNumeroFilas;
    }

    public JSpinner getSprNumeroLugaresFila() {
        return sprNumeroLugaresFila;
    }
}

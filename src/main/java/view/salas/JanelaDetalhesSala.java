package view.salas;

import model.*;
import view.Janela;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class JanelaDetalhesSala extends Janela {
    private final JFrame parentFrame;
    private JPanel pnlDetalhesSala;
    private JButton btnGuardar;
    private JButton btnSair;
    private JScrollPane sclPnDetalhesSala;
    private JLabel lblNumeroSala;
    private JTextField txtNomeSala;
    private JLabel lblFilas;
    private JLabel lblLugaresFila;
    private JLabel lblNumeroFilas;
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
    private JSpinner sprNumeroSala;

    private final boolean isGestor;

    private final Sala sala;

    private static final String ERRO_1 = "O campo 'Número da Sala' não pode estar vazio.";
    private static final String ERRO_2 = "Não foi possível converter o campo 'Número da Sala' para um número inteiro maior ou igual a 1.";
    private static final String ERRO_3 = "O campo 'Número da Sala' deve ser um número inteiro maior ou igual a 1.";
    private static final String ERRO_4 = "Já existe uma sala com o número ";
    private static final String ERRO_5 = "O campo 'Nome da Sala' não pode estar vazio.";

    private static final String SUCESSO_1 = "Sala atualizada com sucesso!";

    public static void main(String[] args) {
        JanelaDetalhesSala janela = new JanelaDetalhesSala(null, DadosApp.getInstance().getSalas().getLast(), true);
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

        desenharConfiguracaoSala();

        setVisible(true);
    }

    private void addListeners() {
        btnSair.addActionListener(this::btnSairActionPerformed);
        btnSessoesSala.addActionListener(this::btnSessoesSalaActionPerformed);

        if (isGestor) {
            btnGuardar.addActionListener(this::btnGuardarActionPerformed);
        }
    }

    private void configurarCampos() {
        sprNumeroSala.setModel(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));

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
            sprNumeroSala.setEnabled(false);
            txtNomeSala.setEditable(false);
            cmbTipoSistemaSom.setEnabled(false);
            cmbTipoSala.setEnabled(false);
            cmbEstadoSala.setEnabled(false);
        }

    }

    private void preencherDetalhesSala() {
        sprNumeroSala.setValue(sala.getNumeroSala());
        txtNomeSala.setText(sala.getNome());
        lblNumeroFilas.setText(sala.getNumeroFilas() + "");
        lblNumeroLugaresFila.setText(sala.getNumeroLugaresPorFila() + "");
        lblNumeroTotalLugares.setText(sala.getNumeroTotalLugares() + "");
        lblNumeroLugaresAcessiveis.setText(sala.getNumeroLugaresAcessiveis() + "");
        cmbTipoSala.setSelectedItem(sala.getTipoSala());
        cmbTipoSistemaSom.setSelectedItem(sala.getTipoSistemaSom());
        cmbEstadoSala.setSelectedItem(sala.isAtiva() ? "Ativa" : "Inativa");
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
                } else {
                    botao.setText(lugar.getDesignacao());
                }

                pnlConfiguracaoSala.add(botao);
            }
        }


    }

    private void btnSairActionPerformed(ActionEvent e) {
        dispose();
        if (parentFrame != null) {
            parentFrame.setVisible(true);
        }
    }

    private void btnGuardarActionPerformed(ActionEvent e) {
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

        if (numeroSalaInt != sala.getNumeroSala() && DadosApp.getInstance().existeNumeroSala(numeroSalaInt)) {
            mostrarErro(ERRO_4 + numeroSalaInt);
            return;
        }


        // Verificar Nome da Sala
        String nomeSala = txtNomeSala.getText();
        if (nomeSala.trim().isEmpty()) {
            mostrarErro(ERRO_5);
            return;
        }

        atualizarSala(numeroSalaInt, nomeSala);

        mostrarSucesso(SUCESSO_1);
    }

    private void atualizarSala(int numeroSalaInt, String nomeSala) {
        sala.setNumeroSala(numeroSalaInt);
        sala.setNome(nomeSala);
        sala.setTipoSala((TipoSala) cmbTipoSala.getSelectedItem());
        sala.setTipoSistemaSom((TipoSistemaSom) cmbTipoSistemaSom.getSelectedItem());
        if (cmbEstadoSala.getSelectedItem().equals("Ativa")) {
            sala.setAtiva(true);
        } else {
            sala.setAtiva(false);
        }
    }

    private void btnSessoesSalaActionPerformed(ActionEvent e) {
        //TODO
        JOptionPane.showMessageDialog(null, "TODO: Implementar ações para o botão 'Sessoes Sala'.", "Ação não implementada", JOptionPane.INFORMATION_MESSAGE);
    }
}

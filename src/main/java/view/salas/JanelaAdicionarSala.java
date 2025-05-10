package view.salas;

import model.Sala;
import model.TipoSala;
import model.TipoSistemaSom;

import javax.swing.*;
import java.awt.*;

public class JanelaAdicionarSala extends JFrame {
    private JFrame parentFrame;
    private JPanel pnlAdicionarSala;
    private JButton btnAdicionarSala;
    private JButton btnCancelar;
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
    private JPanel pnlConfiguracaoSala;
    private JSpinner sprNumeroSala;
    private JSpinner sprNumeroFilas;
    private JLabel lblConfiguracaoSala;
    private JSpinner sprNumeroLugaresFIla;
    private JLabel lblTotalLugares;
    private JLabel lblLugaresAcessiveis;
    private JLabel lblNumeroLugaresAcessiveis;

    public static void main(String[] args) {
        JanelaAdicionarSala janela = new JanelaAdicionarSala(null);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        janela.sprNumeroSala.setValue(2);
        janela.sprNumeroFilas.setValue(5);
        janela.sprNumeroLugaresFIla.setValue(5);
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

        desenharConfiguracaoSala();
    }

    private void desenharConfiguracaoSala() {
        //pnlConfiguracaoSala.removeAll();

        var nrLinhas = 5;
        var nrColunas = 5;

        var botoes = new JButton[nrLinhas][nrColunas];

        pnlConfiguracaoSala.setLayout(new GridLayout(nrLinhas, nrColunas));

        // Criar e adicionar os botões à janela
        for (int linha = 0; linha < nrLinhas; ++linha) {
            for (int coluna = 0; coluna < nrColunas; ++coluna) {
                var botao = botoes[linha][coluna] = new JButton();
                pnlConfiguracaoSala.add(botoes[linha][coluna]);
                botao.setText((char) ('A' + linha) + "" + (coluna + 1));
            }
        }
    }

}

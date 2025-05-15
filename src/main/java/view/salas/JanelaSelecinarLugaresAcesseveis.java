package view.salas;

import model.DadosApp;
import model.Lugar;
import model.Sala;

import javax.swing.*;
import java.awt.*;

public class JanelaSelecinarLugaresAcesseveis extends JFrame {
    private final JFrame parentFrame;
    private JPanel pnlSelecionarLugaresAcessiveis;
    private JButton btnSair;
    private JLabel lblLugaresAcessiveis;
    private JPanel pnlConfiguracaoSala;
    private JButton[][] botoes;
    private Sala sala;

    public static void main(String[] args) {
        JanelaSelecinarLugaresAcesseveis janela = new JanelaSelecinarLugaresAcesseveis(null, DadosApp.INSTANCIA.getSalas().getLast());
    }

    public JanelaSelecinarLugaresAcesseveis(JFrame parentFrame, Sala sala) {
        super("Confuguração da Sala");
        this.parentFrame = parentFrame;
        this.sala = sala;
        setContentPane(pnlSelecionarLugaresAcessiveis);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);

        addActionListeners();

        desenharConfiguracaoSala();

        setVisible(true);
    }

    private void desenharConfiguracaoSala() {
        pnlConfiguracaoSala.removeAll();

        lblLugaresAcessiveis.setText("Selecione " + sala.getNumeroLugaresAcessiveis() + " lugares acessíveis: ");

        var nrLinhas = sala.getNumeroFilas();
        var nrColunas = sala.getNumeroLugaresPorFila();

        var lugares = sala.getLugares();

        botoes = new BotaoLugar[nrLinhas][nrColunas];

        pnlConfiguracaoSala.setLayout(new GridLayout(nrLinhas, nrColunas));

        for (int linha = 0; linha < nrLinhas; ++linha) {
            for (int coluna = 0; coluna < nrColunas; ++coluna) {
                var botao = botoes[linha][coluna] = new BotaoLugar(lugares[linha][coluna], linha, coluna);
                pnlConfiguracaoSala.add(botao);
            }
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

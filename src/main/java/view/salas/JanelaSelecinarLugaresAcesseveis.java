package view.salas;

import model.DadosApp;
import model.Sala;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class JanelaSelecinarLugaresAcesseveis extends JFrame {
    private final JanelaSalas parentFrame;
    private JPanel pnlSelecionarLugaresAcessiveis;
    private JButton btnSair;
    private JLabel lblLugaresAcessiveis;
    private JPanel pnlConfiguracaoSala;
    private JButton btnConfirmar;
    private BotaoLugar[][] botoes;
    private Sala sala;

    public static void main(String[] args) {
        JanelaSelecinarLugaresAcesseveis janela = new JanelaSelecinarLugaresAcesseveis(null, DadosApp.INSTANCIA.getSalas().getLast());
    }

    public JanelaSelecinarLugaresAcesseveis(JanelaSalas parentFrame, Sala sala) {
        super("Confuguração da Sala");
        this.parentFrame = parentFrame;
        this.sala = sala;
        setContentPane(pnlSelecionarLugaresAcessiveis);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);

        addListeners();

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

    private void addListeners() {
        btnSair.addActionListener(this::btnSairActionPerformed);
        btnConfirmar.addActionListener(this::btnConfirmarActionPerformed);
    }

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {
        if (parentFrame != null) {
            parentFrame.setVisible(true);
        }
        this.dispose();
    }

    private void btnConfirmarActionPerformed(ActionEvent actionEvent) {

        var nrLinhas = sala.getNumeroFilas();
        var nrColunas = sala.getNumeroLugaresPorFila();

        var botoesSelecionados = new ArrayList<BotaoLugar>();

        for (int linha = 0; linha < nrLinhas; ++linha) {
            for (int coluna = 0; coluna < nrColunas; ++coluna) {
                if (botoes[linha][coluna].isAcessivel()) {
                    botoesSelecionados.add(botoes[linha][coluna]);
                }
            }
        }

        int numeroLugaresAcessiveis = sala.getNumeroLugaresAcessiveis();
        if (botoesSelecionados.size() != numeroLugaresAcessiveis) {
            JOptionPane.showMessageDialog(this, "Deve selecionar " + numeroLugaresAcessiveis + " lugares acessíveis.", "Erro", JOptionPane.WARNING_MESSAGE);
            return;
        }

        for (BotaoLugar botao : botoesSelecionados) {
            botao.getLugar().setAcessivel(true);
        }

        DadosApp.INSTANCIA.adicionarSala(sala);

        parentFrame.adicionar(sala);

        JOptionPane.showMessageDialog(this, "Sala adicionada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

        parentFrame.setVisible(true);

        dispose();
    }
}

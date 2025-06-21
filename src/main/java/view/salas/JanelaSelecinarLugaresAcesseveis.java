//Francisco Pedrosa - 2181248
package view.salas;

import model.DadosApp;
import model.Sala;
import view.Janela;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class JanelaSelecinarLugaresAcesseveis extends Janela {
    private final JFrame parentFrame;
    private JPanel pnlSelecionarLugaresAcessiveis;
    private JButton btnSair;
    private JLabel lblLugaresAcessiveis;
    private JPanel pnlConfiguracaoSala;
    private JButton btnConfirmar;
    private BotaoLugar[][] botoes;
    private final Sala sala;

    private static final String ERRO_1 = "Selecionou o número errado de lugares acessíveis.";

    private static final String SUCESSO_1 = "Sala adicionada com sucesso!";

    public static void main(String[] args) {
        JanelaSelecinarLugaresAcesseveis janela = new JanelaSelecinarLugaresAcesseveis(null, DadosApp.getInstance().getSalas().getLast());
    }

    public JanelaSelecinarLugaresAcesseveis(JFrame parentFrame, Sala sala) {
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

    private void btnSairActionPerformed(ActionEvent e) {
        dispose();
        if (parentFrame != null) {
            parentFrame.setVisible(true);
        }
    }

    private void btnConfirmarActionPerformed(ActionEvent e) {

        var botoesSelecionados = obterLugaresSelecionados();

        int numeroLugaresAcessiveis = sala.getNumeroLugaresAcessiveis();
        if (botoesSelecionados.size() != numeroLugaresAcessiveis) {
            mostrarErro(ERRO_1);
            return;
        }

        atualizarLugares(botoesSelecionados);

        DadosApp.getInstance().adicionarSala(sala);

        if (parentFrame instanceof JanelaSalas) {
            ((JanelaSalas) parentFrame).adicionar(sala);
        }

        mostrarSucesso(SUCESSO_1);

        dispose();

        if (parentFrame != null) {
            parentFrame.setVisible(true);
        }
    }

    private ArrayList<BotaoLugar> obterLugaresSelecionados() {
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
        return botoesSelecionados;
    }

    private void atualizarLugares(ArrayList<BotaoLugar> botoesSelecionados) {
        for (BotaoLugar botao : botoesSelecionados) {
            botao.getLugar().setAcessivel(true);
        }
    }
}

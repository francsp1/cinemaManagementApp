//Rodrigo Correia 2231856
package view;

import model.DadosApp;
import model.Filme;
import model.Sala;
import model.Sessao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;

public class JanelaPesquisaSessoes extends Janela{
    private JButton btnSair;
    private JButton btnProcurar;
    private JComboBox comboBoxDia;
    private JComboBox comboBoxMes;
    private JComboBox comboBoxFilmes;
    private JButton removerSessaoButton;
    private JSpinner anoSpinner;
    private JTable tabelaSessoes;
    private JPanel painel1;
    private DefaultTableModel tableModel;
    private final JFrame parentFrame;
    private final boolean isGestor;
    private DefaultListModel<Sessao> modeloLista;
    private JList lstSessoes;


    public JanelaPesquisaSessoes(JFrame parentFrame, boolean isGestor){

        super("Pesquisa de Sessões");
        this.parentFrame = parentFrame;
        this.isGestor = isGestor;
        setContentPane(painel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setSize(900,600);
        setVisible(true);

        comboBoxFilmes.addItem("");

        for (Filme filme : DadosApp.getInstance().getFilmes()) {
            comboBoxFilmes.addItem(filme.getTitulo());
        }

        comboBoxDia.addItem(0); // 0 = não filtrar
        for (int i = 1; i <= 31; i++) {
            comboBoxDia.addItem(i);
        }

        // Carrega meses (1 a 12)
        comboBoxMes.addItem(0); // 0 = não filtrar
        for (int i = 1; i <= 12; i++) {
            comboBoxMes.addItem(i);
        }

        anoSpinner.setModel(new SpinnerNumberModel(2025, 2025, 3000, 1));



        addListeners();

        preencherListaSessoes();

    }

    private void addListeners() {
        btnProcurar.addActionListener(this::btnProcurarActionPerformed);
        btnSair.addActionListener(this::btnSairActionPerformed);
    }

    private void btnProcurarActionPerformed(ActionEvent e) {
        procurarSessoes();
    }

    private void btnSairActionPerformed(ActionEvent e) {
        setVisible(false);
        if(parentFrame!=null){
            parentFrame.setVisible(true);
        }
    }

    public void preencherListaSessoes() {
        modeloLista = new DefaultListModel<>();
        lstSessoes.setModel(modeloLista);

        for (Sessao sessao : DadosApp.getInstance().getSessoes()) {
            modeloLista.addElement(sessao);
        }
    }

    private void procurarSessoes() {
        modeloLista.clear(); // limpa a lista antes de adicionar novas sessões

        String filmeSelecionado = (String) comboBoxFilmes.getSelectedItem();
        int dia = (int) comboBoxDia.getSelectedItem();
        int mes = (int) comboBoxMes.getSelectedItem();
        int ano = (int) anoSpinner.getValue();

        boolean encontrou = false;

        for (Sessao sessao : DadosApp.getInstance().getSessoes()) {
            String tituloSessao = sessao.getFilme().getTitulo();
            int diaSessao = sessao.getDataHora().getDayOfMonth();
            int mesSessao = sessao.getDataHora().getMonthValue();
            int anoSessao = sessao.getDataHora().getYear();

            // FILTROS:
            if (!filmeSelecionado.isEmpty() && !tituloSessao.equals(filmeSelecionado)) {
                continue;
            }

            if (dia != 0 && diaSessao != dia) {
                continue;
            }

            if (mes != 0 && mesSessao != mes) {
                continue;
            }

            if (anoSessao != ano) {
                continue;
            }

            modeloLista.addElement(sessao);
            encontrou = true;
        }

        if (!encontrou) {
            showTimedDialog("Não foram encontradas sessões com os filtros escolhidos", 2000);
        }
    }

    private void showTimedDialog(String mensagem, int duracaoMillis) {
        JOptionPane pane = new JOptionPane(mensagem, JOptionPane.INFORMATION_MESSAGE);
        JDialog dialog = pane.createDialog(this, "Info");
        dialog.setModal(false);
        dialog.setVisible(true);

        // Fecha automaticamente após X milissegundos
        new Timer(duracaoMillis, e -> dialog.dispose()).start();
    }





}

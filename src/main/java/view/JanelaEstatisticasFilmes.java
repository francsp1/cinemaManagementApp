package view;

import model.DadosApp;
import model.Filme;
import model.VendaBilhete;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class JanelaEstatisticasFilmes extends Janela {

    private JPanel mainPanel;
    private JTable table1;
    private JList<String> listaTIpoBilhetes;
    private JComboBox<String> mesInicio;
    private JComboBox<String> anoInicio;
    private JTable table2;
    private JComboBox<String> mesFim;
    private JComboBox<String> anoFim;
    private JButton btnPesquisar;

    public JanelaEstatisticasFilmes(JFrame parent) {
        super("Estatísticas dos Filmes");
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(parent);
        setVisible(true);

        preencherCombos();
        preencherListaTipos();

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                parent.setVisible(true);
            }
        });

        btnPesquisar.addActionListener((ActionEvent e) -> atualizarTabelaEstatisticas());

        String[] columnNames = {"Dia da semana", "Faturação"};
        String[] dias = {"Segunda-Feira", "Terça-Feira", "Quarta-Feira", "Quinta-Feira", "Sexta-Feira", "Sábado", "Domingo"};
        String[] faturacao = {"1000€", "4300€", "2000€", "3000€", "5000€", "6000€", "7000€"};
        Object[][] data2 = new Object[dias.length][2];

        for (int i = 0; i < dias.length; i++) {
            data2[i][0] = dias[i];
            data2[i][1] = faturacao[i];
        }

        DefaultTableModel model2 = new DefaultTableModel(data2, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table2.setModel(model2);
        table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private void atualizarTabelaEstatisticas() {

        try {
            int anoInicioSelecionado = Integer.parseInt((String) anoInicio.getSelectedItem());
            int mesInicioSelecionado = mesInicio.getSelectedIndex() + 1;
            int anoFimSelecionado = Integer.parseInt((String) anoFim.getSelectedItem());
            int mesFimSelecionado = mesFim.getSelectedIndex() + 1;

            LocalDate inicio = LocalDate.of(anoInicioSelecionado, mesInicioSelecionado, 1);
            LocalDate fim = LocalDate.of(anoFimSelecionado, mesFimSelecionado,
                    LocalDate.of(anoFimSelecionado, mesFimSelecionado, 1).lengthOfMonth());

            if (fim.isBefore(inicio)) {
                JOptionPane.showMessageDialog(this, "A data de fim não pode ser anterior à data de início.",
                        "Intervalo inválido", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Map<String, List<Map.Entry<Filme, Integer>>> stats =
                    DadosApp.getInstance().filmesMaisVistosPorTipoBilhete(
                            DadosApp.getInstance().getVendasBilhete(), inicio, fim
                    );

            String tipoSelecionado = listaTIpoBilhetes.getSelectedValue();
            if (tipoSelecionado == null) {
                JOptionPane.showMessageDialog(this, "Selecione um tipo de bilhete.");
                return;
            }
            List<Map.Entry<Filme, Integer>> filmesMaisVistos = stats.get(tipoSelecionado);

            String[] colunas = {"Posição", "Filme", "Realizador", "Vendas"};
            Object[][] dados = new Object[filmesMaisVistos != null ? filmesMaisVistos.size() : 0][4];
            if (filmesMaisVistos != null) {
                for (int i = 0; i < filmesMaisVistos.size(); i++) {
                    Filme f = filmesMaisVistos.get(i).getKey();
                    int vendas = filmesMaisVistos.get(i).getValue();
                    dados[i][0] = i + 1;
                    dados[i][1] = f.getTitulo();
                    dados[i][2] = f.getRealizador();
                    dados[i][3] = vendas;
                }
            }
            DefaultTableModel model = new DefaultTableModel(dados, colunas) {
                public boolean isCellEditable(int row, int col) { return false; }
            };
            table1.setModel(model);
            System.out.println("Filmes encontrados: " + (filmesMaisVistos != null ? filmesMaisVistos.size() : 0));


        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao calcular estatísticas: " + ex.getMessage());
        }


    }

    private void preencherCombos() {
        for (int ano = 2020; ano <= 2025; ano++) {
            anoInicio.addItem(String.valueOf(ano));
            anoFim.addItem(String.valueOf(ano));
        }
        String[] meses = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho",
                "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
        for (String mes : meses) {
            mesInicio.addItem(mes);
            mesFim.addItem(mes);
        }
    }

    private void preencherListaTipos() {
        DefaultListModel<String> model = new DefaultListModel<>();
        model.addElement("Normal");
        model.addElement("Estudante");
        model.addElement("Sénior");
        listaTIpoBilhetes.setModel(model);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }


}

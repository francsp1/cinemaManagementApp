package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class JanelaEstatisticasFilmes extends Janela {

    private JPanel mainPanel;
    private JTable table1;
    private JList list1;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JTable table2;

    public JanelaEstatisticasFilmes() {
        super("Estatisticas dos Filmes");
        String[] columnNamesTabela1 = {"Posição no TOP", "Filme", "Realizador"};
        String[] filmes = {"O Senhor dos Anéis", "Matrix", "Pulp Fiction", "Interstellar", "Inception"};
        String[] realizadores = {"Peter Jackson", "Lana Wachowski", "Quentin Tarantino", "Christopher Nolan", "Christopher Nolan"};
        Object[][] data = new Object[filmes.length][3];

        for (int i = 0; i < filmes.length; i++) {
            data[i][0] = i + 1;
            data[i][1] = filmes[i];
            data[i][2] = realizadores[i];
        }

        DefaultTableModel model = new DefaultTableModel(data, columnNamesTabela1) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table1.setModel(model);
        table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

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

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Estatísticas");
            frame.setSize(800, 400);
            frame.setContentPane(new JanelaEstatisticasFilmes().getMainPanel());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

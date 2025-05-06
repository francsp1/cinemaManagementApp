package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class JanelaEstatisticas {

    private JPanel mainPanel;
    private JTable table1;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JList list1;

    public JanelaEstatisticas() {
        String[] columnNames = {"Posição no TOP", "Filme", "Realizador"};
        String[] filmes = {"O Senhor dos Anéis", "Matrix", "Pulp Fiction", "Interstellar", "Inception"};
        String[] realizadores = {"Peter Jackson", "Lana Wachowski", "Quentin Tarantino", "Christopher Nolan", "Christopher Nolan"};
        Object[][] data = new Object[filmes.length][3];

        for (int i = 0; i < filmes.length; i++) {
            data[i][0] = i + 1;
            data[i][1] = filmes[i];
            data[i][2] = realizadores[i];
        }

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table1.setModel(model);
        table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Estatísticas");
            frame.setSize(800, 400);
            frame.setContentPane(new JanelaEstatisticas().getMainPanel());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

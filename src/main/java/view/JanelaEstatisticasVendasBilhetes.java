package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class JanelaEstatisticasVendasBilhetes {
    private JPanel mainPanel;
    private JTable table1;

    public JanelaEstatisticasVendasBilhetes() {
        String[] columnNames = {"Dia da semana", "Faturação"};
        String[] dias = {"Segunda-Feira", "Terça-Feira", "Quarta-Feira", "Quinta-Feira", "Sexta-Feira", "Sábado", "Domingo"};
        String[] faturacao = {"1000€", "4300€", "2000€", "3000€", "5000€", "6000€", "7000€"};
        Object[][] data = new Object[dias.length][2];

        for (int i = 0; i < dias.length; i++) {
            data[i][0] = dias[i];
            data[i][1] = faturacao[i];
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
            frame.setContentPane(new JanelaEstatisticasVendasBilhetes().getMainPanel());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

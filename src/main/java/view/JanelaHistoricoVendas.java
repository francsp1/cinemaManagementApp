package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class JanelaHistoricoVendas {
    private JTable table1;
    private JComboBox comboBox1;
    private JButton voltarButton;
    private JPanel mainPanel;

    public JanelaHistoricoVendas() {
        // Initialize the components here if needed
        String[] columnNames = {"Produtos","Vendedor","Data", "Preço"};
        String[] produtos = {"2x Bilhete +65", "Pacote Estudante"};
        String[] vendedor = {"João", "Maria"};
        String[] data = {"11-05-2025", "10-05-2025"};
        String[] preco = {"8.00 €", "4.50 €"};
        Object[][] dataTable = new Object[produtos.length][4];  // Inicializa com o tamanho necessário

        for (int i = 0; i < +produtos.length; i++) {
            dataTable[i][0] = produtos[i];
            dataTable[i][1] = vendedor[i];
            dataTable[i][2] = data[i];
            dataTable[i][3] = preco[i];
        }

        DefaultTableModel model = new DefaultTableModel(dataTable, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make cells non-editable
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
            JFrame frame = new JFrame("JanelaHistoricoVendas");
            frame.setSize(800, 400);
            frame.setContentPane(new JanelaHistoricoVendas().getMainPanel());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

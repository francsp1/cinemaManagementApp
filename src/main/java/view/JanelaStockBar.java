package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class JanelaStockBar extends JFrame{
    private JPanel mainPanel;
    private JTable table1;
    private JButton escolherFornecedorButton;
    private JButton adicionarButton1;
    private JTextField textField1;
    private JTable table2;
    private JTable table3;
    private JTable table4;

    public JanelaStockBar() {
        String[] columnNames = {"Produto", "Quantidade"};
        String[] products = {"Coca-Cola", "Pepsi", "Fanta"};
        String[] stock = {"24", "0", "100"};
        Object[][] data = new Object[products.length][2];  // Inicializa com o tamanho necessário

        for (int i = 0; i < products.length; i++) {
            data[i][0] = products[i];
            data[i][1] = stock[i];
        }

        String[] colunasHistorico = {"Fornecedor", "Nr Fatura", "Data", "Valor (€)"};
        Object[][] historico = {
                {"Abílio", 3, "30/04/2025", 4.50},
                {"Bina", 1, "29/04/2025", 1.20},
                {"José Augusto", 2, "28/04/2025", 3.00},
                {"Bina", 5, "27/04/2025", 3.50},
                {"Bina", 10, "26/04/2025", 2.75},
                {"Bina", 7, "25/04/2025", 3.20},
                {"Abílio", 6, "24/04/2025", 2.50},
                {"Abílio", 4, "23/04/2025", 2.40}
        };

        String[] colunasFornecedores = {"Fornecedor"};
        Object[][] fornecedores = {
                {"Abilio"},
                {"José Augusto"},
                {"Bina"}
        };

        String[] colunasVendaProdutos = {"Produto", "Quantidade","Data", "Total (€)"};
        Object[][] vendaProdutos = {
                {"Coca-Cola", 2,"30/04/2025",3.00},
                {"Pepsi", 1,"30/04/2025",1.50},
                {"Fanta", 3,"30/04/2025", 4.50}
        };

        DefaultTableModel model4 = new DefaultTableModel(vendaProdutos, colunasVendaProdutos) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Nenhuma célula pode ser editada
            }
        };
        table4.setModel(model4);

        table4.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        DefaultTableModel model3 = new DefaultTableModel(fornecedores, colunasFornecedores) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Nenhuma célula pode ser editada
            }
        };
        table3.setModel(model3);

        table3.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        DefaultTableModel model2 = new DefaultTableModel(historico, colunasHistorico) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Nenhuma célula pode ser editada
            }
        };
        table2.setModel(model2);

        table2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Nenhuma célula pode ser editada
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
            JFrame frame = new JFrame("Stock Bar");
            frame.setSize(800, 400);
            frame.setContentPane(new JanelaStockBar().getMainPanel());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

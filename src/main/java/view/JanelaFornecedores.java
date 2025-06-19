package view;

import model.Fornecedor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class JanelaFornecedores extends Janela {
    private JPanel mainPanel;
    private JTable table1;
    private JFormattedTextField formattedTextField1;
    private JButton adicionarAoCarrinhoButton;
    private JButton FInalizarComprarEGerarButton;
    private JTable table2;

    public JanelaFornecedores(JFrame parent, Fornecedor fornecedor) {
        super("Fornecedor: " + fornecedor.getNome());
        // código para mostrar os dados do fornecedor
        setLocationRelativeTo(parent);
        setVisible(true);
        String[] columnNames = {"Produto", "Preço unidade (€)"};
        String[] products = {"Coca-Cola", "Pepsi", "Fanta"};
        String[] preco = {"5", "5", "3"};
        Object[][] data = new Object[products.length][2];  // Inicializa com o tamanho necessário

        for (int i = 0; i < products.length; i++) {
            data[i][0] = products[i];
            data[i][1] = preco[i];
        }

        String[] colunasCarrinho = {"Produto", "Quantidade", "Total (€)"};
        Object[][] carrinho = {
                {"Coca-Cola", 2, 10.00},
                {"Pepsi", 1, 5.00},
                {"Fanta", 3, 9.00},
        };

        DefaultTableModel model = new DefaultTableModel(carrinho, colunasCarrinho) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Nenhuma célula pode ser editada
            }
        };
        table2.setModel(model);

        table2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


        DefaultTableModel model2 = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Nenhuma célula pode ser editada
            }
        };
        table1.setModel(model2);

        table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    public JPanel getMainPanel() {
           return mainPanel;
    }

}

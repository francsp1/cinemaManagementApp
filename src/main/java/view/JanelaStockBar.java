package view;

import model.DadosApp;
import model.Fornecedor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class JanelaStockBar extends Janela {
    private JPanel mainPanel;
    private JTable table1;
    private JButton escolherFornecedorButton;
    private JButton adicionarButton1;
    private JTextField textField1;
    private JTable table2;
    private JTable table3;
    private JTable table4;

    public JanelaStockBar(JFrame parent) {
        super("Stock Bar");
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(parent);
        setVisible(true);
        String[] columnNames = {"Produto", "Quantidade"};
        String[] products = {"Coca-Cola", "Pepsi", "Fanta"};
        String[] stock = {"24", "0", "100"};
        Object[][] data = new Object[products.length][2];

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
        List<Fornecedor> listaFornecedores = DadosApp.getInstance().getFornecedores();
        System.out.println("Fornecedores disponíveis: " + listaFornecedores.size());
        Object[][] fornecedores = new Object[listaFornecedores.size()][1];

        for (int i = 0; i < listaFornecedores.size(); i++) {
            fornecedores[i][0] = listaFornecedores.get(i).getNome();
        }

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

        escolherFornecedorButton.addActionListener(e -> {
            int selectedRow = table3.getSelectedRow();
            if (selectedRow != -1) {
                String nomeFornecedor = (String) table3.getValueAt(selectedRow, 0);
                List<Fornecedor> fornecedoresList = DadosApp.getInstance().getFornecedores();
                for (Fornecedor f : fornecedoresList) {
                    if (f.getNome().equals(nomeFornecedor)) {
                        new JanelaFornecedores(this, f);
                        return;
                    }
                }
                JOptionPane.showMessageDialog(this, "Fornecedor não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um fornecedor primeiro.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });

    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

}

package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.DadosApp;
import model.Fornecedor;
import model.StockProduto;

import java.util.List;

public class JanelaVenda extends Janela {
    private JPanel Cabecalho;
    private JButton finalizarCompraButton;
    private JPanel LadoVenda;
    private JList Lista;
    private JScrollPane ListaScroll;
    private JPanel LadoBilhete;
    private JComboBox opTipo;
    private JComboBox opSessao;
    private JButton adicionarBilheteButton;
    private JTable tabelaProdutos;
    private JButton escolherButton;
    private JTextField textField1;
    private JButton adicionarProdutoButton;
    private JPanel mainPanel;
    private JComboBox comboBox2;
    private JButton cancelarOperaçãoButton;
    private JButton removerLinhaButton;

    public JanelaVenda(JFrame parent) {
        super("Registar Venda");

        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(parent);
        setVisible(true);

        // Initialize the table with stock products
        String[] columnNames = {"Produto"};
        List<StockProduto> stockProdutos = DadosApp.getInstance().getStockProdutos();
        Object[][] data = new Object[stockProdutos.size()][1];
        for (int i = 0; i < stockProdutos.size(); i++) {
            data[i][0] = stockProdutos.get(i).getProduto().getNome();
        }
        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Prevent editing of table cells
            }
        };

        tabelaProdutos.setModel(model);
        tabelaProdutos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Opcoes de tipo de bilhete
        DadosApp.getInstance().getTicketTypes().keySet().forEach(opTipo::addItem);

        //TODO: Opcoes de sessao

        // botao de adicionar produto
        adicionarProdutoButton.addActionListener(e -> {
            int selectedRow = tabelaProdutos.getSelectedRow();
            if (selectedRow != -1) {
                String produto = (String) tabelaProdutos.getValueAt(selectedRow, 0);
                // Aqui você pode adicionar lógica para adicionar o produto à venda

                double precoProduto = stockProdutos.get(selectedRow).getProduto().getPreco();

                JOptionPane.showMessageDialog(this, "Produto " + produto + " adicionado à venda.");
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, selecione um produto.");
            }
        });


    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

}

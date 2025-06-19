package view;

import model.Fornecedor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class JanelaFornecedores extends Janela {
    private JPanel mainPanel;
    private JTable table1;
    private JFormattedTextField quantidadeProduto;
    private JButton adicionarAoCarrinhoButton;
    private JButton FInalizarComprarEGerarButton;
    private JTable table2;
    private JLabel precoTotal;

    public JanelaFornecedores(JFrame parent, Fornecedor fornecedor) {
        super("Fornecedor: " + fornecedor.getNome());
        setContentPane(mainPanel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(parent);
        setVisible(true);

        // Colunas da tabela de produtos
        String[] columnNames = {"Produto", "Preço unidade (€)"};

        // Obter os dados reais do fornecedor
        Object[][] data = new Object[fornecedor.getTabelaPrecos().size()][2];
        int i = 0;
        for (var entry : fornecedor.getTabelaPrecos().entrySet()) {
            data[i][0] = entry.getKey().getNome();
            data[i][1] = String.format("%.2f", entry.getValue()); // formato com 2 casas decimais
            i++;
        }

        // Criar o modelo da tabela de produtos
        DefaultTableModel modelProdutos = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table1.setModel(modelProdutos);
        table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Exemplo de dados do carrinho (deves substituir isto com lógica real de carrinho)
        String[] colunasCarrinho = {"Produto", "Quantidade", "Total (€)"};
        Object[][] carrinho = {}; // inicial vazio
        DefaultTableModel modelCarrinho = new DefaultTableModel(carrinho, colunasCarrinho) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table2.setModel(modelCarrinho);
        table2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        adicionarAoCarrinhoButton.addActionListener(e -> {
            int selectedRow = table1.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Selecione um produto primeiro.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String quantidadeStr = quantidadeProduto.getText().trim();
            int quantidade;
            try {
                quantidade = Integer.parseInt(quantidadeStr);
                if (quantidade <= 0) throw new NumberFormatException();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Insira uma quantidade válida (número inteiro positivo).", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String nomeProduto = (String) table1.getValueAt(selectedRow, 0);
            double precoUnitario = Double.parseDouble(((String) table1.getValueAt(selectedRow, 1)).replace(",", "."));
            double total = precoUnitario * quantidade;

            // Adiciona ao carrinho
            modelCarrinho.addRow(new Object[]{
                    nomeProduto,
                    quantidade,
                    String.format("%.2f", total)
            });

            atualizarPrecoTotal(modelCarrinho);
        });
    }

    private void atualizarPrecoTotal(DefaultTableModel modelCarrinho) {
        double totalCarrinho = 0.0;
        for (int i = 0; i < modelCarrinho.getRowCount(); i++) {
            String valorStr = (String) modelCarrinho.getValueAt(i, 2);
            valorStr = valorStr.replace(",", ".");
            try {
                totalCarrinho += Double.parseDouble(valorStr);
            } catch (NumberFormatException ignore) {}
        }
        precoTotal.setText("Preço total do carrinho: " + String.format("%.2f", totalCarrinho) + " €");
    }


    public JPanel getMainPanel() {
           return mainPanel;
    }

}

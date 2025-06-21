//Lourenço Ferreira 2230972
package view;

import model.DadosApp;
import model.Fornecedor;
import model.Produto;
import model.StockProduto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JanelaFornecedores extends Janela {
    private JPanel mainPanel;
    private JTable table1;
    private JFormattedTextField quantidadeProduto;
    private JButton adicionarAoCarrinhoButton;
    private JButton FInalizarComprarEGerarButton;
    private JTable table2;
    private JLabel precoTotal;
    private JLabel nomeFornecedor;
    private JanelaStockBar janelaStockBar;

    public JanelaFornecedores(JanelaStockBar parent, Fornecedor fornecedor) {
        super("Fornecedor: " + fornecedor.getNome());
        this.janelaStockBar = parent;
        setContentPane(mainPanel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(parent);
        setVisible(true);

        nomeFornecedor.setText("Fornecedor: " + fornecedor.getNome());

        String[] columnNames = {"Produto", "Preço unidade (€)"};

        Object[][] data = new Object[fornecedor.getTabelaPrecos().size()][2];
        int i = 0;
        for (var entry : fornecedor.getTabelaPrecos().entrySet()) {
            data[i][0] = entry.getKey().getNome();
            data[i][1] = String.format("%.2f", entry.getValue());
            i++;
        }

        DefaultTableModel modelProdutos = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table1.setModel(modelProdutos);
        table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        String[] colunasCarrinho = {"Produto", "Quantidade", "Total (€)"};
        Object[][] carrinho = {};
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

            modelCarrinho.addRow(new Object[]{
                    nomeProduto,
                    quantidade,
                    String.format("%.2f", total)
            });

            atualizarPrecoTotal(modelCarrinho);
        });

        FInalizarComprarEGerarButton.addActionListener(e -> {
            if (modelCarrinho.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "O carrinho está vazio!", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            java.util.Map<model.Produto, Integer> produtosFatura = new java.util.LinkedHashMap<>();
            double totalFatura = 0.0;

            for (int j = 0; j < modelCarrinho.getRowCount(); j++) {
                String nomeProduto = (String) modelCarrinho.getValueAt(j, 0);
                int quantidade = (int) modelCarrinho.getValueAt(j, 1);

                model.Produto produto = null;
                for (var entry : fornecedor.getTabelaPrecos().entrySet()) {
                    if (entry.getKey().getNome().equals(nomeProduto)) {
                        produto = entry.getKey();
                        break;
                    }
                }
                if (produto != null) {
                    produtosFatura.put(produto, quantidade);
                }

                String totalStr = (String) modelCarrinho.getValueAt(j, 2);
                totalStr = totalStr.replace(",", ".");
                try {
                    totalFatura += Double.parseDouble(totalStr);
                } catch (NumberFormatException ignore) {}
            }


            List<Produto> produtosParaRemover = new ArrayList<>();

            for (Map.Entry<Produto, Integer> entry : produtosFatura.entrySet()) {
                String nomeProduto = entry.getKey().getNome();
                int quantidadeComprada = entry.getValue();
                StockProduto stock = DadosApp.getInstance().getStockProdutoPorNome(nomeProduto);
                if (stock != null) {
                    stock.adicionar(quantidadeComprada);
                } else {
                    int opcao = JOptionPane.showConfirmDialog(this,
                            "O produto \"" + nomeProduto + "\" não existe no catálogo de stock.\n" +
                                    "Deseja adicionar ao catálogo?",
                            "Produto não encontrado",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE);
                    if (opcao == JOptionPane.YES_OPTION) {
                        DadosApp.getInstance().getStockProdutos().add(new StockProduto(entry.getKey(), quantidadeComprada));
                    } else {
                        produtosParaRemover.add(entry.getKey());
                    }
                }
            }

            for (Produto p : produtosParaRemover) {
                produtosFatura.remove(p);

                // Opcional: remover da tabela do carrinho (table2)
                for (int k = 0; k < modelCarrinho.getRowCount(); k++) {
                    String nomeProdutoCarrinho = (String) modelCarrinho.getValueAt(k, 0);
                    if (nomeProdutoCarrinho.equals(p.getNome())) {
                        modelCarrinho.removeRow(k);
                        break;
                    }
                }
            }
            if (produtosFatura.isEmpty()) {

                JOptionPane.showMessageDialog(this, "Nenhum produto válido no carrinho. A compra foi cancelada.", "Aviso", JOptionPane.WARNING_MESSAGE);
                modelCarrinho.setRowCount(0);
                atualizarPrecoTotal(modelCarrinho);
                return;
            }

            totalFatura = 0.0;
            for (Map.Entry<Produto, Integer> entry : produtosFatura.entrySet()) {
                double precoUnitario = fornecedor.getTabelaPrecos().get(entry.getKey());
                totalFatura += precoUnitario * entry.getValue();
            }


            model.FaturaFornecedor fatura = new model.FaturaFornecedor(fornecedor, produtosFatura, totalFatura);

            model.DadosApp.getInstance().adicionarFaturaFornecedor(fatura);

            model.DadosApp.gravarDados();

            JOptionPane.showMessageDialog(this, "Compra finalizada!\nFatura Nº: " + fatura.getNumeroFatura() +
                    "\nValor total: " + String.format("%.2f", fatura.getValorTotal()) + " €");

            modelCarrinho.setRowCount(0);
            atualizarPrecoTotal(modelCarrinho);

            DadosApp.gravarDados();
            if (janelaStockBar != null) {
                janelaStockBar.atualizarStock();
                janelaStockBar.atualizarHistorico();
            }


            if (janelaStockBar != null) {
                janelaStockBar.atualizarHistorico();
            }
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


    public JTable getTable1() { return table1; }
    public JFormattedTextField getQuantidadeProduto() { return quantidadeProduto; }
    public JButton getAdicionarAoCarrinhoButton() { return adicionarAoCarrinhoButton; }
    public JButton getFinalizarComprarEGerarButton() { return FInalizarComprarEGerarButton; }
    public JTable getTable2() { return table2; }

}

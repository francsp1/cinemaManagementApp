package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.DadosApp;
import model.StockProduto;
import model.linhaFatura;

import java.util.ArrayList;
import java.util.List;

public class JanelaVenda extends Janela {
    private JPanel Cabecalho;
    private JButton finalizarCompraButton;
    private JPanel LadoVenda;
    private JList listaItems;
    private JScrollPane ListaScroll;
    private JPanel LadoBilhete;
    private JComboBox opTipo;
    private JComboBox opSessao;
    private JButton adicionarBilheteButton;
    private JTable tabelaProdutos;
    private JButton escolherButton;
    private JTextField txtBoxQuantidade;
    private JButton adicionarProdutoButton;
    private JPanel mainPanel;
    private JComboBox comboBox2;
    private JButton cancelarOperaçãoButton;
    private JButton removerLinhaButton;
    private JLabel valorTotal;

    //para gurdar as linhas de fatura
    private List<linhaFatura> linhasFaturaProduto = new ArrayList<>();

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

                double precoProduto = stockProdutos.get(selectedRow).getProduto().getPreco();
                int quantidade = Integer.parseInt(txtBoxQuantidade.getText());

                if (txtBoxQuantidade.getText().equals("")) {
                    JOptionPane.showMessageDialog(this, "Por favor, insira uma quantidade.");
                    return;
                }
                if(quantidade <= 0) {
                    JOptionPane.showMessageDialog(this, "Quantidade deve ser maior que zero.");
                    return;
                }

                double total = precoProduto * quantidade;

                //Criar linhaFatura
                linhaFatura novaLinha = new linhaFatura(stockProdutos.get(selectedRow).getProduto(), quantidade, total);

                // Adicionar linhaFatura à lista
                linhasFaturaProduto.add(novaLinha);

                //adicionar linhaFatura à venda
                listaItems.setListData(linhasFaturaProduto.stream().map(linhaFatura::toString).toArray());

                //atualizar o valor total
                atualizarValorTotal();

                //TODO: atualizar opcoes de bundle

                JOptionPane.showMessageDialog(this, "Produto " + produto + " adicionado à venda.");
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, selecione um produto.");
            }
        });

        // Botao para remover elemento da lista de venda
        removerLinhaButton.addActionListener(e -> {
            int selectedIndex = listaItems.getSelectedIndex();
            if (selectedIndex != -1) {
                // Remover a linha selecionada da lista
                linhasFaturaProduto.remove(selectedIndex);
                listaItems.setListData(linhasFaturaProduto.stream().map(linhaFatura::toString).toArray());

                // Atualizar o valor total
                atualizarValorTotal();
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, selecione uma linha para remover.");
            }
        });

        // Botao de finalizar compra
        finalizarCompraButton.addActionListener(e -> {
            if (linhasFaturaProduto.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Não há produtos na venda.");
                return;
            }

            // Aqui você pode adicionar a lógica para finalizar a compra
            // Por exemplo, salvar a fatura em um arquivo ou banco de dado

            JOptionPane.showMessageDialog(this, "Venda finalizada com sucesso!");
            dispose(); // Fecha a janela após finalizar a venda
        });


    }

    private void atualizarValorTotal(){
        double valorTotal = 0;
        for (linhaFatura linha : linhasFaturaProduto) {
            valorTotal += linha.getPrecoTotal();
        }

        double valorTotalRounded = Math.round(valorTotal * 100.0) / 100.0;

        this.valorTotal.setText(valorTotalRounded+ " €");

    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

}

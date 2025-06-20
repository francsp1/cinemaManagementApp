package view;

import model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class JanelaAdicionarBundle extends Janela{
    private JList listaItems;
    private JButton criarBundleButton;
    private JButton cancelarOperaçãoButton;
    private JTextField textBoxNome;
    private JTable tabelaItems;
    private JButton adicionarButton;
    private JButton removerButton;
    private JTextField textBoxQuantidade;
    private JTextField textBoxPreco;
    private JPanel mainPanel;
    private JScrollPane scroll;

    private final JFrame parentFrame;
    ArrayList<Produto> produtos = new ArrayList<>();
    ArrayList<String> bilhetes = new ArrayList<>();

    public JanelaAdicionarBundle(JFrame parent) {
        super("Adicionar Bundle");
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(parent);
        setVisible(true);
        this.parentFrame = parent;

        String[] columnNames = {"Item"};

        // Vai buscar os produtos do stock
        List<StockProduto> stockProdutos = DadosApp.getInstance().getStockProdutos();
        Object[][] data = new Object[stockProdutos.size()][1];
        for (int i = 0; i < stockProdutos.size(); i++) {
            data[i][0] = stockProdutos.get(i).getProduto().getNome();
        }

        // Vai buscar os bilhetes
        List<String> tiposBilhete = DadosApp.getInstance().getTicketTypes().keySet().stream().toList();
        Object[][] dataBilhetes = new Object[tiposBilhete.size()][1];
        for (int i = 0; i < tiposBilhete.size(); i++) {
            dataBilhetes[i][0] = tiposBilhete.get(i);
        }

        // Combina os dados dos produtos e bilhetes
        Object[][] combinedData = new Object[data.length + dataBilhetes.length][1];
        for (int i = 0; i < data.length; i++) {
            combinedData[i][0] = data[i][0];
        }
        for (int i = 0; i < dataBilhetes.length; i++) {
            combinedData[data.length + i][0] = dataBilhetes[i][0];
        }

        // Cria o modelo da tabela com os dados combinados
        Object[][] finalData = combinedData;
        DefaultTableModel model = new DefaultTableModel(finalData, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Prevent editing of table cells
            }
        };
        // Define o modelo da tabela
        tabelaItems.setModel(model);
        // Define o modo de seleção da tabela
        tabelaItems.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        //botao adicionar item
        adicionarButton.addActionListener(e -> {
            int selectedRow = tabelaItems.getSelectedRow();
            if (selectedRow != -1) {
                String item = (String) tabelaItems.getValueAt(selectedRow, 0);
                int quantidade = Integer.parseInt(textBoxQuantidade.getText());
                if(quantidade <= 0) {
                    JOptionPane.showMessageDialog(this, "Quantidade deve ser maior que zero.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                //verifica se é um produto ou bilhete
                if (DadosApp.getInstance().getTicketTypes().containsKey(item)) {
                    // É um bilhete
                    for(int i=0; i < quantidade; i++) {
                        bilhetes.add(item);
                    }
                } else {
                    // É um produto
                    Produto produto = DadosApp.getInstance().getStockProdutos().stream()
                            .filter(sp -> sp.getProduto().getNome().equals(item))
                            .findFirst()
                            .orElse(null)
                            .getProduto();
                    for (int i = 0; i < quantidade; i++) {
                        produtos.add(produto);
                    }
                }

                // Atualiza a tabela para mostrar os itens adicionados
                atualizarListaItems();

            } else {
                JOptionPane.showMessageDialog(this, "Por favor, selecione um item para adicionar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });

        //botao remover item
        removerButton.addActionListener(e -> {
            int selectedRow = listaItems.getSelectedIndex();
            if (selectedRow != -1) {
                String item = (String) listaItems.getModel().getElementAt(selectedRow);
                if (produtos.stream().anyMatch(p -> p.getNome().equals(item))) {
                    // É um produto - Remove apenas uma instância
                    Produto produtoToRemove = produtos.stream()
                            .filter(p -> p.getNome().equals(item))
                            .findFirst()
                            .orElse(null);
                    if (produtoToRemove != null) {
                        produtos.remove(produtoToRemove);
                    }
                } else if (bilhetes.contains(item)) {
                    // É um bilhete - Remove apenas uma instância
                    bilhetes.remove(item);
                }
                // Atualiza a tabela para mostrar os itens restantes
                atualizarListaItems();
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, selecione um item para remover.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });

        //botao criar bundle
        criarBundleButton.addActionListener(e -> {
            String nomeBundle = textBoxNome.getText();
            if (nomeBundle.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, insira um nome para o bundle.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (produtos.isEmpty() && bilhetes.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, adicione pelo menos um produto ou bilhete ao bundle.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if( textBoxPreco.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, insira um preço para o bundle.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            double preco = Double.parseDouble(textBoxPreco.getText());

            Bundle novoBundle = new Bundle(nomeBundle, preco, produtos, bilhetes);
            DadosApp.getInstance().adicionarBundle(novoBundle);

            JOptionPane.showMessageDialog(this, "Bundle criado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            parentFrame.setVisible(true);
        });

        //botao cancelar operacao
        cancelarOperaçãoButton.addActionListener(e -> {
            dispose();
            parentFrame.setVisible(true);
        });
    }

    private void atualizarListaItems() {
        List<String> combinedItems = new ArrayList<>();
        combinedItems.addAll(produtos.stream()
                .map(Produto::getNome)
                .toList());
        combinedItems.addAll(bilhetes);

        listaItems.setListData(combinedItems.toArray(new String[0]));
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

}

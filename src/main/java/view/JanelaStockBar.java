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
    private JButton adicionarButton;
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


        atualizarStock();
        table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


        String[] colunasHistorico = {"Fornecedor", "Nr Fatura", "Data", "Valor (€)"};
        List<model.FaturaFornecedor> faturas = DadosApp.getInstance().getFaturasFornecedores();
        Object[][] historico = new Object[faturas.size()][4];

        for (int i = 0; i < faturas.size(); i++) {
            model.FaturaFornecedor f = faturas.get(i);
            historico[i][0] = f.getFornecedor().getNome();
            historico[i][1] = f.getNumeroFatura();
            historico[i][2] = f.getData().toString();
            historico[i][3] = String.format("%.2f", f.getValorTotal());
        }

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
                return false;
            }
        };
        table4.setModel(model4);

        table4.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        DefaultTableModel model3 = new DefaultTableModel(fornecedores, colunasFornecedores) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
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


//        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
//            @Override
//            public boolean isCellEditable(int row, int column) {
//                return false; // Nenhuma célula pode ser editada
//            }
//        };
//        table1.setModel(model);
//
//        table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

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

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                parent.setVisible(true);
            }
        });

        adicionarButton.addActionListener(e -> {
            String nomeProduto = textField1.getText().trim();

            if (nomeProduto.isEmpty()) {
                JOptionPane.showMessageDialog(this, "O nome do produto não pode estar em branco.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean existe = DadosApp.getInstance().getStockProdutos().stream()
                    .anyMatch(sp -> sp.getProduto().getNome().equalsIgnoreCase(nomeProduto));
            if (existe) {
                JOptionPane.showMessageDialog(this, "Esse produto já existe no stock.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            model.Produto novoProduto = new model.Produto(nomeProduto);
            model.StockProduto novoStock = new model.StockProduto(novoProduto, 0);
            DadosApp.getInstance().getStockProdutos().add(novoStock);
            DadosApp.gravarDados();

            // Atualiza a tabela de stock
            atualizarStock();

            // Limpa o campo de texto
            textField1.setText("");
            JOptionPane.showMessageDialog(this, "Produto adicionado com sucesso!", "Info", JOptionPane.INFORMATION_MESSAGE);
        });

    }

    public void atualizarHistorico() {
        List<model.FaturaFornecedor> faturas = model.DadosApp.getInstance().getFaturasFornecedores();
        Object[][] historico = new Object[faturas.size()][4];
        for (int i = 0; i < faturas.size(); i++) {
            model.FaturaFornecedor f = faturas.get(i);
            historico[i][0] = f.getFornecedor().getNome();
            historico[i][1] = f.getNumeroFatura();
            historico[i][2] = f.getData().toString();
            historico[i][3] = String.format("%.2f", f.getValorTotal());
        }
        DefaultTableModel model2 = new DefaultTableModel(historico, new String[]{"Fornecedor", "Nr Fatura", "Data", "Valor (€)"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table2.setModel(model2);
    }

    public void atualizarStock() {
        List<model.StockProduto> stockProdutos = model.DadosApp.getInstance().getStockProdutos();
        Object[][] data = new Object[stockProdutos.size()][2];
        for (int i = 0; i < stockProdutos.size(); i++) {
            data[i][0] = stockProdutos.get(i).getProduto().getNome();
            data[i][1] = stockProdutos.get(i).getQuantidade();
        }
        DefaultTableModel model = new DefaultTableModel(data, new String[]{"Produto", "Quantidade"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table1.setModel(model);
    }


    public JPanel getMainPanel() {
        return mainPanel;
    }

}

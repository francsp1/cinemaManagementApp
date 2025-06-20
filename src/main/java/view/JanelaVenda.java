package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import model.*;

import java.awt.*;
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

    private final JFrame parentFrame;
    private String lugarSelecionado; //para guardar o lugar selecionado

    //para gurdar as linhas de fatura
    private List<linhaFatura> linhasFaturaProduto = new ArrayList<>();

    public JanelaVenda(JFrame parent) {
        super("Registar Venda");

        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(parent);
        setVisible(true);

        this.parentFrame = parent;

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

        //Opcoes de sessao
        DadosApp.getInstance().getSessoes().forEach(sessao -> opSessao.addItem(sessao.getFilme().getTitulo() + " - " + sessao.getDataHora()));

        //botao escolher lugar
        escolherButton.addActionListener(e -> {

            String objSelecionado = (String) opSessao.getSelectedItem();

            Sessao sessao = DadosApp.getInstance().getSessaoPorTitulo(objSelecionado);

            Sala salaSelecionada = sessao.getSala();

            // Cria uma nova janela temporária
            JFrame janelaTemporaria = new JFrame("Visualização da Sala");
            janelaTemporaria.setSize(600, 400);
            janelaTemporaria.setLocationRelativeTo(null); // Centraliza na tela

            // Painel para desenhar a sala
            JPanel painelSala = new JPanel();

            // Adiciona o painel à janela
            janelaTemporaria.add(new JScrollPane(painelSala));

            // Chama o método de desenhar, passando o painel correto
            desenharSalaNaJanela(salaSelecionada, painelSala);

            // Exibe a janela
            janelaTemporaria.setVisible(true);
        });

        // botao adicionar bilhete
        adicionarBilheteButton.addActionListener(e -> {
            String tipoBilheteSelecionado = (String) opTipo.getSelectedItem();
            String sessaoSelecionada = (String) opSessao.getSelectedItem();
            if (tipoBilheteSelecionado == null || sessaoSelecionada == null) {
                JOptionPane.showMessageDialog(this, "Por favor, selecione um tipo de bilhete e uma sessão.");
                return;
            }

            //TODO: selecionar lugar e criar bilhete
        });

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
                if(quantidade > stockProdutos.get(selectedRow).getQuantidade()) {
                    JOptionPane.showMessageDialog(this, "Quantidade insuficiente em stock.");
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

            //criar fatura
            double valorTotal = 0;
            for (linhaFatura linha : linhasFaturaProduto) {
                valorTotal += linha.getPrecoTotal();
            }
            double valorTotalRounded = Math.round(valorTotal * 100.0) / 100.0;

            Fatura fatura = new Fatura(linhasFaturaProduto, valorTotalRounded);

            //guardar fatura
            DadosApp.getInstance().adicionarFatura(fatura);

            //remover stock
            for (linhaFatura linha : linhasFaturaProduto) {
                StockProduto stockProduto = DadosApp.getInstance().getStockProdutos().stream()
                        .filter(sp -> sp.getProduto().equals(linha.getProduto()))
                        .findFirst()
                        .orElse(null);
                if (stockProduto != null) {
                    stockProduto.remover(linha.getQuantidade());
                }
            }

            JOptionPane.showMessageDialog(this, "Venda finalizada com sucesso!");
            linhasFaturaProduto.clear();
            listaItems.setListData(new String[0]);
            atualizarValorTotal();
            dispose(); // Fecha a janela após finalizar a venda
            parentFrame.setVisible(true); // Torna a janela pai visível novamente
        });

        // Botao de cancelar operacao
        cancelarOperaçãoButton.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(this, "Tem a certeza que deseja cancelar a operação?", "Cancelar Operação", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                linhasFaturaProduto.clear();
                listaItems.setListData(new String[0]);
                atualizarValorTotal();
                dispose(); // Fecha a janela
                parentFrame.setVisible(true); // Torna a janela pai visível novamente
            }
        });


    }

    private void desenharSalaNaJanela(Sala sala, JPanel painelDestino) {
        painelDestino.removeAll();

        int nrLinhas = sala.getNumeroFilas();
        int nrColunas = sala.getNumeroLugaresPorFila();
        Lugar[][] lugares = sala.getLugares();

        painelDestino.setLayout(new GridLayout(nrLinhas, nrColunas));

        for (int linha = 0; linha < nrLinhas; ++linha) {
            for (int coluna = 0; coluna < nrColunas; ++coluna) {
                Lugar lugar = lugares[linha][coluna];
                JButton botao = new JButton();

                String nomeLugar = lugar.getDesignacao();
                botao.setText(nomeLugar);

                if (lugar.isAcessivel()) {
                    botao.setBackground(Color.GREEN);
                    botao.setText(nomeLugar + " (Acessível)");
                }

                // Listener para capturar o clique no botão
                botao.addActionListener(e -> {
                    lugarSelecionado = nomeLugar; // ou lugar.getDesignacao()
                    System.out.println("Lugar selecionado: " + lugarSelecionado);

                    // Aqui você pode fazer outra ação, ex: fechar janela ou marcar visualmente
                    botao.setBackground(Color.YELLOW);
                });

                painelDestino.add(botao);
            }
        }

        painelDestino.revalidate();
        painelDestino.repaint();
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

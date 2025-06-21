//Tomás Santos nº2230717
package view;

import javax.swing.*;
import javax.swing.plaf.synth.SynthOptionPaneUI;
import javax.swing.table.DefaultTableModel;

import model.*;

import java.awt.*;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private JComboBox bundleBox;
    private JButton cancelarOperaçãoButton;
    private JButton removerLinhaButton;
    private JLabel valorTotal;
    private JLabel textBoxLugar;

    private final JFrame parentFrame;
    private Lugar lugarSelecionado;
    private Funcionario funcionario;

    //para gurdar as linhas de fatura
    private List<linhaFatura> linhasFaturaProduto = new ArrayList<>();
    Map<Sessao, Integer> quantidadePorSessao = new HashMap<>();

    public JanelaVenda(JFrame parent, Funcionario funcionario) {
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

        //Opcoes de Bundle
        bundleBox.addItem("Nenhum"); // Adiciona opção de nenhum bundle
        DadosApp.getInstance().getBundles().forEach(bundle -> bundleBox.addItem(bundle.getNome()));

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
            if (tipoBilheteSelecionado == null || sessaoSelecionada == null || lugarSelecionado == null) {
                JOptionPane.showMessageDialog(this, "Por favor, selecione um tipo de bilhete, uma sessão e um lugar.");
                return;
            }

            Sessao sessao = DadosApp.getInstance().getSessaoPorTitulo(sessaoSelecionada);

            //criar Bilhete
            Bilhete bilhete = new Bilhete(sessao, lugarSelecionado, tipoBilheteSelecionado);

            //Criar linha de fatura
            linhaFatura novaLinha = new linhaFatura(bilhete);

            // Adicionar linhaFatura à lista
            linhasFaturaProduto.add(novaLinha);

            //adicionar linhaFatura à venda
            listaItems.setListData(linhasFaturaProduto.stream().map(linhaFatura::toString).toArray());

            //atualizar o valor total
            atualizarValorTotal();

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

            Map<String, Integer> quantidadesPorProduto = new HashMap<>();
            Map<String, Integer> quantidadesPorBilhete = new HashMap<>();

            // Verificar se um bundle foi selecionado
            String bundleSelecionado = (String) bundleBox.getSelectedItem();
            if (!bundleSelecionado.equals("Nenhum")) {
                Bundle bundle = DadosApp.getInstance().getBundlePorNome(bundleSelecionado);

                if (bundle != null) {

                    // Obter quantidades por produto
                    for (linhaFatura linha : linhasFaturaProduto) {
                        if(linha.getProduto()!=null){
                            String nome = linha.getProduto().getNome();
                            int quantidade = linha.getQuantidade();

                            // Acumula quantidade
                            quantidadesPorProduto.merge(nome, quantidade, Integer::sum);
                        }else{
                            //caso de bilhete
                            String tipoBilhete = linha.getBilhete().getTipo();
                            int quantidade = linha.getQuantidade();
                            // Acumula quantidade
                            quantidadesPorBilhete.merge(tipoBilhete, quantidade, Integer::sum);
                            quantidadePorSessao.merge(linha.getBilhete().getSessao(), quantidade, Integer::sum);
                        }

                    }


                    // Verificar se as quantidades do bundle estão corretas
                    for (Produto produto : bundle.getProdutos()) {
                        int quantidadeNoBundle = bundle.getQuantidadeProdutos(produto.getNome());
                        if (quantidadesPorProduto.getOrDefault(produto.getNome(), 0) < quantidadeNoBundle) {
                            JOptionPane.showMessageDialog(this, "Quantidade insuficiente do produto: " + produto.getNome() + " no bundle.");
                            return;
                        }
                    }
                    // Verificar se as quantidades de bilhetes estão corretas
                    for (String tipoBilhete : bundle.getBilhetes()) {
                        int quantidadeNoBundle = bundle.getQuantidadeBilhetes(tipoBilhete);
                        if (quantidadesPorBilhete.getOrDefault(tipoBilhete, 0) < quantidadeNoBundle) {
                            JOptionPane.showMessageDialog(this, "Quantidade insuficiente de bilhetes do tipo: " + tipoBilhete + " no bundle.");
                            return;
                        }
                    }

                    //criar fatura
                    double valorTotalBundle = bundle.getPreco();
                    double valorExtras = 0.0;

                    for (Map.Entry<String, Integer> entry : quantidadesPorProduto.entrySet()) {
                        String nomeProduto = entry.getKey();
                        int quantidadeNaVenda = entry.getValue();

                        int quantidadeNoBundle = bundle.getQuantidadeProdutos(nomeProduto);
                        int quantidadeExtra = quantidadeNaVenda - quantidadeNoBundle;

                        if (quantidadeExtra > 0) {
                            Produto produto = DadosApp.getInstance().getProdutoPorNome(nomeProduto);
                            if (produto != null) {
                                valorExtras += quantidadeExtra * produto.getPreco();
                            }
                        }
                    }

                    for (Map.Entry<String, Integer> entry : quantidadesPorBilhete.entrySet()) {
                        String tipo = entry.getKey();
                        int quantidadeNaVenda = entry.getValue();

                        int quantidadeNoBundle = bundle.getQuantidadeBilhetes(tipo);
                        int quantidadeExtra = quantidadeNaVenda - quantidadeNoBundle;

                        if (quantidadeExtra > 0) {
                            double preco = DadosApp.getInstance().getPrecoBilhete(tipo);
                            valorExtras += quantidadeExtra * preco;
                        }
                    }

                    // Valor total da fatura
                    double valorFinal = valorTotalBundle + valorExtras;


                    fechar(valorFinal);
                    System.out.println("Venda finalizada com sucesso! Valor total: " + valorFinal + " €");
                }
            }

            //criar fatura
            double valorTotal = 0;
            for (linhaFatura linha : linhasFaturaProduto) {
                valorTotal += linha.getPrecoTotal();
            }

            double valorTotalRounded = Math.round(valorTotal * 100.0) / 100.0;

            fechar(valorTotalRounded);
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

                // Armazena o objeto Lugar no botão
                botao.putClientProperty("lugar", lugar);

                // Listener para clique
                botao.addActionListener(e -> {
                    JButton sourceButton = (JButton) e.getSource();
                    lugarSelecionado = (Lugar) sourceButton.getClientProperty("lugar");

                    textBoxLugar.setText(lugarSelecionado.getDesignacao());
                    sourceButton.setBackground(Color.YELLOW); // marca visualmente
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

    private void fechar(double valorFinal){

        // Verifica se o funcionário é nulo e atribui um valor padrão
        if(funcionario == null) {
            funcionario = new Funcionario("Anónimo", "Anónimo", "Anónimo",
                    "Anónimo", "Anónimo", "Anónimo", "Anónimo", false);
        }

        // Diminui a quantidade de lugares disponiveis
        for (Map.Entry<Sessao, Integer> entry : quantidadePorSessao .entrySet()) {
            Sessao sessao = entry.getKey();
            int quantidadeDebilhetes = entry.getValue();

            for (int i = 0; i < quantidadeDebilhetes ; i++) {
                sessao.diminuiNumeroLugaresDisponivel();
            }
        }

        // Adicionar Venda ao DadosApp para estatistica
        for (linhaFatura linha : linhasFaturaProduto) {
            if (linha.getBilhete() != null) {
                VendaBilhete venda = new VendaBilhete(
                        linha.getBilhete().getSessao().getFilme(),
                        linha.getBilhete().getTipo(),
                        LocalDate.now(),
                        linha.getQuantidade()
                );
                DadosApp.getInstance().getVendasBilhete().add(venda);
                DadosApp.gravarDados();
            }
        }

        //criar fatura
        Fatura fatura = new Fatura(linhasFaturaProduto, valorFinal,funcionario);

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
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JTable getTabelaProdutos() {
        return tabelaProdutos;
    }

    public JTextField getQuantidadeField() {
        return txtBoxQuantidade;
    }

    public JButton getAdicionarButton() {
        return adicionarProdutoButton;
    }

    public List<linhaFatura> getLinhasFatura() {
        return linhasFaturaProduto;
    }

    public JButton getFinalizarVendaButton() {
        return finalizarCompraButton;
    }
}

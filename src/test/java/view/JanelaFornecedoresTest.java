//Lourenço Ferreira 2230972
package view;

import model.DadosApp;
import model.Fornecedor;
import model.Produto;
import org.junit.jupiter.api.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import static org.junit.jupiter.api.Assertions.*;

class JanelaFornecedoresTest {

    private JanelaFornecedores janela;
    private Fornecedor fornecedorTeste;

    @BeforeEach
    void setUp() {
        DadosApp.resetInstance();

        fornecedorTeste = new Fornecedor("Fornecedor Teste");
        Produto p1 = new Produto("Produto A", 2.5);
        Produto p2 = new Produto("Produto B", 4.0);
        fornecedorTeste.getTabelaPrecos().put(p1, 2.5);
        fornecedorTeste.getTabelaPrecos().put(p2, 4.0);

        DadosApp.getInstance().getFornecedores().add(fornecedorTeste);

        janela = new JanelaFornecedores(null, fornecedorTeste);
    }

    @Test
    void testAdicionarAoCarrinho_semProdutoSelecionado_mostraAviso() {
        janela.getTable1().clearSelection();
        janela.getQuantidadeProduto().setText("1");

        int rowsAntes = ((DefaultTableModel) janela.getTable2().getModel()).getRowCount();

        janela.getAdicionarAoCarrinhoButton().doClick();

        int rowsDepois = ((DefaultTableModel) janela.getTable2().getModel()).getRowCount();

        assertEquals(rowsAntes, rowsDepois, "Não deve adicionar produto ao carrinho sem seleção");
    }

    @Test
    void testAdicionarAoCarrinho_quantidadeInvalida_mostraErro() {
        janela.getTable1().setRowSelectionInterval(0, 0);
        janela.getQuantidadeProduto().setText("-5");

        int rowsAntes = ((DefaultTableModel) janela.getTable2().getModel()).getRowCount();

        janela.getAdicionarAoCarrinhoButton().doClick();

        int rowsDepois = ((DefaultTableModel) janela.getTable2().getModel()).getRowCount();

        assertEquals(rowsAntes, rowsDepois, "Não deve adicionar produto ao carrinho com quantidade inválida");
    }

    @Test
    void testAdicionarAoCarrinho_valido() {
        janela.getTable1().setRowSelectionInterval(0, 0);
        janela.getQuantidadeProduto().setText("3");

        int rowsAntes = ((DefaultTableModel) janela.getTable2().getModel()).getRowCount();

        janela.getAdicionarAoCarrinhoButton().doClick();

        int rowsDepois = ((DefaultTableModel) janela.getTable2().getModel()).getRowCount();

        assertEquals(rowsAntes + 1, rowsDepois, "Deve adicionar produto ao carrinho");
    }

    @Test
    void testFinalizarCompra_carrinhoVazio_mostraAviso() {
        ((DefaultTableModel) janela.getTable2().getModel()).setRowCount(0);

        janela.getFinalizarComprarEGerarButton().doClick();

        assertEquals(0, ((DefaultTableModel) janela.getTable2().getModel()).getRowCount(), "Carrinho deve continuar vazio");
    }

    @Test
    void testFinalizarCompra_comProdutos() {
        janela.getTable1().setRowSelectionInterval(0, 0);
        janela.getQuantidadeProduto().setText("2");
        janela.getAdicionarAoCarrinhoButton().doClick();

        int rowsAntes = ((DefaultTableModel) janela.getTable2().getModel()).getRowCount();
        assertTrue(rowsAntes > 0, "Deve haver produtos no carrinho");

        janela.getFinalizarComprarEGerarButton().doClick();

        int rowsDepois = ((DefaultTableModel) janela.getTable2().getModel()).getRowCount();
        assertEquals(0, rowsDepois, "Carrinho deve ser limpo após finalizar compra");
    }

    @AfterEach
    void tearDown() {
        DadosApp.resetInstance();
    }

}

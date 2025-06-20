package view;

import model.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class JanelaStockBarTest {

    private JanelaStockBar janela;

    @BeforeEach
    void setUp() {
        DadosApp.resetInstance();
        JFrame dummyParent = new JFrame();
        janela = new JanelaStockBar(dummyParent);
    }

    @Test
    void testAdicionarProdutoNovo() {
        DadosApp.resetInstance();

        int tamanhoAntes = DadosApp.getInstance().getStockProdutos().size();

        JTextField input = getPrivateTextField();
        input.setText("Água");

        JButton botao = getPrivateAdicionarButton();
        botao.doClick();

        int tamanhoDepois = DadosApp.getInstance().getStockProdutos().size();

        assertEquals(tamanhoAntes + 1, tamanhoDepois, "Stock deve aumentar ao adicionar produto novo");
    }



    @Test
    void testNaoAdicionaProdutoVazio() {
        getPrivateTextField().setText("");

        int tamanhoAntes = DadosApp.getInstance().getStockProdutos().size();

        getPrivateAdicionarButton().doClick();

        int tamanhoDepois = DadosApp.getInstance().getStockProdutos().size();

        assertEquals(tamanhoAntes, tamanhoDepois, "Stock não deve aumentar ao adicionar produto vazio");
    }


    @Test
    void testNaoAdicionaProdutoRepetido() {
        DadosApp.getInstance().getStockProdutos().add(new StockProduto(new Produto("Ice Tea Limão 33cl",5.0), 5));

        getPrivateTextField().setText("Ice Tea Limão 33cl");
        getPrivateAdicionarButton().doClick();

        List<StockProduto> stock = DadosApp.getInstance().getStockProdutos();
        assertEquals(stock.size(), stock.size(), "Produto repetido não deve ser adicionado");
    }

    private JTextField getPrivateTextField() {
        return (JTextField) TestUtils.getFieldValue(janela, "textField1");
    }

    private JButton getPrivateAdicionarButton() {
        return (JButton) TestUtils.getFieldValue(janela, "adicionarButton");
    }
}

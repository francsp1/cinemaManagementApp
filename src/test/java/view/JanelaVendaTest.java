//Tomás Santos nº2230717

package view;

import model.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class JanelaVendaTest {
    private JanelaVenda janela;

    @BeforeEach
    void setUp() {
        DadosApp.resetInstance();
        JFrame dummyParent = new JFrame();
        //Criar Funcionario para test
        Funcionario funcionarioTeste = new Funcionario("John Doe","","","","",
                "","",false);
        //Criar Janela Para teste
        janela = new JanelaVenda(dummyParent, funcionarioTeste);

        // Adiciona alguns produtos ao stock para testes
        DadosApp.getInstance().getStockProdutos().add(new StockProduto(new Produto("Produto A", 10.0), 5));
        DadosApp.getInstance().getStockProdutos().add(new StockProduto(new Produto("Produto B", 20.0), 3));
    }
    @Test
    void testCriarNovaLinhaFatura() {

        DadosApp.resetInstance();

        int quantidadeAntes = janela.getLinhasFatura().size();

        // Seleciona um produto da tabela
        janela.getTabelaProdutos().setRowSelectionInterval(0, 0);

        // Define a quantidade
        janela.getQuantidadeField().setText("2");

        // Clica no botão de adicionar
        janela.getAdicionarButton().doClick();

        int quantidadeDepois = janela.getLinhasFatura().size();

        assertEquals(quantidadeAntes + 1, quantidadeDepois, "O produto adicionado deve ser 'Produto A'");
    }

    @Test
    void testRemoverStock(){
        // Verifica se o stock do produto foi atualizado após a adição
        DadosApp.resetInstance();
        int quantidadeAntes = DadosApp.getInstance().getStockProdutos().get(0).getQuantidade();

        // Seleciona um produto da tabela
        janela.getTabelaProdutos().setRowSelectionInterval(0, 0);

        // Define a quantidade
        janela.getQuantidadeField().setText("5");

        // Clica no botão de adicionar
        janela.getAdicionarButton().doClick();

        //finaliza a venda
        janela.getFinalizarVendaButton().doClick();

        int quantidadeDepois = DadosApp.getInstance().getStockProdutos().get(0).getQuantidade();

        assertEquals(quantidadeAntes - 5, quantidadeDepois, "O stock do produto deve ser atualizado corretamente após a venda");



    }
}

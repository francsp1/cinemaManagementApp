package view;

import model.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class JanelaAdicionarBundleTest {
    private JanelaAdicionarBundle janela;
    private Bundle bundleTeste;

    @BeforeEach
    void setUp() {
        DadosApp.resetInstance();
        JFrame dummyParent = new JFrame();
        janela = new JanelaAdicionarBundle(dummyParent);
        bundleTeste = new Bundle("Teste Bundle", 50.0, new ArrayList<>(), new ArrayList<>());
    }

    @Test
    void testAdicionarBundleComCamposVazios() {

        janela.getNomeField().setText("");
        janela.getPrecoField().setText("");

        janela.getAdicionarButton().doClick();

        assertTrue(janela.isVisible(), "Janela deve permanecer visível se campos estiverem vazios");
    }

    @Test
    void testAdicionarBundleComCamposValidos() {

        janela.getNomeField().setText("Novo Bundle");
        janela.getPrecoField().setText("100.0");
        janela.getBilhetes().add("Estudante");
        janela.getProdutos().add(new Produto("Produto A", 10.0));

        janela.getAdicionarButton().doClick();

        assertFalse(janela.isVisible(), "Janela deve fechar após adicionar bundle com sucesso");
    }

    @Test
    void testGuardarBundle() {

        int tamanhoAntes = DadosApp.getInstance().getBundles().size();

        janela.getNomeField().setText("Novo Bundle");
        janela.getPrecoField().setText("100.0");
        janela.getBilhetes().add("Estudante");
        janela.getProdutos().add(new Produto("Produto A", 10.0));

        janela.getAdicionarButton().doClick();

        int tamanhoDepois = DadosApp.getInstance().getBundles().size();

        assertEquals(tamanhoAntes + 1, tamanhoDepois, "Bundle deve aumentar ao adicionar bundle novo");
    }

}

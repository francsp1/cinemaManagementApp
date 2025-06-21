//Rodrigo Correia nº2231856

package view;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;



public class SessaoTest {
    private JanelaPesquisaSessoes janela;

    private Sala sala;
    private Filme filme;
    private Sessao sessao;

    @BeforeEach
    public void setUp() {
        sala = new Sala(5, 5, 2, TipoSala.TRES_D, TipoSistemaSom.DOLBY_ATMOS, "Sala 3D");
        filme = new Filme("Inception", "Um ladrão invade sonhos para roubar segredos.", 148,
                Filme.Categoria.THRILLER, "Christopher Nolan", "Leonardo DiCaprio", "Joseph Gordon-Levitt", "Elliot Page", "Tom Hardy",
                Filme.Tipo._2D);
        sessao = new Sessao(sala, 160, LocalDateTime.of(2025, 6, 21, 15, 0), filme);
        sessao.setNumeroLugaresDisponivel(0);
        DadosApp.getInstance().adicionarFilme(filme);
    }

    @Test
    public void testGetFilme() {
        assertEquals(filme, sessao.getFilme(), "O filme deve ser igual ao definido na criação");
    }

    @Test
    public void testGetSala() {
        assertEquals(sala, sessao.getSala(), "A sala deve ser igual à definida");
    }

    @Test
    public void testGetDataHora() {
        assertEquals(LocalDateTime.of(2025, 6, 21, 15, 0), sessao.getDataHora(), "A data/hora deve ser correta");
    }

    @Test
    public void testGetDuracao() {
        assertEquals(160, sessao.getDuracao(), "A duração da sessão deve ser igual à definida no construtor");
    }

    @Test
    public void testToStringContemInfoEssencial() {
        String texto = sessao.toString();
        assertTrue(texto.contains("Inception"));
        assertTrue(texto.contains("Sala 3D")); // nome correto da sala
        assertTrue(texto.contains("2025"), "O texto deve conter o ano");
    }

    @Test
    public void testFilmeComboBoxContemFilmeInception() {


        JanelaAdicionarSessao janela = new JanelaAdicionarSessao(null, true);
        JComboBox combo = janela.getFilmeComboBox();
        boolean contem = false;
        for (int i = 0; i < combo.getItemCount(); i++) {
            Filme item = (Filme) combo.getItemAt(i);
            if (item.getTitulo().equals("Inception")) {
                contem = true;
                break;
            }
        }

        assertTrue(contem, "O comboBox deve conter o filme 'Inception'");
    }
}



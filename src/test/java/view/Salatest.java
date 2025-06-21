package view;

import model.DadosApp;
import model.Sala;
import model.TipoSala;
import model.TipoSistemaSom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.salas.JanelaAdicionarSala;

public class Salatest {

    @BeforeEach
    void setUp() {
        DadosApp.resetInstance();
    }

    @Test
    void adicionarSalaComNumeroSalaExistente() {
        int numeroSala = 999;

        Sala sala1 = new Sala(5, 5, numeroSala, TipoSala.TRES_D, TipoSistemaSom.DOLBY_ATMOS, "Sala Teste 1");
        DadosApp.getInstance().adicionarSala(sala1);

        Sala sala2 = new Sala(5, 5, numeroSala, TipoSala.TRES_D, TipoSistemaSom.DOLBY_ATMOS, "Sala Teste 2");

        boolean result = DadosApp.getInstance().adicionarSala(sala2);

        // Verifica se a adição da segunda sala com o mesmo número falhou
        assert !result : "Deveria falhar ao adicionar uma sala com o mesmo número " + numeroSala;

        // Verificae se existem 2 salas com o mesmo número em DadosApp
        assert DadosApp.getInstance().getSalas().stream()
                .filter(s -> s.getNumeroSala() == numeroSala)
                .count() == 1 : "Deveria haver apenas uma sala com o número " + numeroSala;

    }
}

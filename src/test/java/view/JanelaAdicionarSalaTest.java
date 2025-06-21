//Francisco Pedrosa - 2181248
package view;

import model.DadosApp;
import model.Sala;
import model.TipoSala;
import model.TipoSistemaSom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.salas.JanelaAdicionarSala;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import view.salas.JanelaAdicionarSala;

public class JanelaAdicionarSalaTest {

    private JanelaAdicionarSala janelaAdicionarSala;
    private static final int numeroSalaFicticio = 999;
    private static final String nomeSalaFicticio = "Sala de Teste";

    @BeforeEach
    void setUp() {
        DadosApp.resetInstance();
        janelaAdicionarSala = new JanelaAdicionarSala(null);
    }

    @Test
    void testAdicionarSalaComNumeroInvalido() {

        int beforeInsertSize = DadosApp.getInstance().getSalas().size();

        JSpinner sprNumeroSala = janelaAdicionarSala.getSprNumeroSala();
        sprNumeroSala.setValue(null);

        JButton btnAdicionarSala = janelaAdicionarSala.getBtnAdicionarSala();
        btnAdicionarSala.doClick();

        int afterInsertSize = DadosApp.getInstance().getSalas().size();

        assertEquals(beforeInsertSize + 1, afterInsertSize,
                "O número total de salas deve aumentar em 1 ao adicionar uma sala com número válido");

    }

    @Test
    void testAdicionarSalaComNumeroNegativo() {

        int beforeInsertSize = DadosApp.getInstance().getSalas().size();

        JSpinner sprNumeroSala = janelaAdicionarSala.getSprNumeroSala();
        sprNumeroSala.setValue(-1);

        JButton btnAdicionarSala = janelaAdicionarSala.getBtnAdicionarSala();
        btnAdicionarSala.doClick();

        int afterInsertSize = DadosApp.getInstance().getSalas().size();

        assertFalse(DadosApp.getInstance().getSalas().stream()
                .anyMatch(sala -> sala.getNumeroSala() == -1),
                "Não deve adicionar sala com número negativo");

        assertEquals(beforeInsertSize, afterInsertSize,
                "O número total de salas não deve mudar ao tentar adicionar uma sala com número negativo");
    }

    @Test
    void testAdicionarSalaComNumeroZero() {

        int beforeInsertSize = DadosApp.getInstance().getSalas().size();

        JSpinner sprNumeroSala = janelaAdicionarSala.getSprNumeroSala();
        sprNumeroSala.setValue(0);

        JButton btnAdicionarSala = janelaAdicionarSala.getBtnAdicionarSala();
        btnAdicionarSala.doClick();

        int afterInsertSize = DadosApp.getInstance().getSalas().size();

        assertFalse(DadosApp.getInstance().getSalas().stream()
                .anyMatch(sala -> sala.getNumeroSala() == 0),
                "Não deve adicionar sala com número zero");

        assertEquals(beforeInsertSize, afterInsertSize,
                "O número total de salas não deve mudar ao tentar adicionar uma sala com número zero");
    }

    @Test
    void testAdicionarSalaComUmNumeroExistente() {

        Sala salaExemplo = new Sala(5, 5, numeroSalaFicticio, TipoSala.TRES_D, TipoSistemaSom.DOLBY_ATMOS, "Sala Infantil");

        DadosApp.getInstance().adicionarSala(salaExemplo);

        int beforeInsertSize = DadosApp.getInstance().getSalas().size();

        JSpinner sprNumeroSala = janelaAdicionarSala.getSprNumeroSala();
        sprNumeroSala.setValue(5);

        JButton btnAdicionarSala = janelaAdicionarSala.getBtnAdicionarSala();
        btnAdicionarSala.doClick();

        // Verify if there is 2 rooms with the same number
        boolean exists = DadosApp.getInstance().getSalas().stream()
                .filter(sala -> sala.getNumeroSala() == numeroSalaFicticio)
                .count() > 1;

        //assertFalse(exists, "Não deve adicionar sala com número já existente");

        int afterInsertSize = DadosApp.getInstance().getSalas().size();

        assertEquals(beforeInsertSize, afterInsertSize,
                "O número total de salas não deve mudar ao tentar adicionar uma sala com número existente");
    }

    @Test
    void testAdicionarSalaComNomeVazio() {

        int beforeInsertSize = DadosApp.getInstance().getSalas().size();

        JSpinner sprNumeroSala = janelaAdicionarSala.getSprNumeroSala();
        sprNumeroSala.setValue(numeroSalaFicticio);

        JTextField txtNomeSala = janelaAdicionarSala.getTxtNomeSala();
        txtNomeSala.setText("");

        JButton btnAdicionarSala = janelaAdicionarSala.getBtnAdicionarSala();
        btnAdicionarSala.doClick();

        int afterInsertSize = DadosApp.getInstance().getSalas().size();

        assertEquals(beforeInsertSize, afterInsertSize,
                "O número total de salas não deve mudar ao tentar adicionar uma sala com nome vazio");
    }

    @Test
    void testAdicionarSalaComNumeroFilasInvalido() {

        int beforeInsertSize = DadosApp.getInstance().getSalas().size();

        JSpinner sprNumeroSala = janelaAdicionarSala.getSprNumeroSala();
        sprNumeroSala.setValue(numeroSalaFicticio);

        JTextField txtNomeSala = janelaAdicionarSala.getTxtNomeSala();
        txtNomeSala.setText(nomeSalaFicticio);

        JSpinner sprNumeroFilas = janelaAdicionarSala.getSprNumeroFilas();
        sprNumeroFilas.setValue(null);

        JButton btnAdicionarSala = janelaAdicionarSala.getBtnAdicionarSala();
        btnAdicionarSala.doClick();

        int afterInsertSize = DadosApp.getInstance().getSalas().size();

        assertEquals(beforeInsertSize, afterInsertSize,
                "O número total de salas não deve mudar ao tentar adicionar uma sala com número de filas inválido");
    }

    @Test
    void testAdicionarSalaComNumeroFilasNegativo() {

        int beforeInsertSize = DadosApp.getInstance().getSalas().size();

        JSpinner sprNumeroSala = janelaAdicionarSala.getSprNumeroSala();
        sprNumeroSala.setValue(numeroSalaFicticio);

        JTextField txtNomeSala = janelaAdicionarSala.getTxtNomeSala();
        txtNomeSala.setText(nomeSalaFicticio);

        JSpinner sprNumeroFilas = janelaAdicionarSala.getSprNumeroFilas();
        sprNumeroFilas.setValue(-1);

        JButton btnAdicionarSala = janelaAdicionarSala.getBtnAdicionarSala();
        btnAdicionarSala.doClick();

        int afterInsertSize = DadosApp.getInstance().getSalas().size();

        assertFalse(DadosApp.getInstance().getSalas().stream()
                        .anyMatch(sala -> sala.getNumeroFilas() == -1),
                "Não deve adicionar sala com número de filas negativo");

        assertEquals(beforeInsertSize, afterInsertSize,
                "O número total de salas não deve mudar ao tentar adicionar uma sala com número de filas negativo");
    }

    @Test
    void testAdicionarSalaComNumeroFilasIgualAZero() {

        int beforeInsertSize = DadosApp.getInstance().getSalas().size();

        JSpinner sprNumeroSala = janelaAdicionarSala.getSprNumeroSala();
        sprNumeroSala.setValue(numeroSalaFicticio);

        JTextField txtNomeSala = janelaAdicionarSala.getTxtNomeSala();
        txtNomeSala.setText(nomeSalaFicticio);

        JSpinner sprNumeroFilas = janelaAdicionarSala.getSprNumeroFilas();
        sprNumeroFilas.setValue(0);

        JButton btnAdicionarSala = janelaAdicionarSala.getBtnAdicionarSala();
        btnAdicionarSala.doClick();

        int afterInsertSize = DadosApp.getInstance().getSalas().size();

        assertFalse(DadosApp.getInstance().getSalas().stream()
                        .anyMatch(sala -> sala.getNumeroFilas() == 0),
                "Não deve adicionar sala com número de filas igual a zero");

        assertEquals(beforeInsertSize, afterInsertSize,
                "O número total de salas não deve mudar ao tentar adicionar uma sala com número de filas igual a zero");
    }

    @Test
    void testAdicionarSalaComNumeroFilasMaiorQueOMaximoPermitido() {

        int beforeInsertSize = DadosApp.getInstance().getSalas().size();

        JSpinner sprNumeroSala = janelaAdicionarSala.getSprNumeroSala();
        sprNumeroSala.setValue(numeroSalaFicticio);

        JTextField txtNomeSala = janelaAdicionarSala.getTxtNomeSala();
        txtNomeSala.setText(nomeSalaFicticio);

        JSpinner sprNumeroFilas = janelaAdicionarSala.getSprNumeroFilas();
        sprNumeroFilas.setValue(DadosApp.MAX_FILAS + 1);

        JButton btnAdicionarSala = janelaAdicionarSala.getBtnAdicionarSala();
        btnAdicionarSala.doClick();

        int afterInsertSize = DadosApp.getInstance().getSalas().size();

        assertFalse(DadosApp.getInstance().getSalas().stream()
                        .anyMatch(sala -> sala.getNumeroFilas() == DadosApp.MAX_FILAS + 1),
                "Não deve adicionar sala com número de filas maior que o máximo permitido");

        assertEquals(beforeInsertSize, afterInsertSize,
                "O número total de salas não deve mudar ao tentar adicionar uma sala com número de filas maior que o máximo permitido");
    }

    @Test
    void testAdicionarSalaComNumeroLugaresPorFilaInvalido() {

        int beforeInsertSize = DadosApp.getInstance().getSalas().size();

        JSpinner sprNumeroSala = janelaAdicionarSala.getSprNumeroSala();
        sprNumeroSala.setValue(numeroSalaFicticio);

        JTextField txtNomeSala = janelaAdicionarSala.getTxtNomeSala();
        txtNomeSala.setText(nomeSalaFicticio);

        JSpinner sprNumeroFilas = janelaAdicionarSala.getSprNumeroFilas();
        sprNumeroFilas.setValue(5);

        JSpinner sprNumeroLugaresFila = janelaAdicionarSala.getSprNumeroLugaresFila();
        sprNumeroLugaresFila.setValue(null);

        JButton btnAdicionarSala = janelaAdicionarSala.getBtnAdicionarSala();
        btnAdicionarSala.doClick();

        int afterInsertSize = DadosApp.getInstance().getSalas().size();

        assertEquals(beforeInsertSize, afterInsertSize,
                "O número total de salas não deve mudar ao tentar adicionar uma sala com número de lugares por fila inválido");
    }

    @Test
    void testAdicionarSalaComNumeroLugaresPorFilaNegativo() {

        int beforeInsertSize = DadosApp.getInstance().getSalas().size();

        JSpinner sprNumeroSala = janelaAdicionarSala.getSprNumeroSala();
        sprNumeroSala.setValue(numeroSalaFicticio);

        JTextField txtNomeSala = janelaAdicionarSala.getTxtNomeSala();
        txtNomeSala.setText(nomeSalaFicticio);

        JSpinner sprNumeroFilas = janelaAdicionarSala.getSprNumeroFilas();
        sprNumeroFilas.setValue(5);

        JSpinner sprNumeroLugaresFila = janelaAdicionarSala.getSprNumeroLugaresFila();
        sprNumeroLugaresFila.setValue(-1);

        JButton btnAdicionarSala = janelaAdicionarSala.getBtnAdicionarSala();
        btnAdicionarSala.doClick();

        int afterInsertSize = DadosApp.getInstance().getSalas().size();

        assertFalse(DadosApp.getInstance().getSalas().stream()
                        .anyMatch(sala -> sala.getNumeroLugaresPorFila() == -1),
                "Não deve adicionar sala com número de lugares por fila negativo");

        assertEquals(beforeInsertSize, afterInsertSize,
                "O número total de salas não deve mudar ao tentar adicionar uma sala com número de lugares por fila negativo");
    }

    @Test
    void testAdicionarSalaComNumeroLugaresPorFilaIgualAZero() {

        int beforeInsertSize = DadosApp.getInstance().getSalas().size();

        JSpinner sprNumeroSala = janelaAdicionarSala.getSprNumeroSala();
        sprNumeroSala.setValue(numeroSalaFicticio);

        JTextField txtNomeSala = janelaAdicionarSala.getTxtNomeSala();
        txtNomeSala.setText(nomeSalaFicticio);

        JSpinner sprNumeroFilas = janelaAdicionarSala.getSprNumeroFilas();
        sprNumeroFilas.setValue(5);

        JSpinner sprNumeroLugaresFila = janelaAdicionarSala.getSprNumeroLugaresFila();
        sprNumeroLugaresFila.setValue(0);

        JButton btnAdicionarSala = janelaAdicionarSala.getBtnAdicionarSala();
        btnAdicionarSala.doClick();

        int afterInsertSize = DadosApp.getInstance().getSalas().size();

        assertFalse(DadosApp.getInstance().getSalas().stream()
                        .anyMatch(sala -> sala.getNumeroLugaresPorFila() == 0),
                "Não deve adicionar sala com número de lugares por fila igual a zero");

        assertEquals(beforeInsertSize, afterInsertSize,
                "O número total de salas não deve mudar ao tentar adicionar uma sala com número de lugares por fila igual a zero");
    }

    @Test
    void testAdicionarSalaComNumeroLugaresPorFilaMaiorQueOMaximoPermitido() {

        int beforeInsertSize = DadosApp.getInstance().getSalas().size();

        JSpinner sprNumeroSala = janelaAdicionarSala.getSprNumeroSala();
        sprNumeroSala.setValue(numeroSalaFicticio);

        JTextField txtNomeSala = janelaAdicionarSala.getTxtNomeSala();
        txtNomeSala.setText(nomeSalaFicticio);

        JSpinner sprNumeroFilas = janelaAdicionarSala.getSprNumeroFilas();
        sprNumeroFilas.setValue(5);

        JSpinner sprNumeroLugaresFila = janelaAdicionarSala.getSprNumeroLugaresFila();
        sprNumeroLugaresFila.setValue(DadosApp.MAX_LUGARES_POR_FILA + 1);

        JButton btnAdicionarSala = janelaAdicionarSala.getBtnAdicionarSala();
        btnAdicionarSala.doClick();

        int afterInsertSize = DadosApp.getInstance().getSalas().size();

        assertFalse(DadosApp.getInstance().getSalas().stream()
                        .anyMatch(sala -> sala.getNumeroLugaresPorFila() == DadosApp.MAX_LUGARES_POR_FILA + 1),
                "Não deve adicionar sala com número de lugares por fila maior que o máximo permitido");

        assertEquals(beforeInsertSize, afterInsertSize,
                "O número total de salas não deve mudar ao tentar adicionar uma sala com número de lugares por fila maior que o máximo permitido");
    }

}

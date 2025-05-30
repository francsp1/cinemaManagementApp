package model;

import java.util.ArrayList;

/**
 * Singleton class to hold application data.
 */
public enum DadosApp {
    INSTANCIA;

    public static final int MAX_FILAS = 10;
    public static final int MAX_LUGARES_POR_FILA = 10;
    public static final double PERCENTAGEM_LUGARES_ACESSIVEIS = 0.10;

    private final ArrayList<Sala> salas = new ArrayList<>();
    private final ArrayList<Funcionario> funcionarios = new ArrayList<>();

    DadosApp() {
        adicionarSalasExemplo();
        adicionarFuncionariosExemplo();
    }

    public void adicionarSala(Sala sala) {
        salas.add(sala);
    }

    private void adicionarSalasExemplo() {
        Sala sala1 = new Sala(3, 4, 1, TipoSala.DOIS_D, TipoSistemaSom.NORMAL, "Sala 2D");
        sala1.setEstado(false);
        Sala sala2 = new Sala(5, 5, 2, TipoSala.TRES_D, TipoSistemaSom.DOLBY_ATMOS, "Sala 3D");
        Sala sala3 = new Sala(5, 5, 3, TipoSala.IMAX, TipoSistemaSom.NORMAL, "Sala IMAX");
        Sala sala4 = new Sala(5, 5, 4, TipoSala.IMAX, TipoSistemaSom.DOLBY_ATMOS, "Sala VIP");
        Sala sala5 = new Sala(5, 5, 5, TipoSala.TRES_D, TipoSistemaSom.DOLBY_ATMOS, "Sala Infantil");

        salas.add(sala1);
        salas.add(sala2);
        salas.add(sala3);
        salas.add(sala4);
        salas.add(sala5);

    }

    public boolean existeNumeroSala(int numeroSala) {
        for (Sala sala : salas) {
            if (sala.getNumeroSala() == numeroSala) {
                return true;
            }
        }
        return false;
    }

    private void adicionarFuncionariosExemplo() {
        Funcionario f1 = new Funcionario(
                "João Silva", "12345678", "joao.silva@email.com",
                "Rua A, Lisboa", "912345678", "jsilva", "1234", true
        );

        Funcionario f2 = new Funcionario(
                "Maria Fernandes", "87654321", "maria.fernandes@email.com",
                "Av. B, Porto", "934567890", "mfernandes", "1234", false
        );

        Funcionario f3 = new Funcionario(
                "Carlos Sousa", "11223344", "carlos.sousa@email.com",
                "Rua C, Coimbra", "965432187", "csousa", "1234", false
        );

        Funcionario f4 = new Funcionario(
                "Ana Costa", "44332211", "ana.costa@email.com",
                "Av. D, Faro", "913245678", "acosta", "1234", true
        );

        Funcionario f5 = new Funcionario(
                "Pedro Gomes", "33445566", "pedro.gomes@email.com",
                "Rua E, Braga", "926789012", "pgomes", "1234", false
        );

        Funcionario f6 = new Funcionario(
                "gestor1", "3121432435", "gestor1@email.com",
                "Rua da Moita 27", "926789012", "gestor1", "1234", true
        );

        Funcionario f7 = new Funcionario(
                "funcionario1", "473264738", "funcionario1@email.com",
                "Rua da fonte 3", "926789222", "funcionario1", "1234", false
        );


        funcionarios.add(f1);
        funcionarios.add(f2);
        funcionarios.add(f3);
        funcionarios.add(f4);
        funcionarios.add(f5);
        funcionarios.add(f6);
        funcionarios.add(f7);
    }

    public ArrayList<Sala> getSalas() {
        return salas;
    }

    public ArrayList<Funcionario> getFuncionarios() {
        return funcionarios;
    }
}

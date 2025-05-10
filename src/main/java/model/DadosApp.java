package model;

import java.util.ArrayList;

/**
 * Singleton class to hold application data.
 */
public enum DadosApp {
    INSTANCIA;

    private final ArrayList<Sala> salas = new ArrayList<>();
    private final ArrayList<Funcionario> funcionarios = new ArrayList<>();

    DadosApp() {
        adicionarSalasExemplo();
        adicionarFuncionariosExemplo();
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

    private void adicionarFuncionariosExemplo() {
        Funcionario f1 = new Funcionario(
                "João Silva", "joao", "senha123", true,
                "12345678", "123456789", "987654321",
                "joao.silva@email.com", "Rua A, Lisboa", "912345678"
        );

        Funcionario f2 = new Funcionario(
                "Maria Fernandes", "maria", "pass456", false,
                "87654321", "987654321", "123456789",
                "maria.fernandes@email.com", "Av. B, Porto", "934567890"
        );

        Funcionario f3 = new Funcionario(
                "Carlos Sousa", "carlos", "qwerty", false,
                "11223344", "223344556", "665544332",
                "carlos.sousa@email.com", "Rua C, Coimbra", "965432187"
        );

        Funcionario f4 = new Funcionario(
                "Ana Costa", "ana", "abc123", true,
                "44332211", "556677889", "998877665",
                "ana.costa@email.com", "Av. D, Faro", "913245678"
        );

        Funcionario f5 = new Funcionario(
                "Pedro Gomes", "pedro", "zxcvbn", false,
                "33445566", "667788990", "110022334",
                "pedro.gomes@email.com", "Rua E, Braga", "926789012"
        );

        funcionarios.add(f1);
        funcionarios.add(f2);
        funcionarios.add(f3);
        funcionarios.add(f4);
        funcionarios.add(f5);
    }

    public ArrayList<Sala> getSalas() {
        return salas;
    }

    public ArrayList<Funcionario> getFuncionarios() {
        return funcionarios;
    }
}

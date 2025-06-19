package model;

import java.io.*;
import java.util.ArrayList;

public class DadosApp implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private static DadosApp instance = null;
    
    private static final String DIR = "./data/";
    private static final String FILE_NAME = "dadosApp.dat";
    private static final String FILE_PATH = DIR + FILE_NAME;

    public static final int MAX_FILAS = 10;
    public static final int MAX_LUGARES_POR_FILA = 10;
    public static final double PERCENTAGEM_LUGARES_ACESSIVEIS = 0.10;

    private final ArrayList<Sala> salas = new ArrayList<>();
    private final ArrayList<Funcionario> funcionarios = new ArrayList<>();
    private final ArrayList<Fornecedor> fornecedores = new ArrayList<>();


    DadosApp() {
        adicionarSalasExemplo();
        adicionarFuncionariosExemplo();
        adicionarFornecedoresExemplo();
    }

    public static DadosApp getInstance() {
        if (instance == null) {
            carregarDados();
        }
        return instance;
    }

    public void adicionarSala(Sala sala) {
        if (sala == null) {
            throw new IllegalArgumentException("Sala não pode ser nula.");
        }

        if (existeNumeroSala(sala.getNumeroSala())) {
            return;
        }

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

    private void adicionarFornecedoresExemplo() {
        // Fornecedor 1
        Fornecedor f1 = new Fornecedor("Abílio");
        f1.adicionarProduto(new Produto("Coca-Cola 33cl"), 0.45);
        f1.adicionarProduto(new Produto("Água 50cl"), 0.30);
        f1.adicionarProduto(new Produto("Batatas Fritas Pequenas"), 0.60);

        // Fornecedor 2
        Fornecedor f2 = new Fornecedor("José Augusto");
        f2.adicionarProduto(new Produto("Pepsi 33cl"), 0.40);
        f2.adicionarProduto(new Produto("Ice Tea Limão 33cl"), 0.42);
        f2.adicionarProduto(new Produto("Chocolates Mini"), 0.80);

        // Fornecedor 3
        Fornecedor f3 = new Fornecedor("Bina");
        f3.adicionarProduto(new Produto("Fanta Laranja 33cl"), 0.50);
        f3.adicionarProduto(new Produto("Sumol Ananás 33cl"), 0.48);
        f3.adicionarProduto(new Produto("Pipocas Salgadas 500g"), 1.20);

        fornecedores.add(f1);
        fornecedores.add(f2);
        fornecedores.add(f3);
    }




    private static void carregarDados() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            instance = new DadosApp(); // fallback to a new instance
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            instance = (DadosApp) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            instance = new DadosApp();
        }
    }

    public static void gravarDados() {
        try {
            File dir = new File(DIR);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
                oos.writeObject(instance);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Sala> getSalas() {
        return salas;
    }

    public ArrayList<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    //GESTAO STOCK E FORNECEDORES

    public ArrayList<Fornecedor> getFornecedores() {
        return fornecedores;
    }


    //###########################

}

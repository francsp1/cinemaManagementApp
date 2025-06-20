package model;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

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
    private final ArrayList<FaturaFornecedor> faturasFornecedores = new ArrayList<>();
    private final ArrayList<StockProduto> stockProdutos = new ArrayList<>();

    private final ArrayList<Filme> filmes = new ArrayList<>();

    private final HashMap<String, Double> ticketTypes = new HashMap<>();
    private final ArrayList<Fatura> faturas = new ArrayList<>();
    private final ArrayList<Bundle> bundles = new ArrayList<>();
    private final ArrayList<Sessao> sessoes = new ArrayList<>();

    private final ArrayList<Bilhete> bilhetes = new ArrayList<>();

    private final ArrayList<VendaBilhete> vendasBilhete = new ArrayList<>();

    public ArrayList<VendaBilhete> getVendasBilhete() {
        return vendasBilhete;
    }


    DadosApp() {
        adicionarSalasExemplo();
        adicionarFuncionariosExemplo();
        adicionarFornecedoresExemplo();
        inicializarStockExemplo();
        inicializarTiposBilhete();
        inicializarSessao();
        adicionarFilmesEVendasExemplo();
    }

    public static DadosApp getInstance() {
        if (instance == null) {
            carregarDados();
        }
        return instance;
    }

    //APENAS PARA OS TESTES
    public static void resetInstance() {
        instance = new DadosApp();
    }
    public static void setTestInstance(DadosApp testInstance) {
        instance = testInstance;
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


    private void adicionarFilmesEVendasExemplo() {
        Filme f1 = new Filme(
                "O Senhor dos Anéis",
                "Uma aventura épica pela Terra Média.",
                180,
                Filme.Categoria.AVENTURA,
                "Peter Jackson",
                "Elijah Wood",
                "Ian McKellen",
                "Viggo Mortensen",
                "Orlando Bloom",
                Filme.Tipo._2D
        );

        Filme f2 = new Filme(
                "Matrix",
                "Um hacker descobre a verdade sobre sua realidade.",
                136,
                Filme.Categoria.AVENTURA,
                "Lana Wachowski",
                "Keanu Reeves",
                "Laurence Fishburne",
                "Carrie-Anne Moss",
                "Hugo Weaving",
                Filme.Tipo._2D
        );

        filmes.add(f1);
        filmes.add(f2);

        vendasBilhete.add(new VendaBilhete(f1, "Normal", LocalDate.of(2023, 1, 1), 10));
        vendasBilhete.add(new VendaBilhete(f2, "Estudante", LocalDate.of(2025, 6, 2), 5));
        vendasBilhete.add(new VendaBilhete(f1, "Normal", LocalDate.of(2025, 6, 3), 7));
        vendasBilhete.add(new VendaBilhete(f1, "Estudante", LocalDate.of(2025, 6, 3), 2));
        vendasBilhete.add(new VendaBilhete(f2, "Normal", LocalDate.of(2025, 6, 4), 8));
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
        f1.adicionarProduto(new Produto("Coca-Cola 33cl",0.50), 0.45);
        f1.adicionarProduto(new Produto("Água 50cl",0.40), 0.30);
        f1.adicionarProduto(new Produto("Batatas Fritas Pequenas",0.70), 0.60);

        // Fornecedor 2
        Fornecedor f2 = new Fornecedor("José Augusto");
        f2.adicionarProduto(new Produto("Pepsi 33cl",0.50), 0.40);
        f2.adicionarProduto(new Produto("Ice Tea Limão 33cl",0.52), 0.42);
        f2.adicionarProduto(new Produto("Chocolates Mini",0.90), 0.80);

        // Fornecedor 3
        Fornecedor f3 = new Fornecedor("Bina");
        f3.adicionarProduto(new Produto("Fanta Laranja 33cl",0.60), 0.50);
        f3.adicionarProduto(new Produto("Sumol Ananás 33cl",0.58), 0.48);
        f3.adicionarProduto(new Produto("Pipocas Salgadas 500g",1.40), 1.20);

        fornecedores.add(f1);
        fornecedores.add(f2);
        fornecedores.add(f3);
    }

    private void inicializarStockExemplo() {
        stockProdutos.add(new StockProduto(new Produto("Ice Tea Limão 33cl",0.52), 24));
        stockProdutos.add(new StockProduto(new Produto("Fanta Laranja 33cl",0.60), 0));
        stockProdutos.add(new StockProduto(new Produto("Sumol Ananás 33cl",0.58), 100));
    }

    public void adicionarFaturaFornecedor(FaturaFornecedor fatura) {
        if (fatura != null) {
            faturasFornecedores.add(fatura);
        }
    }

    private void inicializarTiposBilhete(){
        ticketTypes.put("Normal", 10.0);
        ticketTypes.put("Estudante", 7.5);
        ticketTypes.put("+65", 6.0);
    }

    private void inicializarSessao(){
        sessoes.add(new Sessao(new Sala(5, 5, 2, TipoSala.TRES_D, TipoSistemaSom.DOLBY_ATMOS, "Sala 3D")
                , 120, LocalDate.now().atStartOfDay(),  new Filme(
                "Matrix",
                "Um hacker descobre a verdade sobre sua realidade.",
                136,
                Filme.Categoria.AVENTURA,
                "Lana Wachowski",
                "Keanu Reeves",
                "Laurence Fishburne",
                "Carrie-Anne Moss",
                "Hugo Weaving",
                Filme.Tipo._2D
        )));

    }

    public ArrayList<Sessao> getSessoes() {
        return sessoes;
    }

    public Sessao getSessaoPorTitulo(String tituloEDataHora) {
        for (Sessao sessao : sessoes) {
            String titulo = sessao.getFilme().getTitulo();
            String dataHora = sessao.getDataHora().toString();

            String chaveSessao = titulo + " - " + dataHora;

            if (chaveSessao.equals(tituloEDataHora)) {
                return sessao;
            }
        }
        return null;
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
    public ArrayList<FaturaFornecedor> getFaturasFornecedores() {
        return faturasFornecedores;
    }

    public ArrayList<StockProduto> getStockProdutos() {
        return stockProdutos;
    }

    public StockProduto getStockProdutoPorNome(String nomeProduto) {
        for (StockProduto sp : stockProdutos) {
            if (sp.getProduto().getNome().equals(nomeProduto)) {
                return sp;
            }
        }
        return null;
    }

    public HashMap<String, Double> getTicketTypes() {
        return ticketTypes;
    }

    public void adicionarFatura(Fatura fatura) {
        if (fatura != null) {
            faturas.add(fatura);
        }
    }

    public void adicionarBundle(Bundle bundle) {
        if (bundle != null) {
            bundles.add(bundle);
        }
    }


    public void adicionarFilme(Filme filme) {
        if (filme != null) {
            filmes.add(filme);
        }
    }

    public ArrayList<Filme> getFilmes() {
        return filmes;
    }


    public static Map<String, List<Map.Entry<Filme, Integer>>> filmesMaisVistosPorTipoBilhete(
            List<VendaBilhete> vendas, LocalDate dataInicio, LocalDate dataFim) {
        Map<String, Map<Filme, Integer>> contagem = new HashMap<>();

        for (VendaBilhete v : vendas) {
            if (v.getDataVenda().isBefore(dataInicio) || v.getDataVenda().isAfter(dataFim)) continue;
            String tipo = v.getTipoBilhete();
            Filme filme = v.getFilme();
            contagem.putIfAbsent(tipo, new HashMap<>());
            Map<Filme, Integer> filmes = contagem.get(tipo);
            filmes.put(filme, filmes.getOrDefault(filme, 0) + v.getQuantidade());
        }

        Map<String, List<Map.Entry<Filme, Integer>>> resultado = new HashMap<>();
        for (String tipo : contagem.keySet()) {
            List<Map.Entry<Filme, Integer>> lista = new ArrayList<>(contagem.get(tipo).entrySet());
            lista.sort((a, b) -> b.getValue() - a.getValue());
            resultado.put(tipo, lista);
        }
        return resultado;
    }

}

package model;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
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
    private final ArrayList<Sessao> sessoes = new ArrayList<>();

    private final HashMap<String, Double> ticketTypes = new HashMap<>();
    private final ArrayList<Fatura> faturas = new ArrayList<>();
    private final ArrayList<Bundle> bundles = new ArrayList<>();


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
        adicionarFilmesExemplo();
        adicionarSessoesExemplo();
        inicializarBundle();
        inicializarFaturas();
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
    private void inicializarFaturas(){
        ArrayList<linhaFatura> linhasFatura = new ArrayList<>();
        linhasFatura.add(new linhaFatura(new Produto("Ice Tea Limão 33cl",0.52), 10, 5.20));
        linhasFatura.add(new linhaFatura(new Produto("Fanta Laranja 33cl",0.60), 5, 3.00));

        Funcionario funcionario = new Funcionario(
                "João Silva", "12345678", "joao.silva@email.com",
                "Rua A, Lisboa", "912345678", "jsilva", "1234", true
        );

        Fatura fatura = new Fatura(linhasFatura, 8.20, funcionario);
        faturas.add(fatura);

        ArrayList<linhaFatura> linhasFatura2 = new ArrayList<>();
        linhasFatura.add(new linhaFatura(new Produto("Ice Tea Limão 33cl",0.52), 11, 5.20));
        linhasFatura.add(new linhaFatura(new Produto("Fanta Laranja 33cl",0.60), 6, 3.00));

        Funcionario funcionario2 = new Funcionario(
                "João Silva", "12345678", "joao.silva@email.com",
                "Rua A, Lisboa", "912345678", "jsilva", "1234", true
        );

        Fatura fatura2 = new Fatura(linhasFatura2, 10.0, funcionario2);
        faturas.add(fatura2);
    }

    public ArrayList<Fatura> getFaturas(){
        return faturas;
    }

    private void inicializarBundle(){
        ArrayList<Produto> produtos = new ArrayList<>();
        produtos.add(new Produto("Ice Tea Limão 33cl",0.40));

        ArrayList<String> bilhetes = new ArrayList<>();
        bilhetes.add("Normal");

        Bundle bundle = new Bundle("Combo 1", 12.0, produtos, bilhetes);
        bundles.add(bundle);
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

    public double getPrecoBilhete(String tipoBilhete) {
        return ticketTypes.getOrDefault(tipoBilhete, 0.0);
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
        System.out.println("Ticket Types: " + ticketTypes);
        return ticketTypes;
    }
    //###########################


    public void adicionarFilme(Filme filme) {
        if (filme != null) {
            filmes.add(filme);
        }
    }

    public ArrayList<Filme> getFilmes() {
        return filmes;
    }

    private void adicionarFilmesExemplo() {
        Filme filme1 = new Filme("Inception", "Um ladrão invade sonhos para roubar segredos.", 148,
                Filme.Categoria.THRILLER, "Christopher Nolan", "Leonardo DiCaprio", "Joseph Gordon-Levitt", "Elliot Page", "Tom Hardy",
                Filme.Tipo._2D);

        Filme filme2 = new Filme("Avatar", "Humanos colonizam Pandora.", 162,
                Filme.Categoria.FANTASIA, "James Cameron", "Sam Worthington", "Zoe Saldana", "Sigourney Weaver", "Stephen Lang",
                Filme.Tipo._3D);

        Filme filme3 = new Filme("La La Land", "Dois artistas lutam por seus sonhos em LA.", 128,
                Filme.Categoria.MUSICAL, "Damien Chazelle", "Ryan Gosling", "Emma Stone", "", "",
                Filme.Tipo._2D);

        Filme filme4 = new Filme("O Exorcista", "Menina possuída por demônio.", 122,
                Filme.Categoria.TERROR, "William Friedkin", "Linda Blair", "Ellen Burstyn", "", "",
                Filme.Tipo.IMAX);

        this.filmes.add(filme1);
        this.filmes.add(filme2);
        this.filmes.add(filme3);
        this.filmes.add(filme4);
    }

    private void adicionarSessoesExemplo() {
        // Obtem as listas diretamente da instância DadosApp
        ArrayList<Filme> filmes = this.filmes;
        ArrayList<Sala> salas = this.salas;

        // Verifica se existem filmes e salas suficientes
        if (filmes.size() < 4 || salas.size() < 4) {
            System.out.println("Erro: Não há filmes ou salas suficientes para adicionar sessões de exemplo.");
            return;
        }

        // Cria sessões exemplo
        Sessao sessao1 = new Sessao(salas.get(0), 160, LocalDateTime.of(2025, 6, 21, 15, 0), filmes.get(0));
        Sessao sessao2 = new Sessao(salas.get(1), 170, LocalDateTime.of(2025, 6, 21, 18, 30), filmes.get(1));
        Sessao sessao3 = new Sessao(salas.get(2), 130, LocalDateTime.of(2025, 6, 22, 14, 15), filmes.get(2));
        Sessao sessao4 = new Sessao(salas.get(3), 125, LocalDateTime.of(2025, 6, 22, 22, 0), filmes.get(3));

        // Adiciona sessões à lista de sessões
        this.sessoes.add(sessao1);
        this.sessoes.add(sessao2);
        this.sessoes.add(sessao3);
        this.sessoes.add(sessao4);

    }

    public ArrayList<Sessao> getSessoes() {
        return sessoes;
    }

    public void adicionarSessao(Sessao sessao) {
        if (sessao != null) {
            sessoes.add(sessao);
        }
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

package view;

import javax.swing.*;

public class JanelaVerFilmes extends JFrame {
    private JLabel pnlPrincipal;
    private JList list1;
    private JButton verDetalhesButton;

    public JanelaVerFilmes() {
        super("Filmes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(pnlPrincipal);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public static void main(String[] args) {
        // Criar janela (frame)
        JFrame frame = new JFrame("Filmes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);


        // Lista de filmes
        String[] filmes = {
                "Duna: Parte Dois",
                "Oppenheimer",
                "Barbie",
                "Godzilla x Kong: The New Empire",
                "Wonka",
                "The Marvels",
                "The Hunger Games: The Ballad of Songbirds & Snakes",
                "Civil War",
                "The Fall Guy",
                "Inside Out 2"
        };

        // Criar JList com os filmes
        JList<String> listaFilmes = new JList<>(filmes);
        listaFilmes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(listaFilmes);

        // Adicionar à janela
        frame.add(scrollPane);
        frame.setVisible(true);
    }
}

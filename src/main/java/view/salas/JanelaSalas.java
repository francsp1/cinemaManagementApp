package view.salas;

import model.DadosApp;
import model.Sala;

import javax.swing.*;

public class JanelaSalas extends JFrame {

    private JPanel pnlSalas;
    private JButton btnSair;
    private JButton btnDetalhes;
    private JScrollPane sclPnSalas;
    private JList lstSalas;
    private JButton btnAdicionarSala;

    public static void main(String[] args) {
        JanelaSalas janela = new JanelaSalas();
    }

    public JanelaSalas() {
        super("Lista de Salas");
        setContentPane(pnlSalas);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();

        preencherListaSalas();

        setVisible(true);
    }

    private void preencherListaSalas() {
        DefaultListModel<Sala> modeloLista = new DefaultListModel<>();
        lstSalas.setModel(modeloLista);

        for (Sala sala : DadosApp.INSTANCIA.getSalas()) {
            modeloLista.addElement(sala);
        }
    }
}

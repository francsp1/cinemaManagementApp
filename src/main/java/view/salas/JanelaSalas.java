package view.salas;

import model.DadosApp;
import model.Sala;

import javax.swing.*;

public class JanelaSalas extends JFrame {

    private JPanel panelSalas;
    private JButton btnCancelar;
    private JButton btnDetalhes;
    private JScrollPane sclPnSalas;
    private JList lstSalas;

    public JanelaSalas() {
        super("Lista de Salas");
        setContentPane(panelSalas);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();

        DefaultListModel<Sala> modeloLista = new DefaultListModel<>();
        lstSalas.setModel(modeloLista);

        for (Sala sala : DadosApp.INSTANCIA.getSalas()) {
            modeloLista.addElement(sala);
        }

        setVisible(true);
    }

    public static void main(String[] args) {
        JanelaSalas janela = new JanelaSalas();
    }
}

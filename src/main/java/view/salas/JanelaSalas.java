package view.salas;

import model.DadosApp;
import model.Sala;

import javax.swing.*;

public class JanelaSalas extends JFrame {
    private JFrame parentFrame;
    private JPanel pnlSalas;
    private JButton btnSair;
    private JScrollPane sclPnSalas;
    private JList lstSalas;
    private JButton btnAdicionarSala;
    private JButton btnDetalesSala;
    private JButton btnSessoesSala;

    public static void main(String[] args) {
        JanelaSalas janela = new JanelaSalas(null);
    }

    public JanelaSalas(JFrame parentFrame) {
        super("Lista de Salas");
        this.parentFrame = parentFrame;
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

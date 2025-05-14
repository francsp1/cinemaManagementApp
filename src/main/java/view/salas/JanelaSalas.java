package view.salas;

import model.DadosApp;
import model.Sala;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class JanelaSalas extends JFrame {
    private JFrame parentFrame;
    private JPanel pnlSalas;
    private JButton btnSair;
    private JScrollPane sclSalas;
    private JList lstSalas;
    private JButton btnAdicionarSala;
    private JButton btnDetalesSala;
    private JButton btnSessoesSala;

    public static void main(String[] args) {
        JanelaSalas janela = new JanelaSalas(null, true);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public JanelaSalas(JFrame parentFrame, boolean isGestor) {
        super("Lista de Salas");
        this.parentFrame = parentFrame;
        setContentPane(pnlSalas);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);

        addActionListeners(isGestor);

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

    private void addActionListeners(boolean isGestor) {
        if (isGestor) {
            btnAdicionarSala.addActionListener(this::btnAdicionarSalaActionPerformed);
        } else {
            btnAdicionarSala.setVisible(false);
        }

        btnSair.addActionListener(this::btnSairActionPerformed);
    }
    
    private void btnSairActionPerformed(ActionEvent e) {
        if (parentFrame != null) {
            parentFrame.setVisible(true);
        }
        dispose();
    }

    private void btnAdicionarSalaActionPerformed(ActionEvent e) {
        //TODO
    }
}

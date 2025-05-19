package view.salas;

import model.DadosApp;
import model.Sala;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class JanelaSalas extends JFrame {
    private final JFrame parentFrame;
    private JPanel pnlSalas;
    private JButton btnSair;
    private JScrollPane sclSalas;
    private JList lstSalas;
    private JButton btnAdicionarSala;
    private JButton btnVerDetalesSala;
    private JButton btnSessoesSala;

    private DefaultListModel<Sala> modeloLista;

    public static void main(String[] args) {
        JanelaSalas janela = new JanelaSalas(null, true);
    }

    public JanelaSalas(JFrame parentFrame, boolean isGestor) {
        super("Lista de Salas");
        this.parentFrame = parentFrame;
        setContentPane(pnlSalas);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);

        addListeners(isGestor);

        preencherListaSalas();

        setVisible(true);
    }

    public void preencherListaSalas() {
        modeloLista = new DefaultListModel<>();
        lstSalas.setModel(modeloLista);

        for (Sala sala : DadosApp.INSTANCIA.getSalas()) {
            adicionarElemento(sala);
        }
    }

    public void adicionarElemento(Sala sala) {
        modeloLista.addElement(sala);
    }

    private void addListeners(boolean isGestor) {
        btnSair.addActionListener(this::btnSairActionPerformed);

        if (isGestor) {
            btnAdicionarSala.addActionListener(this::btnAdicionarSalaActionPerformed);
        } else {
            btnAdicionarSala.setVisible(false);
        }

        btnVerDetalesSala.addActionListener(this::btnDetalesSalaActionPerformed);

    }
    
    private void btnSairActionPerformed(ActionEvent e) {
        if (parentFrame != null) {
            parentFrame.setVisible(true);
        }
        dispose();
    }

    private void btnAdicionarSalaActionPerformed(ActionEvent e) {
        JanelaAdicionarSala janelaAdicionarSala = new JanelaAdicionarSala(this);
        setVisible(false);
    }

    private void btnDetalesSalaActionPerformed(ActionEvent e) {
        Sala salaSelecionada = (Sala) lstSalas.getSelectedValue();
        if (salaSelecionada != null) {
            new JanelaDetalhesSala(parentFrame, salaSelecionada);
            setVisible(false);
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma sala para ver os detalhes.", "Erro", JOptionPane.WARNING_MESSAGE);
        }
    }
}

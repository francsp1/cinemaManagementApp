package view.salas;

import model.DadosApp;
import model.Sala;
import view.Janela;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class JanelaSalas extends Janela {
    private final JFrame parentFrame;
    private JPanel pnlSalas;
    private JButton btnSair;
    private JScrollPane sclSalas;
    private JList lstSalas;
    private JButton btnAdicionarSala;
    private JButton btnVerDetalesSala;
    private JButton btnSessoesSala;

    private final boolean isGestor;

    private static final String ERRO_1 = "Deve selecionar uma sala para poder ver os detalhes.";

    private DefaultListModel<Sala> modeloLista;

    public static void main(String[] args) {
        JanelaSalas janela = new JanelaSalas(null, true);
    }

    public JanelaSalas(JFrame parentFrame, boolean isGestor) {
        super("Lista de Salas");
        this.parentFrame = parentFrame;
        this.isGestor = isGestor;
        setContentPane(pnlSalas);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);

        addListeners();

        configurarCampos();

        preencherListaSalas();

        setVisible(true);
    }

    private void addListeners() {
        btnSair.addActionListener(this::btnSairActionPerformed);
        if (isGestor) {
            btnAdicionarSala.addActionListener(this::btnAdicionarSalaActionPerformed);
        }
        btnVerDetalesSala.addActionListener(this::btnVerEditarDetalesSalaActionPerformed);
    }

    private void configurarCampos() {
        if (!isGestor) {
            btnAdicionarSala.setVisible(false);
        }
    }

    public void preencherListaSalas() {
        modeloLista = new DefaultListModel<>();
        lstSalas.setModel(modeloLista);

        for (Sala sala : DadosApp.getInstance().getSalas()) {
            adicionar(sala);
        }
    }

    public void adicionar(Sala sala) {
        modeloLista.addElement(sala);
    }
    
    private void btnSairActionPerformed(ActionEvent e) {
        dispose();
        if (parentFrame != null) {
            parentFrame.setVisible(true);
        }
    }

    private void btnAdicionarSalaActionPerformed(ActionEvent e) {
        setVisible(false);
        JanelaAdicionarSala janelaAdicionarSala = new JanelaAdicionarSala(this);
    }

    private void btnVerEditarDetalesSalaActionPerformed(ActionEvent e) {
        Sala salaSelecionada = (Sala) lstSalas.getSelectedValue();
        if (salaSelecionada == null) {
            mostrarErro(ERRO_1);
            return;
        }
        setVisible(false);
        new JanelaDetalhesSala(this, salaSelecionada, isGestor);
    }
}

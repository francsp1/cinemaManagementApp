package view;

import model.DadosApp;
import model.Filme;
import model.Funcionario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;


public class JanelaAdicionarFilme extends Janela{
    private JTextField tituloField;
    private JComboBox categoriaCombo;
    private JButton btnAdicionar;
    private JButton btnCancelar;
    private JSpinner duracaoSpinner;
    private JTextArea sinopseArea;
    private JComboBox tipoCombo;
    private JTextField realizadorField;
    private JTextField ator1Field;
    private JTextField ator3Field;
    private JTextField ator4Field;
    private JTextField ator2Field;
    private JPanel panel1;
    private final JFrame parentFrame;
    private final boolean isGestor;

    public JanelaAdicionarFilme(JFrame parentFrame, boolean isGestor) {
        super("Adicionar Filme");
        this.parentFrame = parentFrame;
        this.isGestor = isGestor;
        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800,1000);

        duracaoSpinner.setModel(new SpinnerNumberModel(90, 1, 600, 1));

        for (Filme.Tipo tipo : Filme.Tipo.values()) {
            tipoCombo.addItem(tipo);
        }

        for (Filme.Categoria categoria : Filme.Categoria.values()) {
            categoriaCombo.addItem(categoria);
        }

        setVisible(true);
        pack();

        addListeners();

    }

    private void addListeners() {
        btnAdicionar.addActionListener(this::btnAdicionarActionPerformed);
        btnCancelar.addActionListener(this::btnCancelarActionPerformed);
    }

    private boolean validarCamposObrigatorios() {
        if (tituloField.getText().trim().isEmpty()) return false;
        if (sinopseArea.getText().trim().isEmpty()) return false;
        if (realizadorField.getText().trim().isEmpty()) return false;
        if (ator1Field.getText().trim().isEmpty()) return false;
        if (duracaoSpinner.getValue() == null || (int) duracaoSpinner.getValue() <= 0) return false;
        if (categoriaCombo.getSelectedItem() == null) return false;
        if (tipoCombo.getSelectedItem() == null) return false;

        return true;
    }


    private void btnAdicionarActionPerformed(ActionEvent e) {

      /*  ArrayList<Filme> filmes = DadosApp.getInstance().getFilmes();

        if (filmes == null || filmes.isEmpty()) {
            System.out.println("Lista de filmes está vazia ou nula.");
        } else {
            System.out.println("Lista de filmes:");
            for (Filme f : filmes) {
                System.out.println(f);
            }
        }


        System.out.println("Título: " + tituloField.getText());
        System.out.println("Sinopse: " + sinopseArea.getText());
        System.out.println("Realizador: " + realizadorField.getText());
        System.out.println("Ator 1: " + ator1Field.getText());
        System.out.println("Duração: " + duracaoSpinner.getValue());
        System.out.println("Categoria: " + categoriaCombo.getSelectedItem());
        System.out.println("Tipo: " + tipoCombo.getSelectedItem());
*/
        if (!validarCamposObrigatorios()) {
            JOptionPane.showMessageDialog(this,
                    "Dados Inválidos/incompletos.",
                    "Erro",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        Filme filme = new Filme(
                tituloField.getText(),
                sinopseArea.getText(),
                (int) duracaoSpinner.getValue(),
                (Filme.Categoria) categoriaCombo.getSelectedItem(),
                realizadorField.getText(),
                ator1Field.getText(),
                ator2Field.getText(),
                ator3Field.getText(),
                ator4Field.getText(),
                (Filme.Tipo) tipoCombo.getSelectedItem()
        );
        System.out.println("Filme criado: " + filme);

        DadosApp.getInstance().adicionarFilme(filme);

        JOptionPane.showMessageDialog(this, "Filme guardado com sucesso!");

    }

    private void btnCancelarActionPerformed(ActionEvent e) {
        setVisible(false);
        if(parentFrame!=null){
            parentFrame.setVisible(true);
        }
    }

}

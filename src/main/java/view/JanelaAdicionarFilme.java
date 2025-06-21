//Rodrigo Correia 2231856
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
        setLocationRelativeTo(null);
        setSize(900,600);

        duracaoSpinner.setModel(new SpinnerNumberModel(90, 1, 600, 1));

        for (Filme.Tipo tipo : Filme.Tipo.values()) {
            tipoCombo.addItem(tipo);
        }

        for (Filme.Categoria categoria : Filme.Categoria.values()) {
            categoriaCombo.addItem(categoria);
        }

        setVisible(true);

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


        DadosApp.getInstance().adicionarFilme(filme);

        showTimedDialog("Filme adicionado com sucesso!", 2000);

    }

    private void btnCancelarActionPerformed(ActionEvent e) {
        setVisible(false);
        if(parentFrame!=null){
            parentFrame.setVisible(true);
        }
    }

    private void showTimedDialog(String mensagem, int duracaoMillis) {
        JOptionPane pane = new JOptionPane(mensagem, JOptionPane.INFORMATION_MESSAGE);
        JDialog dialog = pane.createDialog(this, "Info");
        dialog.setModal(false);
        dialog.setVisible(true);

        // Fecha automaticamente após X milissegundos
        new Timer(duracaoMillis, e -> dialog.dispose()).start();
    }

}

//Rodrigo Correia 2231856
package view;

import model.DadosApp;
import model.Filme;
import model.Sala;
import model.Sessao;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.time.LocalDateTime;

public class JanelaAdicionarSessao extends Janela {
    private JComboBox filmeComboBox;
    private JComboBox salaComboBox;
    private JSpinner horaSpinner;
    private JSpinner minutoSpinner;
    private JSpinner diaSpinner;
    private JSpinner mesSpinner;
    private JButton btnAdicionar;
    private JButton btnCancelar;
    private JSpinner duracaoSpinner;
    private JPanel painel1;
    private JSpinner anoSpinner;
    private final JFrame parentFrame;
    private final boolean isGestor;

    public JanelaAdicionarSessao(JFrame parentFrame, boolean isGestor) {


        super("Adicionar Sessão");
        this.parentFrame = parentFrame;
        this.isGestor = isGestor;

        setContentPane(painel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setSize(900,600);
        setVisible(true);


        duracaoSpinner.setModel(new SpinnerNumberModel(90, 1, 600, 1));


        salaComboBox.setModel(new DefaultComboBoxModel<>(
                DadosApp.getInstance().getSalas().toArray(new Sala[0])
        ));

        filmeComboBox.setModel(new DefaultComboBoxModel<>(
                DadosApp.getInstance().getFilmes().toArray(new Filme[0])
        ));

        diaSpinner.setModel(new SpinnerNumberModel(1, 1, 31, 1));
        mesSpinner.setModel(new SpinnerNumberModel(1, 1, 12, 1));
        horaSpinner.setModel(new SpinnerNumberModel(0, 0, 23, 1));
        minutoSpinner.setModel(new SpinnerNumberModel(0, 0, 59, 1));
        anoSpinner.setModel(new SpinnerNumberModel(2025, 2025, 3000, 1));

        addListeners();
    }

    private void addListeners() {
        btnAdicionar.addActionListener(this::btnAdicionarActionPerformed);
        btnCancelar.addActionListener(this::btnCancelarActionPerformed);
    }

    private boolean validarCamposSessao() {
        // Verificar se sala e filme estão selecionados
        if (salaComboBox.getSelectedItem() == null) return false;
        if (filmeComboBox.getSelectedItem() == null) return false;

        // Verificar se os valores de data/hora são válidos
        try {
            int dia = (int) diaSpinner.getValue();
            int mes = (int) mesSpinner.getValue();
            int ano = (int) anoSpinner.getValue();
            int hora = (int) horaSpinner.getValue();
            int minuto = (int) minutoSpinner.getValue();
            LocalDateTime.of(ano, mes, dia, hora, minuto); // Lança exceção se inválido
        } catch (Exception e) {
            return false;
        }

        // Verificar se duração da sessão é válida
        Integer duracaoSessao = (Integer) duracaoSpinner.getValue();
        Filme filmeSelecionado = (Filme) filmeComboBox.getSelectedItem();

        if (duracaoSessao == null || duracaoSessao <= 0) return false;
        if (filmeSelecionado == null) return false;

        // Verificar se duração da sessão é menor que a do filme
        if (duracaoSessao < filmeSelecionado.getDuracao()) return false;

        return true;
    }



    private void btnAdicionarActionPerformed(ActionEvent e) {

                if (!validarCamposSessao()) {
                    //JOptionPane.showMessageDialog(this, "Dados inválidos ou incompletos.", "Erro", JOptionPane.ERROR_MESSAGE);
                    showTimedDialog("Dados Inválidos, verifique se todos os campos foram preenchidos e se a sessão não tem uma duração menor do que o filme.", 2000);
                    return;
                }

                int dia = (int) diaSpinner.getValue();
                int mes = (int) mesSpinner.getValue();
                int hora = (int) horaSpinner.getValue();
                int minuto = (int) minutoSpinner.getValue();
                int ano = (int) anoSpinner.getValue();

                LocalDateTime dataHora = LocalDateTime.of(ano, mes, dia, hora, minuto);

                Sessao novaSessao = new Sessao(
                        (Sala) salaComboBox.getSelectedItem(),
                        (int) duracaoSpinner.getValue(),
                        dataHora,
                        (Filme) filmeComboBox.getSelectedItem()
                );
                DadosApp.getInstance().adicionarSessao(novaSessao);
                showTimedDialog("Sessão adicionada com sucesso!", 2000);


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

    public JComboBox getFilmeComboBox() {
        return filmeComboBox;
    }
}

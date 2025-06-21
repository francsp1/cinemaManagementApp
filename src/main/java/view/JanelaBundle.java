//Tomás Santos nº2230717
package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class JanelaBundle extends Janela{
    private JTable table1;
    private JButton adicionarBundleButton;
    private JButton removerBundleButton;
    private JButton voltarButton;
    private JPanel mainPanel;

    private final JFrame parentFrame;

    public JanelaBundle(JFrame parent) {
        super("Bundles");

        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(parent);
        setVisible(true);
        this.parentFrame = parent;

        //adicionar Bundle
        adicionarBundleButton.addActionListener(e -> {
            JanelaAdicionarBundle janelaAdicionarBundle = new JanelaAdicionarBundle(this);
            janelaAdicionarBundle.setVisible(true);
            setVisible(false);
        });

        //botao voltar
        voltarButton.addActionListener(e -> {
            dispose();
            parentFrame.setVisible(true);
        });

    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

}

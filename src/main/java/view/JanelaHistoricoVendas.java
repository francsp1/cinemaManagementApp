package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class JanelaHistoricoVendas extends Janela{
    private JTable table1;
    private JComboBox comboBox1;
    private JButton voltarButton;
    private JPanel mainPanel;

    private final JFrame parentFrame;

    public JanelaHistoricoVendas(JFrame parent) {
        super("Historico de Vendas");
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(parent);
        setVisible(true);
        this.parentFrame = parent;

        // Initialize the components here if needed
        String[] columnNames = {"Produtos","Vendedor","Data", "Preço"};


        table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

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

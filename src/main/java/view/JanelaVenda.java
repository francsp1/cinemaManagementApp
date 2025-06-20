package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class JanelaVenda extends Janela {
    private JPanel Cabecalho;
    private JButton finalizarCompraButton;
    private JPanel LadoVenda;
    private JList Lista;
    private JScrollPane ListaScroll;
    private JPanel LadoBilhete;
    private JComboBox opSessao;
    private JComboBox comboBox1;
    private JButton adicionarBilheteButton;
    private JTable tabelaProdutos;
    private JButton escolherButton;
    private JTextField textField1;
    private JButton adicionarProdutoButton;
    private JPanel mainPanel;
    private JComboBox comboBox2;
    private JButton cancelarOperaçãoButton;
    private JButton removerLinhaButton;

    public JanelaVenda(JFrame parent) {
        super("Registar Venda");

        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(parent);
        setVisible(true);

        String[] columnNames = {"Produto"};
        String[] products = {"Coca-Cola", "Pepsi", "Fanta"};

        // Create a 2D Object array for the rows
        Object[][] data = new Object[products.length][1];
        for (int i = 0; i < products.length; i++) {
            data[i][0] = products[i]; // Set each product in the only column
        }

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make cells non-editable
            }
        };




        tabelaProdutos.setModel(model);

        tabelaProdutos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

}

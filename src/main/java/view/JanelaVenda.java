package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class JanelaVenda extends JFrame {
    private JPanel Cabecalho;
    private JButton finalizarCompraButton;
    private JPanel LadoVenda;
    private JList Lista;
    private JScrollPane ListaScroll;
    private JPanel LadoBilhete;
    private JComboBox opSessao;
    private JComboBox comboBox1;
    private JButton adicionarBilheteButton;
    private JButton removerBilheteButton;
    private JTable tabelaProdutos;
    private JButton escolherButton;
    private JTextField textField1;
    private JButton adicionarProdutoButton;
    private JButton removerProdutoButton;
    private JPanel mainPanel;
    private JComboBox comboBox2;
    private JButton cancelarOperaçãoButton;

    public JanelaVenda() {
        String[] columnNames = {"Produto", "Quantidade"};
        String[] products = {"Coca-Cola", "Pepsi", "Fanta"};
        String[] stock = {"0", "0", "1"};
        Object[][] data = new Object[products.length][2];  // Inicializa com o tamanho necessário

        for (int i = 0; i < products.length; i++) {
            data[i][0] = products[i];
            data[i][1] = stock[i];
        }

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Nenhuma célula pode ser editada
            }
        };
        tabelaProdutos.setModel(model);

        tabelaProdutos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Stock Bar");
            frame.setSize(800, 400);
            frame.setContentPane(new JanelaVenda().getMainPanel());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class JanelaAdicionarBundle extends Janela{
    private JList list1;
    private JButton criarBundleButton;
    private JButton cancelarOperaçãoButton;
    private JTextField textField1;
    private JTable table1;
    private JButton adicionarButton;
    private JButton removerButton;
    private JTextField textField2;
    private JTextField textField3;
    private JPanel mainPanel;

    public JanelaAdicionarBundle() {
        super("Adicionar Bundle");
        // Initialize the components here if needed
        String[] columnNames = {"Item"};
        String[] products = {"Bilhete Estudante","Bilhete Normal","Bilhete +65","Coca-Cola", "Pepsi", "Fanta"};

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

        table1.setModel(model);

        table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    public JPanel getMainPanel() {
        return mainPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("JanelaAdicionarBundle");
            frame.setSize(800, 400);
            frame.setContentPane(new JanelaAdicionarBundle().getMainPanel());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

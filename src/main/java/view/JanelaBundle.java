package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class JanelaBundle extends Janela{
    private JTable table1;
    private JButton adicionarBundleButton;
    private JButton removerBundleButton;
    private JButton voltarButton;
    private JPanel mainPanel;

    public JanelaBundle() {
        super("Bundles");
        // Initialize the components here if needed
        String[] columnNames = {"Nome", "Itens", "Preço"};
        String[] names = {"Pacote estudante", "Pacote familia"};
        String[] itens = {"1x Bilhete Estudante  +  1x Coca Cola", "2x Bilhete Normal  +  2x Fanta"};
        String[] preco = {"4.50 €", "7.00 €"};
        Object[][] data = new Object[names.length][3];  // Inicializa com o tamanho necessário

        for (int i = 0; i < names.length; i++) {
            data[i][0] = names[i];
            data[i][1] = itens[i];
            data[i][2] = preco[i];
        }

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Nenhuma célula pode ser editada
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
            JFrame frame = new JFrame("JanelaBundle");
            frame.setSize(800, 400);
            frame.setContentPane(new JanelaBundle().getMainPanel());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }

}

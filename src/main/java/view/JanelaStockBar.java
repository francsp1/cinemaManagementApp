package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class JanelaStockBar extends JFrame{
    private JPanel mainPanel;
    private JTable table1;
    private JButton adicionarButton;
    private JTextField quantidadeTextField;
    private JButton adicionarButton1;
    private JTextField textField1;
    private JTable table2;

    public JanelaStockBar() {
        String[] columnNames = {"Produto", "Quantidade"};
        String[] products = {"Coca-Cola", "Pepsi", "Fanta"};
        String[] stock = {"24", "0", "100"};
        Object[][] data = new Object[products.length][2];  // Inicializa com o tamanho necessário

        for (int i = 0; i < products.length; i++) {
            data[i][0] = products[i];
            data[i][1] = stock[i];
        }

        String[] colunasHistorico = {"Produto", "Quantidade", "Data", "Valor (€)"};
        Object[][] historico = {
                {"Coca-Cola", 3, "30/04/2025", 4.50},
                {"Pepsi", 1, "29/04/2025", 1.20},
                {"Fanta", 2, "28/04/2025", 3.00},
                {"Sprite", 5, "27/04/2025", 3.50},
                {"Laranja", 10, "26/04/2025", 2.75},
                {"Guaraná", 7, "25/04/2025", 3.20},
                {"Schweppes", 6, "24/04/2025", 2.50},
                {"Tónica", 4, "23/04/2025", 2.40},
                {"Água", 20, "22/04/2025", 0.80},
                {"Cerveja", 3, "21/04/2025", 1.50},
                {"Vinho Tinto", 2, "20/04/2025", 5.20},
                {"Vinho Branco", 1, "19/04/2025", 4.80},
                {"Lima", 6, "18/04/2025", 3.00},
                {"Melancia", 8, "17/04/2025", 3.60},
                {"Morangos", 5, "16/04/2025", 2.90},
                {"Uva", 4, "15/04/2025", 3.10},
                {"Abacaxi", 7, "14/04/2025", 3.40},
                {"Coco", 9, "13/04/2025", 2.60},
                {"Cerveja Artesanal", 3, "12/04/2025", 6.50},
                {"Sangria", 2, "11/04/2025", 7.20},
                {"Água com gás", 15, "10/04/2025", 1.00},
                {"Laranja Natural", 5, "09/04/2025", 3.30},
                {"Café", 12, "08/04/2025", 1.70},
                {"Chá Preto", 7, "07/04/2025", 2.10},
                {"Chá Verde", 3, "06/04/2025", 2.00},
                {"Chá de Camomila", 4, "05/04/2025", 2.40},
                {"Chá de Erva-Doce", 6, "04/04/2025", 2.30},
                {"Capuccino", 2, "03/04/2025", 2.80},
                {"Macchiato", 1, "02/04/2025", 3.10},
                {"Cappuccino Gelado", 3, "01/04/2025", 3.50},
                {"Suco de Laranja", 5, "31/03/2025", 3.00},
                {"Suco de Maçã", 4, "30/03/2025", 2.80},
                {"Suco de Abacaxi", 6, "29/03/2025", 3.20},
                {"Suco de Manga", 7, "28/03/2025", 3.40},
                {"Suco de Limão", 10, "27/03/2025", 2.50},
                {"Suco de Morango", 5, "26/03/2025", 3.30},
                {"Suco de Framboesa", 4, "25/03/2025", 3.10},
                {"Suco de Goiaba", 2, "24/03/2025", 2.90},
                {"Suco de Caju", 3, "23/03/2025", 3.00},
                {"Suco Detox", 8, "22/03/2025", 4.20},
                {"Refrigerante Diet", 4, "21/03/2025", 3.00},
                {"Refrigerante Zero", 9, "20/03/2025", 3.10},
                {"Energy Drink", 7, "19/03/2025", 2.80},
                {"Suco de Acerola", 5, "18/03/2025", 3.40},
                {"Suco de Cenoura", 4, "17/03/2025", 3.10},
                {"Suco de Beterraba", 6, "16/03/2025", 3.30},
                {"Suco de Pepino", 3, "15/03/2025", 2.70},
                {"Suco de Tomate", 2, "14/03/2025", 2.90},
                {"Café Gelado", 5, "13/03/2025", 2.50},
                {"Frapuccino", 1, "12/03/2025", 4.00},
                {"Smoothie de Morango", 3, "11/03/2025", 3.50},
                {"Smoothie de Banana", 4, "10/03/2025", 3.20},
                {"Milkshake de Chocolate", 6, "09/03/2025", 4.00},
                {"Milkshake de Baunilha", 5, "08/03/2025", 3.70}
        };


        DefaultTableModel model2 = new DefaultTableModel(historico, colunasHistorico) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Nenhuma célula pode ser editada
            }
        };
        table2.setModel(model2);

        table2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


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
            JFrame frame = new JFrame("Stock Bar");
            frame.setSize(800, 400);
            frame.setContentPane(new JanelaStockBar().getMainPanel());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

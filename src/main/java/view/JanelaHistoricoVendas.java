package view;

import model.DadosApp;
import model.Fatura;
import model.linhaFatura;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class JanelaHistoricoVendas extends Janela{
    private JTable table1;
    private JComboBox comboBox1;
    private JButton voltarButton;
    private JPanel mainPanel;

    private final JFrame parentFrame;
    private ArrayList<Fatura> faturas;

    public JanelaHistoricoVendas(JFrame parent) {
        super("Historico de Vendas");
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(parent);
        setVisible(true);
        this.parentFrame = parent;

        faturas = DadosApp.getInstance().getFaturas();

        atualizarTabela();

        //Ordenar
        comboBox1.addActionListener(e -> {
            String criterio = (String) comboBox1.getSelectedItem();

            if ("Data".equalsIgnoreCase(criterio)) {
                faturas.sort((f1, f2) -> f2.getData().compareTo(f1.getData())); // Descrescente por data
            } else if ("Valor".equalsIgnoreCase(criterio)) {
                faturas.sort((f1, f2) -> Double.compare(f2.getValorTotal(), f1.getValorTotal())); // Descrescente por valor
            }

            // Atualiza a tabela com os dados ordenados
            atualizarTabela();
        });

        //botao voltar
        voltarButton.addActionListener(e -> {
            dispose();
            parentFrame.setVisible(true);
        });
    }

    private void atualizarTabela() {
        String[] columnNames = {"Produtos", "Vendedor", "Data", "Preço"};
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Object[][] data = new Object[faturas.size()][4]; // 4 colunas

        for (int i = 0; i < faturas.size(); i++) {
            Fatura fatura = faturas.get(i);

            data[i][0] = fatura.toString();
            data[i][1] = fatura.getFuncionario().getNome();
            data[i][2] = sdf.format(fatura.getData());
            data[i][3] = String.format("€%.2f", fatura.getValorTotal());
        }

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Evita edição
            }
        };

        table1.setModel(model);
        table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    public JPanel getMainPanel() {
        return mainPanel;
    }
}

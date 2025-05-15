package view.salas;

import javax.swing.*;

public class JanelaSessoesSala extends JFrame {
    private final JFrame parentFrame;
    private JPanel pnlSessoesSala;
    private JScrollPane sclSessoesSala;
    private JButton btnSair;
    private JList lstSessoesSala;
    private JButton btnDetalhesSessao;

    public static void main(String[] args) {
        JanelaSessoesSala janela = new JanelaSessoesSala(null);
        janela.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public JanelaSessoesSala(JFrame parentFrame) {
        super("Lista de Sessoes da Sala");
        this.parentFrame = parentFrame;
        setContentPane(pnlSessoesSala);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);

        preencherListaSessoes();

        setVisible(true);
    }

    private void preencherListaSessoes() {

    }
}

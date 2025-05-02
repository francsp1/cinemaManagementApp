package view;

import javax.swing.*;

public class JanelaTeste extends JFrame {

  public static void main(String[] args) {
    JanelaTeste janela = new JanelaTeste();

    janela.setTitle("Janela de Teste");
    janela.setSize(400, 300);
    janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    janela.setLocationRelativeTo(null);
    janela.setVisible(true);


  }
}

package view;

import javax.swing.*;

public abstract class Janela extends JFrame {

    public Janela(String title) {
        super(title);
    }

    protected void mostrarErro(String erro) {
        JOptionPane.showMessageDialog(this,erro , "Erro", JOptionPane.ERROR_MESSAGE);
    }

    protected void mostrarSucesso(String sucesso) {
        JOptionPane.showMessageDialog(this, sucesso, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

}

//Francisco Pedrosa - 2181248
package view.salas;

import model.Lugar;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class BotaoLugar extends JButton {
    private final String designacao;
    private final int fila; //Linha
    private final int numeroLugar; //Coluna
    private boolean isAcessivel;
    private final Lugar lugar;

    public BotaoLugar(Lugar lugar, int fila, int numeroLugar) {
        this.lugar = lugar;
        this.designacao = lugar.getDesignacao();
        this.fila = fila;
        this.numeroLugar = numeroLugar;
        this.isAcessivel = false;

        addActionListener(this::btnLugarActionPerformed);

        setText(designacao);
    }

    private void btnLugarActionPerformed(ActionEvent e) {
        if (isAcessivel) {
            setBackground(null);
            setText(designacao);
            isAcessivel = false;
        } else {
            setBackground(java.awt.Color.GREEN);
            setText(designacao + " (Acessível)");
            isAcessivel = true;
        }
    }

    public String getDesignacao() {
        return designacao;
    }

    public int getFila() {
        return fila;
    }

    public int getNumeroLugar() {
        return numeroLugar;
    }

    public boolean isAcessivel() {
        return isAcessivel;
    }

    public void setAcessivel(boolean acessivel) {
        isAcessivel = acessivel;
    }

    public Lugar getLugar() {
        return lugar;
    }
}

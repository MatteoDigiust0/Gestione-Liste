package gestionelista;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;

public class DrawLine extends JPanel{

    public void paintComponent(Graphics g) {

        g.setColor(Color.black);
        System.out.println("here");
        g.drawLine(Gui.getLastX(), (170-50)/2 + 70 + 25, Gui.getLastX() + 100, (170-50)/2 + 70 + 25);

    }
}

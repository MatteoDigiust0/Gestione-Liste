package gestionelista;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;

public class DrawLine extends JPanel {

    public void paintComponent(Graphics g) {

        g.setColor(Color.black);
        for(int i = 0; i < Gui.getNodePanels().size(); i++){
            System.out.println("here");
            g.drawLine(50 + (100 * i) + (50 * i) + 100, (170-50)/2 + 70 + 25, 50 + (100 * i) + (50 * i) + 200, (170-50)/2 + 70 + 25);
        }
    }
}

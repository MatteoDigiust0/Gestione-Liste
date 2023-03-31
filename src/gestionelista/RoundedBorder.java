//https://stackoverflow.com/questions/25796572/simplest-code-to-round-corners-of-jlabel-in-java

package gestionelista;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;


public class RoundedBorder implements Border {

    private int radius;

    RoundedBorder(int radius) {
        this.radius = radius;
    }
    //@Override
    public Insets getBorderInsets(Component c) {
        return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
    }

    //@Override
    public boolean isBorderOpaque() {
        return true;
    }

    //@Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.drawRoundRect(x,y,width-1,height-1,radius,radius);
    }
}



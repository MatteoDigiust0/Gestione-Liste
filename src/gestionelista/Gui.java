package gestionelista;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;
import java.util.ArrayList;
import java.util.IllegalFormatException;

public class Gui implements ActionListener {


    private final JFrame frame;
    private final JPanel panel;
    private final JPanel buttonsPanel;
    private final JLabel titleLabel;

    private final JScrollPane nodesPanel;
    private final JButton inserisciTestaButton;
    private final JButton inserisciCodaButton;

    private final JSpinner numeroSpinner;

    private final  JPanel scrollPanelInternalPanel;

    private static ArrayList<JPanel> nodePanels;

    static Lista lista;

    private static Border nodePanelBorder = BorderFactory.createLineBorder(Color.black, 5);
    public Gui(){

        frame = new JFrame("Gestione Liste");
        frame.setSize(720, 580);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(null);

        titleLabel = new JLabel("Gestione liste");
        titleLabel.setBounds(0,0,720,20);

        panel = new JPanel();
        panel.setBounds(0,20,720,560);
        panel.setLayout(null);

        numeroSpinner = new JSpinner();
        //numeroSpinner.setBounds(0,40,240,90);

        inserisciTestaButton = new JButton("Inserisci tesa");
        inserisciTestaButton.addActionListener(this);
        //inserisciTestaButton.setBounds(240,40,480,90);

        inserisciCodaButton = new JButton("Inserisci coda");
        inserisciCodaButton.addActionListener(this);
        //inserisciCodaButton.setBounds(480,40, 720,90);

        buttonsPanel = new JPanel();
        buttonsPanel.setBounds(20,55, 660, 50);
        buttonsPanel.setLayout(new GridLayout(1,3, 2,2));
        buttonsPanel.add(numeroSpinner);
        buttonsPanel.add(inserisciTestaButton);
        buttonsPanel.add(inserisciCodaButton);

        scrollPanelInternalPanel = new JPanel();
        //scrollPanelInternalPanel.setBounds(20, 180, 640, 300);
        scrollPanelInternalPanel.setLayout(null);
        scrollPanelInternalPanel.setPreferredSize(new Dimension(2000,300));

        nodesPanel = new JScrollPane(scrollPanelInternalPanel);
        nodesPanel.setBounds(20, 130, 660, 300);
        nodesPanel.setVisible(true);
        nodesPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        nodesPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        nodesPanel.setPreferredSize(new Dimension(100,100));


        panel.add(buttonsPanel);
        panel.add(nodesPanel);
        frame.add(titleLabel);
        frame.add(panel);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == inserisciTestaButton){

            lista.addFront((Integer) numeroSpinner.getValue());
            System.out.println(numeroSpinner.getValue());
            addNode();

        }

    }

    public static void main(String args[]){

        Gui gui = new Gui();
        nodePanels = new ArrayList<JPanel>();
        lista = new Lista();

    }

    private void addNode(){

        nodePanels.add(new JPanel());

        for(int i = 0; i < nodePanels.size(); i++){
            nodePanels.get(i).setBounds(50 + (100 * i) + (50 * i), (170-50)/2 + 70, 100,  50); // y = 150
            nodePanels.get(i).setLayout(new GridLayout(1,2,2,2));
            nodePanels.get(i).setBorder(nodePanelBorder);
            nodePanels.get(i).setVisible(true);
            nodePanels.get(i).setBackground(Color.red);

            nodePanels.get(i).setBorder(BorderFactory.createLineBorder(Color.black));

            //nodesPanel.add(nodePanels.get(i));
            scrollPanelInternalPanel.add(nodePanels.get(i));

            if(nodePanels.size() > 1){
                Graphics g = new Graphics() {
                    @Override
                    public Graphics create() {
                        return null;
                    }

                    @Override
                    public void translate(int x, int y) {

                    }

                    @Override
                    public Color getColor() {
                        return null;
                    }

                    @Override
                    public void setColor(Color c) {

                    }

                    @Override
                    public void setPaintMode() {

                    }

                    @Override
                    public void setXORMode(Color c1) {

                    }

                    @Override
                    public Font getFont() {
                        return null;
                    }

                    @Override
                    public void setFont(Font font) {

                    }

                    @Override
                    public FontMetrics getFontMetrics(Font f) {
                        return null;
                    }

                    @Override
                    public Rectangle getClipBounds() {
                        return null;
                    }

                    @Override
                    public void clipRect(int x, int y, int width, int height) {

                    }

                    @Override
                    public void setClip(int x, int y, int width, int height) {

                    }

                    @Override
                    public Shape getClip() {
                        return null;
                    }

                    @Override
                    public void setClip(Shape clip) {

                    }

                    @Override
                    public void copyArea(int x, int y, int width, int height, int dx, int dy) {

                    }

                    @Override
                    public void drawLine(int x1, int y1, int x2, int y2) {

                    }

                    @Override
                    public void fillRect(int x, int y, int width, int height) {

                    }

                    @Override
                    public void clearRect(int x, int y, int width, int height) {

                    }

                    @Override
                    public void drawRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {

                    }

                    @Override
                    public void fillRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {

                    }

                    @Override
                    public void drawOval(int x, int y, int width, int height) {

                    }

                    @Override
                    public void fillOval(int x, int y, int width, int height) {

                    }

                    @Override
                    public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle) {

                    }

                    @Override
                    public void fillArc(int x, int y, int width, int height, int startAngle, int arcAngle) {

                    }

                    @Override
                    public void drawPolyline(int[] xPoints, int[] yPoints, int nPoints) {

                    }

                    @Override
                    public void drawPolygon(int[] xPoints, int[] yPoints, int nPoints) {

                    }

                    @Override
                    public void fillPolygon(int[] xPoints, int[] yPoints, int nPoints) {

                    }

                    @Override
                    public void drawString(String str, int x, int y) {

                    }

                    @Override
                    public void drawString(AttributedCharacterIterator iterator, int x, int y) {

                    }

                    @Override
                    public boolean drawImage(Image img, int x, int y, ImageObserver observer) {
                        return false;
                    }

                    @Override
                    public boolean drawImage(Image img, int x, int y, int width, int height, ImageObserver observer) {
                        return false;
                    }

                    @Override
                    public boolean drawImage(Image img, int x, int y, Color bgcolor, ImageObserver observer) {
                        return false;
                    }

                    @Override
                    public boolean drawImage(Image img, int x, int y, int width, int height, Color bgcolor, ImageObserver observer) {
                        return false;
                    }

                    @Override
                    public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, ImageObserver observer) {
                        return false;
                    }

                    @Override
                    public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, Color bgcolor, ImageObserver observer) {
                        return false;
                    }

                    @Override
                    public void dispose() {

                    }
                };
                g.setColor(Color.black);
                g.drawLine(50 + (100 * i) + (50 * i) + 100, (170-50)/2 + 70 + 25, 50 + (100 * i) + (50 * i) + 200, (170-50)/2 + 70 + 25);


            }

            //Add value inside a label in the panel

        }

        JLabel label = new JLabel();
        label.setText("num");
        label.setBackground(Color.green);

        nodePanels.get(nodePanels.size()-1).add(label);


    }

}


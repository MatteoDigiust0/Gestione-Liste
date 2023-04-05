package gestionelista;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.awt.Graphics;

public class Gui implements ActionListener {

    //JFrame
    private final JFrame frame;

    //Panels
    private final JPanel panel;
    private final JPanel buttonsPanel;
    private final  JPanel scrollPanelInternalPanel;
    private static ArrayList<JPanel> nodePanels;

    //Label
    private final JLabel titleLabel;
    private final JLabel averageLabel;

    //ScrollPane
    private final JScrollPane nodesPanel;

    //Buttons
    private final JButton inserisciTestaButton;
    private final JButton inserisciCodaButton;

    //TextField
    private final JTextField numeroInput;

    //JCheckBox
    private final JCheckBox enableConditionalInput;

    //Borders
    private static Border nodePanelBorder = BorderFactory.createLineBorder(Color.black, 1);

    //Colors
    private static Color textColor = new Color(52, 58, 64);
    private static Color backgroundColor = new Color(206,212,218);

    //Fonts
    private static Font titleFont = new Font("Figtree", Font.BOLD, 30 );
    private static Font textFont = new Font("Figtree", Font.BOLD,15);

    //Variables
    private static int lastX;
    private static Lista lista;
    private static boolean inserimentoCondizionale;


    public Gui(){

        frame = new JFrame("Gestione Liste");
        frame.setSize(720, 580);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(null);

        titleLabel = new JLabel("Gestione liste");
        titleLabel.setBounds(0,0,720,30);
        titleLabel.setFont(titleFont);

        panel = new JPanel();
        panel.setBounds(0,20,720,560);
        panel.setLayout(null);

        numeroInput = new JTextField();
        numeroInput.setForeground(textColor);
        numeroInput.setBackground(backgroundColor);
        numeroInput.setFont(textFont);

        inserisciTestaButton = new JButton("Inserisci testa");
        inserisciTestaButton.addActionListener(this);
        inserisciTestaButton.setForeground(textColor);
        inserisciTestaButton.setBackground(backgroundColor);
        inserisciTestaButton.setFont(textFont);

        inserisciCodaButton = new JButton("Inserisci coda");
        inserisciCodaButton.addActionListener(this);
        inserisciCodaButton.setForeground(textColor);
        inserisciCodaButton.setBackground(backgroundColor);
        inserisciCodaButton.setFont(textFont);

        buttonsPanel = new JPanel();
        buttonsPanel.setBounds(20,55, 660, 50);
        buttonsPanel.setLayout(new GridLayout(1,3, 2,2));
        buttonsPanel.add(numeroInput);
        buttonsPanel.add(inserisciTestaButton);
        buttonsPanel.add(inserisciCodaButton);

        scrollPanelInternalPanel = new JPanel();
        scrollPanelInternalPanel.setLayout(null);
        scrollPanelInternalPanel.setPreferredSize(new Dimension(2000,300));

        nodesPanel = new JScrollPane(scrollPanelInternalPanel);
        nodesPanel.setBounds(20, 130, 660, 300);
        nodesPanel.setVisible(true);
        nodesPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        nodesPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        nodesPanel.setPreferredSize(new Dimension(100,100));

        averageLabel = new JLabel("Average: ");
        averageLabel.setFont(textFont);
        averageLabel.setForeground(textColor);
        averageLabel.setBounds(20,490,360, 30);
        averageLabel.setVisible(true);

        enableConditionalInput = new JCheckBox();
        enableConditionalInput.addActionListener(this);
        enableConditionalInput.setBounds(435, 490, 660, 30);
        enableConditionalInput.setText("Attivare inserimento condizionale?");
        enableConditionalInput.setFont(textFont);
        enableConditionalInput.setForeground(textColor);


        panel.add(buttonsPanel);
        panel.add(nodesPanel);
        panel.add(averageLabel);
        panel.add(enableConditionalInput);
        frame.add(titleLabel);
        frame.add(panel);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == inserisciTestaButton){

            lista.addFront( Integer.parseInt(numeroInput.getText()));
            addNode();

            for(int i = 0; i < nodePanels.size(); i++){

                nodePanels.get(i).removeAll();
            }

            updateNodes();

        }

        if(e.getSource() == inserisciCodaButton){

            lista.addBehind(Integer.parseInt(numeroInput.getText()));
            addNode();

            Nodo nodo = new Nodo();

            nodo = lista.getHead();

            while(nodo != null){
                System.out.println(nodo.getInfo());
                nodo = nodo.getNext();
            }

            for(int i = 0; i < nodePanels.size(); i++){
                nodePanels.get(i).removeAll();
            }

            updateNodes();

        }

        if(e.getSource() == enableConditionalInput){
            if(enableConditionalInput.isSelected())
                inserimentoCondizionale = true;

            else if(!enableConditionalInput.isSelected())
                inserimentoCondizionale = false;

        }
    }
    
    public static void main(String args[]){

        Gui gui = new Gui();
        nodePanels = new ArrayList<JPanel>();
        lista = new Lista();
        lastX = 50;
        inserimentoCondizionale = false;

    }

    private void updateNodes(){

        Nodo nodoTemp = new Nodo();
        nodoTemp = lista.getHead();

        for(int i = 0; i < nodePanels.size(); i++) {

            if(nodoTemp != null){
            JPanel panel1 = new JPanel(new FlowLayout());
            JPanel panel2 = new JPanel(new FlowLayout());

            panel1.setPreferredSize(new Dimension(50, 50));
            panel2.setPreferredSize(new Dimension(50, 50));

            panel1.add(new JLabel(String.valueOf(nodoTemp.getInfo())));
            panel2.add(new JLabel());

            nodePanels.get(i).add(panel1, BorderLayout.WEST);
            nodePanels.get(i).add(panel2, BorderLayout.EAST);

            panel1.setBorder(nodePanelBorder);
            panel2.setBorder(nodePanelBorder);

            nodoTemp = nodoTemp.getNext();

            }

        }

    }


    private void addNode(){

        nodePanels.add(new JPanel());

        nodePanels.get(nodePanels.size()-1).setBounds(lastX, /*(170-50)/2 + 70*/100, 100, 50);        //(170-50)/2 + 70 + 25, 100, 50
        nodePanels.get(nodePanels.size()-1).setLayout(new BorderLayout());
        nodePanels.get(nodePanels.size()-1).setBorder(nodePanelBorder);
        nodePanels.get(nodePanels.size()-1).setVisible(true);

        lastX+=100;

        scrollPanelInternalPanel.add(nodePanels.get(nodePanels.size()-1));

        drawArrow();
        nodesPanel.repaint();

        /*
        JPanel panel1 = new JPanel(new FlowLayout());
        JPanel panel2 = new JPanel(new FlowLayout());

        panel1.setPreferredSize(new Dimension(50,50));
        panel2.setPreferredSize(new Dimension(50,50));

        panel1.add(new JLabel(String.valueOf(lista.getHead().getInfo())));
        panel2.add(new JLabel());

        nodePanels.get(nodePanels.size() - 1).add(panel1, BorderLayout.WEST);
        nodePanels.get(nodePanels.size() - 1).add(panel2, BorderLayout.EAST);

        panel1.setBorder(nodePanelBorder);
        panel2.setBorder(nodePanelBorder);
        */
        //updateNodes();
        nodesPanel.repaint();
        
    }

    public void drawArrow(){

        if(nodePanels.size() > 1) {
            JLabel arrowLabel = new JLabel("---------->");
            arrowLabel.setFont(new Font("Arial", Font.BOLD, 20));
            arrowLabel.setBounds(lastX - 200, /*(170-50)/2*/100, 100, 50/*(170-50)/2 + 70 + 25*/);
            arrowLabel.setHorizontalAlignment(JLabel.CENTER);
            scrollPanelInternalPanel.add(arrowLabel);
        }

        lastX += 100;

    }

}


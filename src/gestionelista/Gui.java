package gestionelista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Gui implements ActionListener {


    private final JFrame frame;
    private final JPanel panel;
    private final JPanel buttonsPanel;
    private final JLabel titleLabel;
    private final JButton inserisciTestaButton;
    private final JButton inserisciCodaButton;

    private final JSpinner numeroSpinner;

    private static ArrayList<JPanel> nodesPanels;
    public Gui(){

        frame = new JFrame("Gestione Liste");
        frame.setSize(720, 580);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(null);

        titleLabel = new JLabel("Gestione liste");
        titleLabel.setBounds(0,0,720,40);

        panel = new JPanel();
        panel.setBounds(0,40,720,540);

        numeroSpinner = new JSpinner();
        numeroSpinner.setBounds(0,40,240,90);

        inserisciTestaButton = new JButton("Inserisci tesa");
        inserisciTestaButton.setBounds(240,40,480,90);

        inserisciCodaButton = new JButton("Inserisci coda");
        inserisciCodaButton.setBounds(480,40, 720,90);

        buttonsPanel = new JPanel();
        buttonsPanel.setBounds(0,55, 720, 105);
        buttonsPanel.setLayout(new GridLayout(1,3, 2,2));
        buttonsPanel.add(numeroSpinner);
        buttonsPanel.add(inserisciTestaButton);
        buttonsPanel.add(inserisciCodaButton);


        panel.add(buttonsPanel);

        frame.add(titleLabel);
        frame.add(panel);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String args[]){

        Gui gui = new Gui();
        nodesPanels = new ArrayList<JPanel>();

    }

    private void addNode(){

        nodesPanels.add(new JPanel());
        nodesPanels.get(nodesPanels.size() -1).setBounds((panel.getWidth() - (40 * nodesPanels.size()) - (20 * nodesPanels.size() - 1))/2, panel.getHeight() - 20/2,  (40 * nodesPanels.size()) - (20 * nodesPanels.size() - 1), 20);
        nodesPanels.get(nodesPanels.size() - 1).setLayout(new GridLayout(1, 2, 2, 2));

        panel.add(nodesPanels.get(nodesPanels.size() - 1));

        if(nodesPanels.size() > 1){

            Graphics g = null;
            g.drawLine((panel.getWidth() - (40 * nodesPanels.size()) - (20 * nodesPanels.size() - 1))/2 + 40, panel.getHeight() - 20/2 + 10, (40 * nodesPanels.size()) - (20 * nodesPanels.size() - 1)/2 + 40 +20, panel.getHeight() - 20/2 + 10);
        }
    }

}


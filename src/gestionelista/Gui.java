//Attualemente è possibilie eliminare una sola volta un nodo

package gestionelista;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Gui implements ActionListener {

    //JFrame
    private final JFrame frame;

    //Panels
    private final JPanel panel;
    private final JPanel buttonsPanel;
    private final  JPanel scrollPanelInternalPanel;

    //La rappresentazione grafica dei nodi della lista avviene tramite l'utilizzo di vari Jpanel, ogni JPanel contiene un valore della lista, memorizzati in un ArrayList
    private static ArrayList<JPanel> nodePanels;

    //Label
    private final JLabel titleLabel;
    private final JLabel averageLabel;
    private final JLabel notificationLabel;
    private static JLabel lastArrow;
    private static ArrayList<JLabel> arrows;

    //ScrollPane
    private final JScrollPane nodesPanel;

    //Buttons
    private final JButton inserisciTestaButton;
    private final JButton inserisciCodaButton;
    private final JButton deleteButton;

    //TextField
    private final JTextField numeroInput;

    //JCheckBox
    private final JCheckBox enableConditionalInput;

    //Borders
    private static Border nodePanelBorder = BorderFactory.createLineBorder(Color.black, 1);
    private static Border boldBorder = BorderFactory.createLineBorder(Color.black, 3); 

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

    private static int triggeredIndex;


    public Gui(){

        frame = new JFrame("Gestione Liste");
        frame.setSize(720, 580);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(null);

        titleLabel = new JLabel("Gestione liste");
        titleLabel.setBounds(3,0,720,30);
        titleLabel.setFont(titleFont);
        titleLabel.setHorizontalAlignment(JTextField.CENTER);

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

        deleteButton = new JButton("Elimina");
        deleteButton.addActionListener(this);
        deleteButton.setForeground(textColor);
        deleteButton.setBackground(backgroundColor);
        deleteButton.setFont(textFont);
        deleteButton.setEnabled(false);

        buttonsPanel = new JPanel();
        buttonsPanel.setBounds(20,55, 660, 50);
        buttonsPanel.setLayout(new GridLayout(1,4, 2,2));
        buttonsPanel.add(numeroInput);
        buttonsPanel.add(inserisciTestaButton);
        buttonsPanel.add(inserisciCodaButton);
        buttonsPanel.add(deleteButton);


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
        enableConditionalInput.setBounds(485, 490, 660, 30);
        enableConditionalInput.setText("Inserimento condizionale");
        enableConditionalInput.setFont(textFont);
        enableConditionalInput.setForeground(textColor);

        notificationLabel = new JLabel();
        notificationLabel.setBounds(20,440,660,20);
        notificationLabel.setFont(textFont);
        notificationLabel.setForeground(Color.red);
        notificationLabel.setHorizontalAlignment(JTextField.CENTER);


        panel.add(buttonsPanel);
        panel.add(nodesPanel);
        panel.add(averageLabel);
        panel.add(notificationLabel);
        panel.add(enableConditionalInput);
        frame.add(titleLabel);
        frame.add(panel);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == inserisciTestaButton){      //Caso in cui l'utente schiaccia sul JButton per l'inserimento del numero in testa alla pila

            try{


                notificationLabel.setText("");
                checkLength();

                if(inserimentoCondizionale){    //Parte di codice da eseguire nel caso in cui l'utente abbia abilitato l'inserimento condizionale, cioè uno stesso numero può essere inserito una sola volta

                    if(lista.controlloPresenzaElemento(Integer.parseInt(numeroInput.getText()))){       //Controllo che il numero che l'utente vuole inserire non sia gia presente nella lista
                        notificationLabel.setText("Valore già inserito");                          //Nel caso in cui il numero sia giò presente stampo nel Label delle notifiche un messaggio di errore
                        return;
                    }


                }

                lista.addFront( Integer.parseInt(numeroInput.getText()));   //Inserire il numero in testa alla coda
                addNode();

                for(int i = 0; i < nodePanels.size(); i++){

                    nodePanels.get(i).removeAll();
                }

                updateNodes();
                numeroInput.setText("");
                averageLabel.setText("Average: " + lista.media());

            }catch(Exception exc){
                notificationLabel.setText("Valore errato");
            }

        }

        if(e.getSource() == inserisciCodaButton){       //Caso in cui l'utente sceglie di inserire un valore alla fine della lista

            try{

                notificationLabel.setText("");
                checkLength();

                if(inserimentoCondizionale){

                    if(lista.controlloPresenzaElemento(Integer.parseInt(numeroInput.getText()))){
                        notificationLabel.setText("Valore già inserito");
                        return;
                    }

                }

                lista.addBehind(Integer.parseInt(numeroInput.getText()));
                addNode();

                for(int i = 0; i < nodePanels.size(); i++){
                    nodePanels.get(i).removeAll();
                }

                updateNodes();
                numeroInput.setText(null);
                averageLabel.setText("Average: " + lista.media());

            }catch(Exception exc){
                notificationLabel.setText("Valore errato");
                System.out.println(exc);
            }

        }

        if(e.getSource() == enableConditionalInput){        //Caso in cui l'utente decide di abilitare l'inserimento condizionale (un numero può essere inserito una sola volta)
            if(enableConditionalInput.isSelected())
                inserimentoCondizionale = true;

            else if(!enableConditionalInput.isSelected())
                inserimentoCondizionale = false;

        }

        if(e.getSource() == deleteButton){      //Caso in cui l'utente decide di eliminare un nodo precedentemente selezionato

            notificationLabel.setText("");
            lista.elimina(triggeredIndex);

            scrollPanelInternalPanel.remove(nodePanels.get(nodePanels.size() - 1));

            if(!arrows.isEmpty()) {
                scrollPanelInternalPanel.remove(arrows.get(arrows.size() - 1));
                arrows.remove(arrows.size() - 1);
            }

            for(int i = 0; i < nodePanels.size(); i++){
                nodePanels.get(i).removeAll();
            }

            nodePanels.remove(nodePanels.size() - 1);
            lastX-=200;

            System.out.println("\n\n" + lista.visualizzaLista());

            updateNodes();
            nodesPanel.repaint();
            if(nodePanels.size() != triggeredIndex)
                nodePanels.get(triggeredIndex).setBorder(nodePanelBorder);

            deleteButton.setEnabled(false);
            averageLabel.setText("Average: " + lista.media());
        }
    }

    public static void main(String args[]){

        Gui gui = new Gui();
        nodePanels = new ArrayList<JPanel>();
        arrows = new ArrayList<JLabel>();
        lista = new Lista();
        lastX = 50;
        inserimentoCondizionale = false;

    }


    //Richiamato durante l'inserimento di un valore dopo la funzione "AddNode"
    //Modifica i JPanel presenti nell ArrayList in modo da dagli la sembianza tipica dei nodi delle liste e inserisce i valori
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

    //Metodo che viene richiamato quando l'utente inserisce un nuovo valore nella lista
    //Aggiunge un nuovo JPanel nell ArrayList e lo modifica
    private void addNode(){

        nodePanels.add(new JPanel());

        nodePanels.get(nodePanels.size()-1).setBounds(lastX, /*(170-50)/2 + 70*/100, 100, 50);        //(170-50)/2 + 70 + 25, 100, 50
        nodePanels.get(nodePanels.size()-1).setLayout(new BorderLayout());
        nodePanels.get(nodePanels.size()-1).setBorder(nodePanelBorder);
        nodePanels.get(nodePanels.size()-1).setVisible(true);
        nodePanels.get(nodePanels.size()-1).addMouseListener(new MouseAdapter(){

            @Override
            public void mouseClicked(MouseEvent ev) {

                if(nodePanels.size() != triggeredIndex)
                    nodePanels.get(triggeredIndex).setBorder(nodePanelBorder);

                triggeredIndex = nodePanels.indexOf(ev.getSource());
                nodePanels.get(triggeredIndex).setBorder(boldBorder);
                deleteButton.setEnabled(true);

            }
        });

        lastX+=100;

        scrollPanelInternalPanel.add(nodePanels.get(nodePanels.size()-1));

        drawArrow();
        nodesPanel.repaint();

    }

    //Richiamata della funzione "AddNode"
    //Disegna una freccia che collega i nodi tra di loro, nel caso in cui siano più di uno
    private void drawArrow(){

        if(nodePanels.size() > 1) {
            JLabel arrowLabel = new JLabel("---------->");
            arrowLabel.setFont(new Font("Arial", Font.BOLD, 20));
            arrowLabel.setBounds(lastX - 200, /*(170-50)/2*/100, 100, 50/*(170-50)/2 + 70 + 25*/);
            arrowLabel.setHorizontalAlignment(JLabel.CENTER);
            scrollPanelInternalPanel.add(arrowLabel);
            arrows.add(arrowLabel);
        }

        lastX += 100;

    }

    //Metodo che restituisce una eccezione se il numero inserito ha più di 5 cifre
    //Necessario per evitare problemi con la visualizzazione dei numeri all'interno dei JPanel che vanno a formare i nodi della lista.
    private void checkLength() throws Exception{

        if(numeroInput.getText().length() > 6)
            throw new Exception();

    }


}


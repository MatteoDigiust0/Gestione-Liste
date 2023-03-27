package gestionelista;

public class Lista {
    private Nodo head;

    public Lista(){
        head = null;
    }

    public void addFront(int valore){
        Nodo nodo = new Nodo(valore);

        if(head == null)
            head = nodo;

        else { //Lista non vuota
            nodo.setNext(head);
            head = nodo;
        }
    }

    public void addBehind(int valore){

        if(head == null)
            addFront(valore);

        else{   //La lista non è vuota
            
            Nodo nodo = new Nodo(valore);
            Nodo nodoTemp = new Nodo();
            nodoTemp = head;

            while(nodoTemp.getNext() != null){
                nodoTemp = nodoTemp.getNext();
            }

            nodo.setNext(nodo);

            /*Boolean trovato = false;

            while(!trovato){

                if(nodoTemp.getNext() == null){
                    nodoTemp.setNext(nodo);
                    //head = nodoTemp;
                    trovato = true;
                }
                else{
                    nodoTemp = nodoTemp.getNext();

                }*/
        }
    }

    public String visualizzaLista(){

        Nodo nodoTemp = new Nodo();
        nodoTemp = head;

        String output = "";

        if(head == null)
            return output;

        for(int i = 1;  nodoTemp != null; i++){
            output += "\nNodo " + i + " : " + nodoTemp.getInfo();
            nodoTemp = nodoTemp.getNext();
        }

        return output;
    }

    public void eliminaTesta(){

        if(head == null)
            return;

        Nodo aux = new Nodo();
        aux = head;
        head = head.getNext();
        aux.setNext(null);
        System.gc();
    }

    public void eliminaCoda(){

        Nodo nodoTemp = new Nodo();
        nodoTemp = head;

        if(head == null)
            return;

        if(head.getNext() == null) {
            head = null;
            return;
        }

        while(nodoTemp.getNext().getNext() != null){
            nodoTemp = nodoTemp.getNext();
        }

        nodoTemp.getNext().setNext(null);
        System.gc();
    }

    public int contaLunghezza(){

        Nodo nodoTemp = new Nodo();
        nodoTemp = head;
        int size = 0;

        while(nodoTemp != null){

            size++;
            nodoTemp = nodoTemp.getNext();

        }

        return size;
    }

    public double media() {

        Nodo nodoTemp = new Nodo();
        nodoTemp = head;
        int somma = 0;
        double media;

        while(nodoTemp != null) {
            somma+= nodoTemp.getInfo();
            nodoTemp = nodoTemp.getNext();
        }

        return (double)somma/contaLunghezza();

    }

    public boolean controlloPresenzaElemento(int valore){

        Nodo nodoTemp = new Nodo();
        nodoTemp = head;

        if(head == null)
            return true;

        while(nodoTemp != null){
            if(nodoTemp.getInfo() == valore){
                return true;
            }
            else
                nodoTemp = nodoTemp.getNext();
        }

        return false;

    }

    public int contaElementiInferioriMedia(){

        Nodo nodoTemp = new Nodo();
        nodoTemp = head;
        int conta = 0;

        if(head == null)
            return conta;

        while(nodoTemp != null){
            if(nodoTemp.getInfo() < media())
                conta++;

            nodoTemp = nodoTemp.getNext();
        }

        return conta;
    }

    public boolean verificaElementiRipetuti(){

        Nodo nodoTemp = new Nodo();
        Nodo nodoTemp2 = new Nodo();
        nodoTemp = head;

        if(head == null)
            return false;

        while(nodoTemp != null){

            nodoTemp2 = nodoTemp.getNext();

            while(nodoTemp2 != null){

                if(nodoTemp.getInfo() == nodoTemp2.getInfo())
                    return true;

                else
                    nodoTemp2 = nodoTemp2.getNext();
            }

            nodoTemp = nodoTemp.getNext();
        }

        return false;
    }

    public boolean inserimentoTestaConControllo(int valore){

        if(!controlloPresenzaElemento(valore)){
            addFront(valore);
            return true;
        }

        return false;

    }

    public boolean inserimentoCodaConControllo(int valore){

        if(!controlloPresenzaElemento(valore)){
            addBehind(valore);
            return true;
        }

        return false;
    }
}

package gestionelista;

public class Lista {
    private Nodo head;

    public Lista(){
        head = null;
    }

    //Metodo che passato un valore, lo aggiunge in testa alla lista
    public void addFront(int valore){
        Nodo nodo = new Nodo(valore);

        if(head == null)
            head = nodo;

        else { //Lista non vuota
            nodo.setNext(head);
            head = nodo;
        }
    }

    //Metodo che passato un valore, lo aggiunge in coda alla lista
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

            nodoTemp.setNext(nodo);
        }
    }

    //Metodo che stampa il contenuto della lista
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


    //Metodo che, passato l'indice di un nodo della lista, decide la funzione da richimare per eliminare il nodo
    //Nel caso in cui l'indice coincida con la testa o la coda della lista richiamo della funzioni specifiche
    public void elimina(int index){

        if(index == 0) {
            eliminaTesta();
            return;
        }
        if(index == contaLunghezza() - 1){
            eliminaCoda();
            return;
        }
        else{
            eliminaIndice(index);
        }

    }

    //Metodo che passato l'indice di un nodo lo elimina
    public void eliminaIndice(int index){

        int i = 0;

        Nodo aux = new Nodo();
        aux = head;

        while(i < (index -1 )){
            aux = aux.getNext();
            i++;
        }

        aux.setNext(aux.getNext().getNext());
        System.gc();

    }

    //Metodo che elimina la testa della lista
    public void eliminaTesta(){

        if(head == null)
            return;

        Nodo aux = new Nodo();
        aux = head;
        head = head.getNext();
        aux.setNext(null);
        System.gc();
    }

    //Metodo che elimina la coda della lista
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

        nodoTemp.getNext();
        nodoTemp.setNext(null);
        System.gc();
    }

    //Metodo che conta la lunghezza della lista
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

    //Metodo che calcola la media della lista
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

    //Metodo che, passato un valore, controlla se è già presente all'interno della lista
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

    //Metodo che onta il numero di elementi presenti all'interno della lista che sono inferiori alla media
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

    //Metodo che verifica se ci sono elementi ripetuti all'interno della lista
    //ATTUALMENTE NON IN USO
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


    //Metodi utilizzati per l'inserimento condizionale
    //Passato un valore controllano che non sia già presente all'interno della lista e in tal caso procedono con l'inserimento
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

    public Nodo getHead(){
        return head;
    }
}

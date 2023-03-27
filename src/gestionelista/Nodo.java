package gestionelista;

public class Nodo {

    private int info;
    private Nodo next;

    public Nodo(){}

    public Nodo(int info){
        this.info = info;
        this.next = null;
    }

    public void setInfo(int info){
        this.info = info;
    }

    public void setNext(Nodo next){
        this.next = next;
    }

    public int getInfo(){
        return info;
    }

    public Nodo getNext(){
        return next;
    }
}

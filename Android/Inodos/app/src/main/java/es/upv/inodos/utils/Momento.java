package es.upv.inodos.utils;


public class Momento {
    private long momento;

    public Momento(){
        momento = 0;
    }

    public long getMomento() {
        // devuelve el tiempo en formato unix
        return System.currentTimeMillis()/1000;
    }
}

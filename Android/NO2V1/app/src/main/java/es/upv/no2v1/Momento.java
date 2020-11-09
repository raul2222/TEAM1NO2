package es.upv.no2v1;


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

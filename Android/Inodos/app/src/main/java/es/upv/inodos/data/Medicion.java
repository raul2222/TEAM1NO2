package es.upv.inodos.data;

public class Medicion {

    private int contador;
    private String idsen;
    private String lat;
    private String longi;
    private String accuracy;
    private String valor;
    private String momento;
    private String temperatura;
    private String distancia;
    private String bat;
    private String batVolt;


    public String getBatVolt() {
        return batVolt;
    }

    public void setBatVolt(String batVolt) {
        this.batVolt = batVolt;
    }

    public String getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(String accuracy) {
        this.accuracy = accuracy;
    }

    public int getContador() { return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }

    public String getIdsen() {
        return idsen;
    }

    public void setIdsen(String idsen) {
        this.idsen = idsen;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getBat() {
        return bat;
    }

    public void setBat(String lat) {
        this.bat = lat;
    }

    public String getLongi() {
        return longi;
    }

    public void setLongi(String longi) {
        this.longi = longi;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getMomento() {
        return momento;
    }

    public void setMomento(String valor) {
        this.momento = valor;
    }

    public String getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }

    public String getDistancia() {
        return distancia;
    }

    public void setDistancia(String distancia) {
        this.distancia = distancia;
    }

    public Medicion(){
        this.contador = 0;
        this.idsen = "";
        this.lat = "";
        this.longi = "";
        this.valor = "";
        this.momento = "";
        this.bat = "";
        this.temperatura = "";
        this.distancia ="";
        this.accuracy = "";
    }

    public Medicion(String  idsen, String lat, String longi, String valor, String moment, String bat) {
        this.idsen = idsen;
        this.lat = lat;
        this.longi = longi;
        this.valor = valor;
        this.momento = moment;
        this.bat = bat;
    }

    public void borrarMedicion(){
        this.idsen = "";
        //this.lat = "";
        //this.longi = "";
        this.valor = "";
        this.momento = "";
        this.contador = 0;
        this.temperatura = "";
        this.bat = "";
        this.distancia = "";
        //this.accuracy = "";
    }

}

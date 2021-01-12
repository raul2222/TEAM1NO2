package es.upv.no2v1.Models;

public class Medicion {
    private String idsen;
    private String lat;
    private String longi;
    private String valor;
    private String temperature;
    private String momento;
    private String bat;

    public String getTemperature() {        return temperature;    }

    public void setTemperature(String temperature) {        this.temperature = temperature;    }

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

    public Medicion(){
        this.idsen = "";
        this.lat = "";
        this.longi = "";
        this.valor = "";
        this.momento = "";
        this.bat = "";
        this.temperature="";
    }

    public Medicion(String  idsen, String lat, String longi, String valor, String moment, String bat, String temperature) {
        this.idsen = idsen;
        this.lat = lat;
        this.longi = longi;
        this.valor = valor;
        this.momento = moment;
        this.bat = bat;
        this.temperature = temperature;
    }

    public void borrarMedicion(){
        this.idsen = "";
        //this.lat = "";
        //this.longi = "";
        this.valor = "";
        this.momento = "";
        this.bat = "";
        this.temperature = "";
    }

}

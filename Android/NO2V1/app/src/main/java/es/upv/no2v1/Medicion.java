package es.upv.no2v1;

public class Medicion {
    private String idsen;
    private String lat;
    private  String longi;
    private String valor;
    private String momento;

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
    }

    public Medicion(String  idsen, String lat, String longi, String valor, String moment) {
        this.idsen = idsen;
        this.lat = lat;
        this.longi = longi;
        this.valor = valor;
        this.momento = moment;
    }

    public void borrarMedicion(){
        this.idsen = "";
        //this.lat = "";
        //this.longi = "";
        this.valor = "";
        this.momento = "";
    }

}

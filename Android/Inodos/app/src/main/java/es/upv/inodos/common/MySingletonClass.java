package es.upv.inodos.common;

public class MySingletonClass {
    private static MySingletonClass instance;

    public static MySingletonClass getInstance() {
        if (instance == null)
            instance = new MySingletonClass();
        return instance;
    }

    private MySingletonClass() {
    }

    private String mensajeNotificacion;
    private String mensajeNotificacionTitle;

    public String getMensajeNotificacionTitle() {
        return mensajeNotificacionTitle;
    }

    public void setMensajeNotificacionTitle(String mensajeNotificacionTitle) {
        this.mensajeNotificacionTitle = mensajeNotificacionTitle;
    }

    public String getmensajeNotificacion() {
        return mensajeNotificacion;
    }

    public void setmensajeNotificacion(String value) {
        this.mensajeNotificacion = value;
    }
}

package es.upv.inodos.common;

public class Constants {
    public static final String TAG = "Inodos";
    public static final String MONITOR_SERVICE_DESCRIPTION = "Servicios habilitados correctamente";
    public static final int TIMER_DELAY = 2000;
    public static final int TIMER_INTERVAL = 1000*30*1;
    public static final String CHANNEL_ID = "HeartServiceChannel";
    public static final String name_notification = "Inodos";


    //OJO ESTO TIENE QUE LEERSE DE LA BASE DE DATOS EL SERIAL DEL SENSOR
    // TODO
    public static final String SerialNumber = "F5:81:CF:8C:08:78";

    public static final int Interval_Lectura_GPS = 1;
    public static final int distancia_GPS = 15;
    public static final int Min_Radius_GPS_Accuracy = 25;


    public static final int Tiempo_Envios = 60;

    public static final String BROADCAST_DETECTED_ACTIVITY = "activity_intent";

    public static final long DETECTION_INTERVAL_IN_MILLISECONDS = 30 * 1000;

    public static final int CONFIDENCE = 70;

    public static final int IN_VEHICLE = 0;
    public static final int ON_BICYCLE = 1;
    public static final int ON_FOOT = 2;
    public static final int STILL = 3;
    public static final int UNKNOWN = 4;
    public static final int TILTING = 5;
    public static final int WALKING = 7;
    public static final int RUNNING = 8;



}

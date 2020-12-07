package es.upv.inodos.workers;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import es.upv.inodos.R;
import es.upv.inodos.activities.MainActivity;
import static es.upv.inodos.common.Constants.CHANNEL_ID;
import static es.upv.inodos.common.Constants.name_notification;
// *********************************************************
//  RAUL SANTOS LOPEZ       07/12/2020
// *********************************************************
public class NotificationWorker extends Worker {
    public NotificationWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }
    // Se define el parámetro de entrada
    public static final String text = "";

    @NonNull
    @Override
    public Result doWork() {
        // Extración de la información:
        String text = getInputData().getString("text");
        if(text != "") {
            sendNotification(text);
        }
        return Result.success();
    }
    public  void sendNotification(String text){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),0,intent,0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                .setContentTitle(name_notification)
                .setContentText(String.valueOf(text))
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
        notificationManagerCompat.notify(1,builder.build());
    }

}

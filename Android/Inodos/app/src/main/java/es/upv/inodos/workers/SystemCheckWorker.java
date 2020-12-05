package es.upv.inodos.workers;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import es.upv.inodos.common.Constants;
import es.upv.inodos.utils.SystemUtils;


public class SystemCheckWorker extends Worker {

    public SystemCheckWorker(
            @NonNull Context context,
            @NonNull WorkerParameters params) {
        super(context, params);
    }

    @Override
    public Result doWork() {
        Log.d(Constants.TAG, "--> SystemCheckWorker");

        SystemUtils.launchMonitorService(getApplicationContext());
        return Result.success();
    }
}

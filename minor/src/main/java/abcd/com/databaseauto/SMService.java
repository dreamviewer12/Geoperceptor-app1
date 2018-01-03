package abcd.com.databaseauto;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Nitisha Agarwal on 26-11-2017.
 */

public class SMService extends Service
{

    @Override
    public void onCreate() {
        Log.d("codekamp","First Time enter here");
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("codekamp", "Sound Mode Changed");
        Toast.makeText(this, "Sound Mode is going to be changed", Toast.LENGTH_LONG).show();
        super.onCreate();
        final AudioManager audioManager;
        audioManager = (AudioManager) getSystemService(this.AUDIO_SERVICE);
        audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
        return START_NOT_STICKY;
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}


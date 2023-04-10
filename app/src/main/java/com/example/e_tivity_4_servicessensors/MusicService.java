// Oisin O'Sullivan
// 21304971

package com.example.e_tivity_4_servicessensors;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MusicService extends Service{
    MediaPlayer player;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate(){
        Toast.makeText(this, "Service Made Succesfully!", Toast.LENGTH_LONG).show();

        player = MediaPlayer.create(getApplicationContext(), R.raw.music);
        player.setLooping(false);
    }
    @Override
    public void onStart(Intent intent, int startId){
        Toast.makeText(this, "Service has Started!", Toast.LENGTH_LONG).show();
        player.start();
    }
    @Override
        public void onDestroy(){
            Toast.makeText(this, "Service Stopped", Toast.LENGTH_LONG).show();
            player.stop();
        }
}

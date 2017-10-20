package com.raybritton.playpausetoggle;

import android.media.AudioManager;
import android.os.SystemClock;
import android.view.KeyEvent;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

public class ListenerService extends WearableListenerService {

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        AudioManager am = (AudioManager) getSystemService(AUDIO_SERVICE);
        sendMediaButton(am, KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE);
    }
    private void sendMediaButton(AudioManager audioManager, int keyCode) {

        long eventtime = SystemClock.uptimeMillis() - 1;
        KeyEvent downEvent = new KeyEvent(eventtime, eventtime, KeyEvent.ACTION_DOWN, keyCode, 0);
        audioManager.dispatchMediaKeyEvent(downEvent);

        eventtime++;
        KeyEvent upEvent = new KeyEvent(eventtime,eventtime,KeyEvent.ACTION_UP,keyCode, 0);
        audioManager.dispatchMediaKeyEvent(upEvent);
    }
}
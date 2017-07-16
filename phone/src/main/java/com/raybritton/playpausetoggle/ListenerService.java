package com.raybritton.playpausetoggle;

import android.media.AudioManager;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

public class ListenerService extends WearableListenerService implements AudioManager.OnAudioFocusChangeListener {

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        AudioManager am = (AudioManager) getSystemService(AUDIO_SERVICE);
        if (am.isMusicActive()) {
            am.requestAudioFocus(this, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
        } else {
            am.abandonAudioFocus(this);
        }
    }

    @Override
    public void onAudioFocusChange(int i) {

    }
}
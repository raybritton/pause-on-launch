package com.raybritton.playpausetoggle;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.NodeApi;
import com.google.android.gms.wearable.Wearable;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ToggleActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final GoogleApiClient client = new GoogleApiClient.Builder(this)
                .addApi(Wearable.API)
                .build();
        new Thread(new Runnable() {
            @Override
            public void run() {
                client.blockingConnect(30000, TimeUnit.MILLISECONDS);
                NodeApi.GetConnectedNodesResult result =
                        Wearable.NodeApi.getConnectedNodes(client).await();
                List<Node> nodes = result.getNodes();
                if (nodes.size() > 0) {
                    String nodeId = nodes.get(0).getId();
                    Wearable.MessageApi.sendMessage(client, nodeId, Uri.fromParts("wear", "toggle_playback", null).toString(), null);
                }
                client.disconnect();
            }
        }).start();
        finish();
    }

}

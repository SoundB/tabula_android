package com.jakocrew.tabula.services;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jakocrew.tabula.R;
import com.jakocrew.tabula.util.Util;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;


public class TabulaService extends Service {
    private static final String ServicePackage = Util.PACKAGE_NAME + ".TabulaService";

    // 브로드캐스트 퍼미션
    public static final String PERMISSION_FOR_BROADCAST = "com.jakocrew.tabula.services.BROADCAST_ALLOW";
    public static final String ACTION_ROOM_LIST = ServicePackage + "ACTION_ROOM_LIST";
    private WebSocketClient mWebSocketClient;

    public TabulaService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("TabulaService","onCreate");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(TabulaService.ACTION_ROOM_LIST);
        registerReceiver(actionRecevier, intentFilter, PERMISSION_FOR_BROADCAST, null);

//        connectWebSocket();
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(actionRecevier);
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("TabulaService","onStartCommand");
        if(ACTION_ROOM_LIST.equalsIgnoreCase(intent.getAction())){
            Log.d("TabulaService","ACTION_ROOM_LIST");
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.d("TabulaService","onBind");
        throw new UnsupportedOperationException("Not yet implemented");
    }


    private BroadcastReceiver actionRecevier = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("TabulaService","onReceive");
            if(ACTION_ROOM_LIST.equalsIgnoreCase(intent.getAction())){
                Log.d("onReceive","ACTION_ROOM_LIST");

                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("scene","lounge");
                    jsonObject.put("command","roomList");
                    jsonObject.put("id","민시기");

                    mWebSocketClient.send(jsonObject.toString());

                    Log.d("send Message ",jsonObject.toString());
                }catch (Exception e){

                }

            }
        }
    };

    private void connectWebSocket() {
        URI uri;
        try {
            uri = new URI("ws://10.201.2.138:8765");
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return;
        }

        mWebSocketClient = new WebSocketClient(uri) {
            @Override
            public void onOpen(ServerHandshake serverHandshake) {
                Log.i("Websocket", "Opened");
                mWebSocketClient.send("Hello from " + Build.MANUFACTURER + " " + Build.MODEL);
            }

            @Override
            public void onMessage(String s) {
                final String message = s;
                Log.d("onMessasge ",message);
            }

            @Override
            public void onClose(int i, String s, boolean b) {
                Log.i("Websocket", "Closed " + s);
            }

            @Override
            public void onError(Exception e) {
                Log.i("Websocket", "Error " + e.getMessage());
            }
        };
        mWebSocketClient.connect();
    }

}

package com.jakocrew.tabula;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.jakocrew.tabula.services.TabulaService;


public class SplashActivity extends Activity {
    /** 로딩 화면이 떠있는 시간(밀리초단위)  **/
    private final int SPLASH_DISPLAY_LENGTH = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Intent intent = new Intent(this, TabulaService.class);
        startService(intent);

        /* SPLASH_DISPLAY_LENGTH 뒤에 메뉴 액티비티를 실행시키고 종료한다.*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {

                LayoutInflater layoutInflaterAndroid = LayoutInflater.from(SplashActivity.this);
                View mView = layoutInflaterAndroid.inflate(R.layout.dialog_user_input, null);
                AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(SplashActivity.this);
                alertDialogBuilderUserInput.setView(mView);

                final EditText userInputDialogEditText = (EditText) mView.findViewById(R.id.userInputDialog);
                userInputDialogEditText.setText(Config.getInstance().getNickName());
                alertDialogBuilderUserInput.setCancelable(false);
                alertDialogBuilderUserInput.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {

                        Config.getInstance().setNickName(userInputDialogEditText.getText().toString());
                                 /* 메뉴액티비티를 실행하고 로딩화면을 죽인다.*/
                        Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                        SplashActivity.this.startActivity(mainIntent);
                        SplashActivity.this.finish();
                    }
                });

                AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
                alertDialogAndroid.show();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}

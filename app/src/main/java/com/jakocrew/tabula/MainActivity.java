package com.jakocrew.tabula;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.jakocrew.tabula.data.RoomInfo;
import com.jakocrew.tabula.list.RoomRecyclerView;
import com.jakocrew.tabula.services.TabulaService;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements View.OnClickListener{
    RoomRecyclerView listView;
    FloatingActionButton btn_create_room;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setDrawerLayout(this);


        initUI();

        Intent intent = new Intent(this,TabulaService.class);
        intent.setAction(TabulaService.ACTION_ROOM_LIST);
        startService(intent);
    }

    public void initUI() {
        listView = (RoomRecyclerView) findViewById(R.id.list_room);

        ArrayList<RoomInfo> arrayList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            arrayList.add(new RoomInfo());
        }

        listView.setListData(arrayList);


        btn_create_room = (FloatingActionButton)findViewById(R.id.btn_create_room);
        btn_create_room.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_create_room:
                //create room
                actionCreateRoom();
                break;

            default:
        }
    }



    private void actionCreateRoom() {
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(MainActivity.this);
        View mView = layoutInflaterAndroid.inflate(R.layout.dialog_user_input, null);
        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilderUserInput.setView(mView);

        TextView dialogTitle = (TextView) mView.findViewById(R.id.dialogTitle);
        dialogTitle.setText("방만들기");


        final EditText userInputDialogEditText = (EditText) mView.findViewById(R.id.userInputDialog);
        userInputDialogEditText.setHint("방제목을 입력해주세요.");

        alertDialogBuilderUserInput.setCancelable(false);
        alertDialogBuilderUserInput.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogBox, int id) {

                Intent intent = new Intent(MainActivity.this,TabulaService.class);
                intent.putExtra("ROOM_NAME",userInputDialogEditText.getText().toString());
                intent.setAction(TabulaService.ACTION_CREATE_ROOM);
                MainActivity.this.startService(intent);
            }
        });

        AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
        alertDialogAndroid.show();
    }
}

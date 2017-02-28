package com.jakocrew.tabula;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.jakocrew.tabula.data.RoomInfo;
import com.jakocrew.tabula.list.RoomRecyclerView;
import com.jakocrew.tabula.services.TabulaService;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {
    RoomRecyclerView listView;

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


}

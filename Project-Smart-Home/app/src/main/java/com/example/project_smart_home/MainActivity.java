package com.example.project_smart_home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.project_smart_home.ui.AI.AIActivity;
import com.example.project_smart_home.ui.OptionActivity;
import com.example.project_smart_home.ui.member.MemberActivity;
import com.example.project_smart_home.ui.message.MessageActivity;
import com.example.project_smart_home.ui.room.RoomListActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Button;
import android.widget.ImageButton;


public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        NavigationView.OnNavigationItemSelectedListener {
    private Button nav_room_btn, nav_member_btn, nav_ai_btn;
    private ImageButton option_btn;

    NavigationView mNavigationView;
    DrawerLayout mDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_24px);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mDrawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                mDrawer,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(toggle);
        mNavigationView = findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);

        btnSetting();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return super.onSupportNavigateUp();
    }

    public static Intent getStartIntent(Context context){
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    public void onClick(View view) {
        Intent next = null;
        switch (view.getId()){
            case R.id.nav_room_btn:
                System.out.println(view.getId());
                System.out.println("Room");
                next = RoomListActivity.getStartIntent(this);
                break;
            case R.id.nav_member_btn:
                next = MemberActivity.getStartIntent(this);
                break;
            case R.id.nav_ai_btn:
                next = AIActivity.getStartIntent(this);
                break;
            case R.id.option_btn:
                next = OptionActivity.getStartIntent(this);
                break;
        }
        if (next != null) startNextActivity(next);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Intent next = null;
        switch (menuItem.getItemId()){
            case R.id.nav_message:
                System.out.println(menuItem.getItemId());
                System.out.println("message");
                next = new Intent(this, MessageActivity.class);
                startNextActivity(next);
                break;
            case R.id.nav_description:
                break;
            case R.id.nav_notification:
                break;
        }

        return false;
    }

    private void startNextActivity(Intent n){
        startActivity(n);
        finish();
    }

    private void btnSetting(){
        View hView = mNavigationView.getHeaderView(0);
        nav_room_btn = hView.findViewById(R.id.nav_room_btn);
        nav_room_btn.setOnClickListener(this);
        nav_member_btn = hView.findViewById(R.id.nav_member_btn);
        nav_member_btn.setOnClickListener(this);
        nav_ai_btn = hView.findViewById(R.id.nav_ai_btn);
        nav_ai_btn.setOnClickListener(this);
        option_btn = hView.findViewById(R.id.option_btn);
        option_btn.setOnClickListener(this);
    }
}

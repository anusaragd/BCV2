package com.example.anusara.bcv2.Member;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.anusara.bcv2.About.AboutActivity;
import com.example.anusara.bcv2.Alarm.SetAlarmActivity;
import com.example.anusara.bcv2.GPS.GPSActivity;
import com.example.anusara.bcv2.GPS.MapsActivity;
import com.example.anusara.bcv2.HelpActivity;
import com.example.anusara.bcv2.MainActivity;
import com.example.anusara.bcv2.Member.MPost.MPostActivity;
import com.example.anusara.bcv2.Member.MTest.MTestActivity;
import com.example.anusara.bcv2.Member.Questionnaire.MQuestionActivity;
import com.example.anusara.bcv2.R;
import com.example.anusara.bcv2.RegisterActivity;
import com.example.anusara.bcv2.Touch.IntroTouchActivity;
import com.example.anusara.bcv2.Touch.TouchActivity;

public class MembermainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String user;
    TextView usernameit ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_membermain);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        user = getIntent().getStringExtra("user");
        Log.e( "HomeActivity: ", user + "");

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.membermain, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.about) {
            Intent intent = new Intent(getApplicationContext(), AboutActivity.class);
            startActivity(intent);
            // Handle the camera action
        } else if (id == R.id.questionnaire) {
            Intent intent = new Intent(getApplicationContext(), MQuestionActivity.class);
            intent.putExtra("username",user);
            startActivity(intent);

        } else if (id == R.id.test) {
            Intent intent = new Intent(getApplicationContext(), MTestActivity.class);
            intent.putExtra("username",user);
            startActivity(intent);

        } else if (id == R.id.alarm) {
            Intent intent = new Intent(getApplicationContext(), SetAlarmActivity.class);
            startActivity(intent);

        } else if (id == R.id.gps) {
            Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
            startActivity(intent);

        } else {
            if (id == R.id.Posts) {
                Intent intent = new Intent(getApplicationContext(), MPostActivity.class);
                intent.putExtra("username", user);
                startActivity(intent);

//            } else if (id == R.id.touch) {
//                Intent intent = new Intent(getApplicationContext(), IntroTouchActivity.class);
//                startActivity(intent);

            } else if (id == R.id.help) {
                Intent intent = new Intent(getApplicationContext(), HelpActivity.class);
                startActivity(intent);

            } else if (id == R.id.nav_send) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("username", user);
                startActivity(intent);
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

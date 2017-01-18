package com.example.anusara.bcv2.Member.MTest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.anusara.bcv2.R;

public class MTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mtest);

        final Button startbutt = (Button) findViewById(R.id.startbutt);
        startbutt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (v.getId() == R.id.startbutt) {
                    Intent intent = new Intent(getApplicationContext(), MT1Activity.class);
                    startActivity(intent);
                    //Intent intent = new Intent(getApplicationContext(), homeActivity.class);
                    //startActivity(intent);
                }

            }
        });
    }
}

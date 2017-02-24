package com.example.anusara.bcv2.Touch;

import android.content.Intent;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.anusara.bcv2.R;

import java.util.ArrayList;

public class TouchActivity extends AppCompatActivity {

    private GestureLibrary gLib;
    private static final String TAG = "gesture";

    int sum1,sum2,sum3; //คำตอบ
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch);

        gLib = GestureLibraries.fromRawResource(this, R.raw.gestures);
        if (!gLib.load()) {
            Log.w(TAG, "could not load gesture library");
            finish();
        }

        GestureOverlayView gestures = (GestureOverlayView) findViewById(R.id.gestures);
        gestures.addOnGesturePerformedListener(handleGestureListener);


        next = (Button) findViewById(R.id.button6);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (v.getId() == R.id.button6) {
                    Intent intent = new Intent(getApplicationContext(), finishTouchActivity.class);
                    intent.putExtra("sum1", sum1);
                    intent.putExtra("sum2", sum2);
                    intent.putExtra("sum3", sum3);
                    startActivity(intent);

                }

            }
        });
    }

    /**
     * our gesture listener
     */
    private GestureOverlayView.OnGesturePerformedListener handleGestureListener = new GestureOverlayView.OnGesturePerformedListener() {
        @Override
        public void onGesturePerformed(GestureOverlayView gestureView,
                                       Gesture gesture) {

            ArrayList<Prediction> predictions = gLib.recognize(gesture);

            // one prediction needed
            if (predictions.size() > 0) {
                Prediction prediction = predictions.get(0);
                // checking prediction
                if (prediction.score > 1.0) {
                    // and action
                    Toast.makeText(TouchActivity.this,"Correct",
                            Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(TouchActivity.this,"InCorrect",
                            Toast.LENGTH_SHORT).show();
                }
            }

        }
    };
}

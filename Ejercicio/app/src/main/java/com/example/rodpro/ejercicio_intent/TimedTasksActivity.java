package com.example.rodpro.ejercicio_intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class TimedTasksActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "TIMED_TASKS";
    private static final long UPDATE_INTERVAL = 3000;

    private int counter;

    private Button start;
    private View image;
    private Timer timer = new Timer();
    private TimerTask timerTask;
    private RotateAnimation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timed_tasks);
        setUpViews();
    }

    private void setUpViews() {
        animation = new RotateAnimation(0, 360,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);

        start = findViewById(R.id.rotate_button);
        image = findViewById(R.id.image_launch);

        start.setOnClickListener(this);

        timerTask = new TimerTask() {
            @Override
            public void run() {
                Log.d(TAG,"TimerTask, account: " + ++counter);
                rotateLaunchImage(image);
            }
        };
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rotate_button:
                try {
                    timer.scheduleAtFixedRate(timerTask,0, UPDATE_INTERVAL);
                }catch (Exception e){
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void rotateLaunchImage(final View view) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                animation.setDuration(1000);
                animation.setRepeatCount(Animation.ABSOLUTE);
                animation.setRepeatMode(Animation.ZORDER_NORMAL);
                view.startAnimation(animation);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        timer.cancel();
        timer.purge();
    }
}

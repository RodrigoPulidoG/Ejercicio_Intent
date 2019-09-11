package com.example.rodpro.ejercicio_intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class TimedTasksActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "TIMED_TASKS";
    private static final long UPDATE_INTERVAL = 3000;

    private int counter;

    private Button start;
    private Button substract;
    private View image;
    private Timer timer = new Timer();
    private TimerTask timerTask;
    private RotateAnimation animation;
    private TextView currentDate;
    private TextView newDateParce;
    private Date parseDate;
    private EditText hoursSub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timed_tasks);
        setUpViews();
        takeCurrentData();
    }

    private void setUpViews() {
        animation = new RotateAnimation(0, 360,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);

        start = findViewById(R.id.rotate_button);
        substract = findViewById(R.id.subtract);
        image = findViewById(R.id.image_launch);
        currentDate = findViewById(R.id.current_date);
        hoursSub = findViewById(R.id.hours);
        newDateParce = findViewById(R.id.new_date);

        start.setOnClickListener(this);
        substract.setOnClickListener(this);

        timerTask = new TimerTask() {
            @Override
            public void run() {
                Log.d(TAG,"TimerTask, account: " + ++counter);
                rotateLaunchImage(image);
            }
        };
    }

    private void takeCurrentData() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.getDefault());
        Date date = new Date();
        String time = dateFormat.format(date);
        try {
            parseDate = dateFormat.parse(time);
            Log.d(TAG,"takeCurrentData, Current Date: " + parseDate);
            currentDate.setText(changeDateFormat(parseDate));
        } catch (ParseException e) {
            e.printStackTrace();
            Log.e(TAG,"takeCurrentData, ParseException: " + e);
        }


    }

    private void subtractTime() {
        int horas = Integer.parseInt(String.valueOf(hoursSub.getText()));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(parseDate);
        calendar.add(Calendar.HOUR,horas*(-1));
        Date newDate = calendar.getTime();
        newDateParce.setText(changeDateFormat(newDate));
        Log.d(TAG,"subtractTime, New Date: ");
    }

    private String changeDateFormat(Date date) {
        String year = (String) android.text.format.DateFormat.format("yyyy", date);
        String month = (String) android.text.format.DateFormat.format("MMM", date);
        month = month.substring(0, 1).toUpperCase() + month.substring(1);
        month = month.replace(".","").trim();
        String day = (String) android.text.format.DateFormat.format("dd", date);
        String currentHourString = (String) android.text.format.DateFormat.format("h:mm a", date);
        String currentDateString = year + ", " + month + " " + day + "_";
        String dateComplete = currentDateString +" hour: "+ currentHourString;
        return dateComplete;
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
            case R.id.subtract:
                subtractTime();
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

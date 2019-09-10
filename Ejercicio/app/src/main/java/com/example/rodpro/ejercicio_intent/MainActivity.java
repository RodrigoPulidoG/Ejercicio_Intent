package com.example.rodpro.ejercicio_intent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn;
    private Button tasks;
    private Button rotateView;
    private RotateAnimation animation;
    private final String GREETER = "Hello from the oder side!";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpViews();
    }

    private void setUpViews() {
        animation = new RotateAnimation(0, 360,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        tasks = findViewById(R.id.buttonTasks);
        rotateView = findViewById(R.id.rotate);
        btn = findViewById(R.id.buttonMain);

        tasks.setOnClickListener(this);
        rotateView.setOnClickListener(this);
        btn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonMain:
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("gretter",GREETER);
                startActivity(intent);
                break;
            case R.id.rotate:
                View imageView = findViewById(R.id.image);
                rotateView(imageView);
                break;
            case R.id.buttonTasks:
                Toast.makeText(this, "Go to Timed Tasks", Toast.LENGTH_SHORT).show();
                break;
        }

    }

    private void rotateView(View view) {
        animation.setDuration(2000);
        animation.setRepeatCount(Animation.ABSOLUTE);
        animation.setRepeatMode(Animation.ZORDER_NORMAL);
        view.startAnimation(animation);
    }
}

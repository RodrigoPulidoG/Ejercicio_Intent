package com.example.rodpro.ejercicio_intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textView = (TextView)findViewById(R.id.textViewMain);

        // TOMAR LOS DATOS DEL INTENT
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            String gretter = bundle.getString("gretter");
            Toast.makeText(SecondActivity.this, gretter,Toast.LENGTH_SHORT).show();
            textView.setText(gretter);
        }else {
            Toast.makeText(SecondActivity.this, "It is empty",Toast.LENGTH_SHORT).show();
        }

    }
}

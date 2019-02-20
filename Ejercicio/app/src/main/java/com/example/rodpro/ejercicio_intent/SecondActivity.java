package com.example.rodpro.ejercicio_intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    private TextView textView;
    private Button btsh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textView = (TextView)findViewById(R.id.textViewMain);
        btsh = (Button)findViewById(R.id.buttonSharing);

        // TOMAR LOS DATOS DEL INTENT
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            String gretter = bundle.getString("gretter");
            Toast.makeText(SecondActivity.this, gretter,Toast.LENGTH_SHORT).show();
            textView.setText(gretter);
        }else {
            Toast.makeText(SecondActivity.this, "It is empty",Toast.LENGTH_SHORT).show();
        }

        btsh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                startActivity(intent);
            }
        });

    }
}

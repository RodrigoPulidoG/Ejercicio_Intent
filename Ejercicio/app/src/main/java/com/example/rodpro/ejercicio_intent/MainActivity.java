package com.example.rodpro.ejercicio_intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btn;
    private final String GREETER = "Hello from the oder side!";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button)findViewById(R.id.buttonMain);

        // FORMA ADECUADA DE USAR UN "BUTTON"
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // ACCEDER AL SEGUNDO ACTIVITY Y MANDARLE UN STRING
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("gretter",GREETER);
                startActivity(intent);

            }
        });
    }
}

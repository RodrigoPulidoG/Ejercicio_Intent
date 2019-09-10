package com.example.rodpro.ejercicio_intent;

import android.content.Intent;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    private ProgressBar pbA, pbB;
    private CheckBox cb1, cb2;
    private DrawerLayout drawerLayout;

    private Button btn;
    private final String GREETER = "Hello from the oder side!";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button)findViewById(R.id.buttonMain);
        cb1 = findViewById(R.id.cBoxA);
        cb2 = findViewById(R.id.cBoxB);
        pbA = findViewById(R.id.login_spin_kitA);
        pbB = findViewById(R.id.login_spin_kitB);
        drawerLayout = findViewById(R.id.drawerL);

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
    public void onStart(){
        super.onStart();
        // (MOSTRAR / OCULTAR) LOS PROGRES BAR CON LOS CHECK BOX DESDE EL DRAWER LAYOUT
        checkStatusBox();
        showProgresBar();

    }

    private void checkStatusBox() {
        /*
        pbA.setVisibility(View.INVISIBLE);
        pbB.setVisibility(View.INVISIBLE);

        if (drawerLayout.getDrawableState(cb1.isChecked())){
            pbA.setVisibility(View.VISIBLE);
        }if (cb2.isChecked())
            pbB.setVisibility(View.VISIBLE);
        */
    }

    private void showProgresBar(){
        /*
        cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    pbA.setVisibility(View.VISIBLE);
                }else {
                    pbA.setVisibility(View.INVISIBLE);
                }
            }
        });

        cb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (cb2.isChecked()){
                    pbB.setVisibility(View.VISIBLE);
                }else {
                    pbB.setVisibility(View.INVISIBLE);
                }
            }
        });
        */
    }
}

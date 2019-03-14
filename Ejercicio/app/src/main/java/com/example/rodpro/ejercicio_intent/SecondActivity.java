package com.example.rodpro.ejercicio_intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textView;
    private Button btsh;
    private Button button;
    private ProgressBar progressBar, pbA, pbB;
    private CheckBox cb1, cb2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textView = findViewById(R.id.textViewMain);
        btsh = findViewById(R.id.buttonSharing);
        button = findViewById(R.id.b_visual);
        progressBar = findViewById(R.id.login_spin_kit);
        pbA = findViewById(R.id.login_spin_kitA);
        pbB = findViewById(R.id.login_spin_kitB);
        cb1 = findViewById(R.id.checkBox);
        cb2 = findViewById(R.id.checkBox2);


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

    public void onStart() {
        super.onStart();
        hideProgressDialog();
        button.setOnClickListener(this);

        // (MOSTRAR / OCULTAR) LOS PROGRES BAR CON LOS CHECK BOX
        //checkStatusBox();
        //showProgresBar();

    }

    private void checkStatusBox() {
        pbA.setVisibility(View.INVISIBLE);
        pbB.setVisibility(View.INVISIBLE);
        if (cb1.isChecked()){
            pbA.setVisibility(View.VISIBLE);
        }if (cb2.isChecked())
            pbB.setVisibility(View.VISIBLE);
    }

    private void showProgresBar() {
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


        /*
        }if (cb2.isChecked()){
            pbB.setVisibility(View.VISIBLE);
        }
        */
    }

    private void hideProgressDialog() {
        progressBar.setVisibility(View.INVISIBLE);
        button.setVisibility(View.VISIBLE);
        button.setEnabled(true);
    }

    private void showProgressDialog() {
        progressBar.setVisibility(View.VISIBLE);
        button.setVisibility(View.INVISIBLE);
        button.setEnabled(false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.b_visual:
                showProgressDialog();
                break;

            default:
                break;
        }
    }

    @Override public boolean onCreateOptionsMenu(Menu mimenu){
        getMenuInflater().inflate(R.menu.menu_show_bp,mimenu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem option_menu){
        int id=option_menu.getItemId();
        if (id==R.id.pbarA_show){
            pbA.setVisibility(View.INVISIBLE);
            Toast.makeText(this,"OK !!!",Toast.LENGTH_SHORT).show();
            return  true;
        }if (id==R.id.pbarB_show){
            pbB.setVisibility(View.INVISIBLE);
            return  true;
        }
        return super.onOptionsItemSelected(option_menu);
    }
}

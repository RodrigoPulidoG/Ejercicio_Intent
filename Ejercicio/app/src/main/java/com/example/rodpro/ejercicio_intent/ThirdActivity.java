package com.example.rodpro.ejercicio_intent;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity {

    private EditText etp, etw;
    private ImageButton ibp, ibw, ibc;
    private final int PHONE_CALL_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        etp = (EditText) findViewById(R.id.editTextPhone);
        etw = (EditText) findViewById(R.id.editTextWeb);
        ibp = (ImageButton) findViewById(R.id.imageButtonPhone);
        ibw = (ImageButton) findViewById(R.id.imageButtonWeb);
        ibc = (ImageButton) findViewById(R.id.imageButtonCamera);


        ibp.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                String phone = etp.getText().toString();
                if (phone != null) {
                    // COMPROBAR VERSION ACTUAL DE ANDROID QUE ESTAMOS CORRIENDO
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
                        requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, PHONE_CALL_CODE);
                    } else {
                        OlderVersions(phone);
                    }
                }
            }



            @SuppressLint("MissingPermission")
            private void OlderVersions(String phone) {
                Intent intentCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel: " + phone));
                if (CheckPermission(Manifest.permission.CALL_PHONE)) {
                    startActivity(intentCall);
                } else {
                    Toast.makeText(ThirdActivity.this, "You declined the access", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        // EN CASO DE TELEFONO
        switch (requestCode) {
            case PHONE_CALL_CODE:

                String permission = permissions[0];
                int result = grantResults[0];

                if (permission.equals(Manifest.permission.CALL_PHONE)) {
                    // COMPROBAR SI AH SIDO ACEPTADA O DENEGADA LA PETICION DEL PERMISO
                    if (result == PackageManager.PERMISSION_GRANTED) {
                        // PERMISO CONCEDIDO
                        String phone = etp.getText().toString();
                        Intent intentCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel" + phone));
                        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) return;
                        startActivity(intentCall);

                    }
                    else {
                        // SIN PERMISO
                        Toast.makeText(ThirdActivity.this, "You declined the access", Toast.LENGTH_SHORT).show();

                    }

                }


            break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private boolean CheckPermission(String permission){
        int result = this.checkCallingOrSelfPermission(permission);
        return result == PackageManager.PERMISSION_GRANTED;


    }

    private  void VersioneAntiguas(){

    }
}

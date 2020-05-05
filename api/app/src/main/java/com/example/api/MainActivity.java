package com.example.api;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class MainActivity extends AppCompatActivity {

    Button btnMap;
    private static final String TaG= "MAinActivity";

    private static final int ERROR_DIALOG_REQUEST = 9001;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnMap=findViewById(R.id.btnMap);
        if(isServicesOK()) {
        init();
        }
    }

    private void init(){
        Button BtnMap = (Button) findViewById(R.id.btnMap);
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,MapActivity.class);
                startActivity(intent);
            }
        });
    }
    public boolean isServicesOK(){
     //  Log.d(TAG,"isServicesOK: checking google services ");


        int availabla = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainActivity.this);

        if(availabla== ConnectionResult.SUCCESS){
        //    Log.d(TAG,"isServiceOK:Google play service is working");
            return true;
        }
        else if(GoogleApiAvailability.getInstance().isUserResolvableError(availabla)){
           // Log.d(TAG,"isServiceOK:Google play service is working");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this,availabla,ERROR_DIALOG_REQUEST);
            dialog.show();
        }
        else{
            Toast.makeText(this,"we cant make app request",Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}

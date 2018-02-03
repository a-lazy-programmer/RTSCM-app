package com.example.sakir.rtscm;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
    Button NetworkAccess,LocationAccess;
    private final int   NETWORK_ACCESS_CODE = 2;
    private final int   LOCATION_ACCESS_CODE = 3;
    private Context permission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        LocationAccess = (Button) findViewById(R.id.LoctionAccess);
        LocationAccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                askPermission(Manifest.permission.ACCESS_FINE_LOCATION,LOCATION_ACCESS_CODE);
            }
        });

        String myurl="http://localhost:8080/Coming%20Soon/index.html";
        WebView view = (WebView) this.findViewById(R.id.HomePage);
        view.getSettings().setJavaScriptEnabled(true);
        view.loadUrl(myurl);
    }
    private void askPermission(String permissions,int requestCode) {
        if(ContextCompat.checkSelfPermission(this,permissions)!= PackageManager.PERMISSION_GRANTED) {
        //We dont have Permission
            ActivityCompat.requestPermissions(this,new String[]{permissions},requestCode);
        }else {
            //We have Permission
            Toast.makeText(this, "Permission is Already Granted",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
            switch (requestCode) {
                case LOCATION_ACCESS_CODE:
                    if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                        Toast.makeText(this,"Location Access Permission Granted",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(this,"Location Access Permission Denied",Toast.LENGTH_SHORT).show();
                    }
            }
    }

}

package com.example.lorca.roomshareapp;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.lorca.roomshareapp.GoogleMaps.MapActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.firebase.auth.FirebaseAuth;

public class MainPageActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private static final String TAG = "MainPageActivity";

    private static final int ERROR_DIALOG_REQUEST = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        mAuth = FirebaseAuth.getInstance();

        if(isServicesOK()){
            init();
        }

    }

    public void goToSettings(View view) {
        Intent intent = new Intent(MainPageActivity.this, SettingsActivity.class);
        startActivity(intent);
        return;
    }

    public void logoutUser(View view) {
        mAuth.signOut();
        Intent intent = new Intent(MainPageActivity.this, ChooseLoginRegistrationActivity.class);
        startActivity(intent);
        finish();
        return;
    }

    public void goToRoomies(View view) {
        Intent intent = new Intent(MainPageActivity.this, Roomies.class);
        startActivity(intent);
        return;
    }

    public void goToListings(View view) {
        Intent intent = new Intent(MainPageActivity.this, HostMainActivity.class);
        startActivity(intent);
        return;
    }

    public void goToMap(View view) {
        Intent intent = new Intent(MainPageActivity.this, MapActivity.class);
        startActivity(intent);
        return;
    }

    private void init(){
        /*Button btnMap = (Button) findViewById(R.id.btnMap);
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainPageActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });*/
    }

    public boolean isServicesOK(){
        Log.d(TAG, "isServicesOK: checking google services version");

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainPageActivity.this);

        if(available == ConnectionResult.SUCCESS){
            //everything is fine and user can make map requests
            Log.d(TAG, "isServicesOK: Google Play Services is working");
            return true;
        }
        else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            //an error occured but we cn resolve it
            Log.d(TAG, "isServicesOK: An error occured but we cn resolve it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MainPageActivity.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        }
        else{
            Toast.makeText(this, "You can't make map requests", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}

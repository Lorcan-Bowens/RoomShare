package com.example.lorca.roomshareapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.text.TextUtils;
import android.widget.Button;
import android.util.Log;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class RegistrationActivityHost extends AppCompatActivity {

    Button mRegister;
    EditText mEmail, mPassword, mHostName, mCompany, mAddress, mDescription, mAskingPrice;

    RadioGroup mRadioGroup;

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    String enSuite = "";

    DatabaseReference uTable;
    ValueEventListener eventListener;
//      public void onRadioButtonClicked(View view) {
//          boolean checked = ((RadioButton) view).isChecked();
//
//          switch(view.getId()) {
//              case R.id.male:
//                  if (checked)
//                      u.setSex("male");
//
//                  break;
//              case R.id.female:
//                  if (checked)
//                      u.setSex("female");
//                  break;
//          }
//      }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mAuth = FirebaseAuth.getInstance();
        uTable = FirebaseDatabase.getInstance().getReference().child("users");

        mHostName = (EditText) findViewById(R.id.hostName);
        mRegister = (Button) findViewById(R.id.hostRegister);
        mEmail = (EditText) findViewById(R.id.hostEmail);
        mPassword = (EditText) findViewById(R.id.hostPassword);
        mCompany = (EditText) findViewById(R.id.hostCompany);
        mAddress = (EditText) findViewById(R.id.address);
        mDescription = (EditText) findViewById(R.id.description);
        mAskingPrice = (EditText) findViewById(R.id.askingPrice);

        mRadioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId) {
                    case R.id.yes:
                        enSuite = "Yes";
                        break;
                    case R.id.no:
                        enSuite = "No";
                        break;
                }

            }
        });
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUser();
            }
        });

    }

    private void createUser() {
        Host h = new Host();
        final String name = mHostName.getText().toString();
        final String company = mCompany.getText().toString();
        final String address = mAddress.getText().toString();
        final String desc = mDescription.getText().toString();
        final String price = mAskingPrice.getText().toString();
        String email = mEmail.getText().toString();
        String pass = mPassword.getText().toString();


        if (TextUtils.isEmpty((email))) {
            mEmail.setError("Enter correct email");
        } else if (TextUtils.isEmpty((pass))) {
            mEmail.setError("Enter correct password");
        } else if (TextUtils.isEmpty((name))) {
            mHostName.setError("Name must be set!");
        } else {
            mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(RegistrationActivityHost.this,
            new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        mUser = mAuth.getCurrentUser();
                        addUserDetail(company, address, price, mUser);
                    } else {
                        Toast.makeText(RegistrationActivityHost.this, "Register did not work", Toast.LENGTH_LONG).show();
                    }


                }
            });
        }
    }

    boolean exist = false;
    void addUserDetail(String company, String address, String price, FirebaseUser h) {
        exist = false;
        /*final Host host = new Host(name, company, address, desc, enSuite, askingPrice, h.getUid());*/
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    User user = child.getValue(User.class);
                    if (user.getuID().equals(mUser.getUid())) {
                        exist = true;
                        break;
                    }
                }
                if (exist) {
                    Toast.makeText(RegistrationActivityHost.this, "User Already exist", Toast.LENGTH_LONG).show();
                } else {
                    /*uTable.push().setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                finish();
                                startActivity(new Intent(RegistrationActivityHost.this, MainPageActivity.class));
                            } else {
                                Toast.makeText(RegistrationActivityHost.this, "Could not add to database", Toast.LENGTH_LONG).show();
                            }
                        }
                    });*/
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("RegistrationActivity", "Failed to read value.", databaseError.toException());
            }


        };
        uTable.addValueEventListener(listener);
        eventListener = listener;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (eventListener != null) {
            uTable.removeEventListener(eventListener);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mUser = mAuth.getCurrentUser();
    }
}
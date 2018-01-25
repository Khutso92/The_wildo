package com.example.admin.the_wildo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

import com.example.admin.the_wildo.activities.LandingActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class MainActivity extends AppCompatActivity {


    FirebaseAuth auth;
    EditText numberPhone, verifyCode;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBack;
    String verification_code;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {

//            User not logged in
            finish();
            startActivity(new Intent(getApplicationContext(), LandingActivity.class));
        }

        numberPhone = findViewById(R.id.edt_numberPhone);
        verifyCode = findViewById(R.id.edt_verifyCode);



        mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

            }

            @Override
            public void onVerificationFailed(FirebaseException e) {

            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);

                verification_code = s;
                Toast.makeText(MainActivity.this, "Code sent to the number", Toast.LENGTH_SHORT).show();
            }
        };
    }

    public void sendSms(View view) {

        String cell = numberPhone.getText().toString().trim();


        PhoneAuthProvider.getInstance().verifyPhoneNumber(cell, 3, TimeUnit.SECONDS, this, mCallBack);
    }


    public void signInWithPhone(PhoneAuthCredential credential) {

        auth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "user signed in successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), LandingActivity.class));
                } else {
                    Toast.makeText(MainActivity.this, "The verification code entered was invalid ", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void verify(View view) {

        String inputCode = verifyCode.getText().toString().trim();
        verifyPhoneNumber(verification_code, inputCode);

    }

    private void verifyPhoneNumber(String verify_code, String inputCode) {

        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verify_code, inputCode);
        signInWithPhone(credential);
    }
}



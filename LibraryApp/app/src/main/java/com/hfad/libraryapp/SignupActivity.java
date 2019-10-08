package com.hfad.libraryapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }

    public void signup(View view) {
        EditText email = (EditText) findViewById(R.id.ide);
        EditText pass = (EditText) findViewById(R.id.pass);
        EditText cpass = (EditText) findViewById(R.id.cpass);
        if(TextUtils.isEmpty(email.getText().toString().trim())) {
            Toast.makeText(this, "Enter a valid Email Id", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(pass.getText().toString().trim())) {
            Toast.makeText(this, "Enter a Password", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(cpass.getText().toString().trim())) {
            Toast.makeText(this, "Please confirm password", Toast.LENGTH_SHORT).show();
            return;
        }
        if(pass.getText().toString().trim().equals(cpass.getText().toString().trim())) {
            FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
            final FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
            firebaseAuth.createUserWithEmailAndPassword(email.getText().toString().trim(),pass.getText().toString().trim())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                Intent intent = new Intent(SignupActivity.this, UpdateInfoActivity.class);
                                intent.putExtra("flag", "1");
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(SignupActivity.this, "SignUp Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
        else {
            Toast.makeText(this, "Password does not match", Toast.LENGTH_SHORT).show();
            return;
        }
    }

}

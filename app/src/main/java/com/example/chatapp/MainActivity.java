package com.example.chatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText Email, password;
    Button Login;
    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.pass);
        Login = (Button) findViewById(R.id.login);
        fAuth = FirebaseAuth.getInstance();
        if (fAuth.getCurrentUser() != null) {

        }
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stemail = Email.getText().toString();
                String stpass = password.getText().toString();
                if (stemail.equals("Aditya@gmail.com") && stpass.equals("password")) {
                    Intent in = new Intent(MainActivity.this, chat.class);
                    startActivity(in);

                } else if (stemail.equals("") || stpass.equals("")) {
                    Toast.makeText(getApplicationContext(), "Enter both email and password", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "wrong email and password", Toast.LENGTH_SHORT).show();
                }
                fAuth.createUserWithEmailAndPassword(stemail, stpass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                        }
                    }
                });

            }
        });
    }
}



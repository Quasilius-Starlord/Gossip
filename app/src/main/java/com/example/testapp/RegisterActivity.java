package com.example.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Random;

public class RegisterActivity extends AppCompatActivity {

    private EditText edtEmail, edtPassword, edtName;
    private Button btnRegister;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        edtName = findViewById(R.id.edtName);
        btnRegister = findViewById(R.id.btnRegister);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();
                final String name = edtName.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    edtEmail.setText("can't empty");
                } else if (TextUtils.isEmpty(password)) {
                    edtPassword.setText("can't empty");
                } else if (TextUtils.isEmpty(name)) {
                    edtName.setText("can't empty");
                } else {
                    mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser currentUser = mAuth.getCurrentUser();
                                final String uid = currentUser.getUid();

                                HashMap<String,Object> dataUser = new HashMap<>();
                                dataUser.put("name",name);
                                dataUser.put("id",getRandomString(6));

                                db.collection("user").document(uid).set(dataUser).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Intent i = new Intent(RegisterActivity.this,MainActivity.class);
                                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(i);
                                        finish();
                                    }
                                });


                            } else {
                                Toast.makeText(RegisterActivity.this, "Register Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }

    private String getRandomString(int i) {
        String characters = "abcdefghijklmnopqrstuvwxyz1234567890";
        StringBuilder result = new StringBuilder();
        while(i>0) {
            Random rand = new Random();
            result.append(characters.charAt(rand.nextInt(characters.length())));
            i--;
        }
        return  result.toString();
    }
}
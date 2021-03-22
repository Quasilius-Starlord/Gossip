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

public class RegisterActivity extends AppCompatActivity {

    private EditText ediusername,edipassword;
    private Button btnregister;
    private FirebaseAuth mauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ediusername=findViewById(R.id.ediusername);
        edipassword=findViewById(R.id.edipassword);

        mauth=FirebaseAuth.getInstance();

        btnregister=findViewById(R.id.btnregister);

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=ediusername.getText().toString().trim();
                String password=edipassword.getText().toString().trim();
                if(TextUtils.isEmpty(username)){
                    ediusername.setText("can't be empty");
                }else if (TextUtils.isEmpty(password)){
                    edipassword.setText("can't be empty");
                }else{
                    mauth.createUserWithEmailAndPassword(username,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Intent i=new Intent(RegisterActivity.this,LoginActivity.class);
                                startActivity(i);
                                finish();
                            }else{
                                Toast.makeText(RegisterActivity.this,"fail registration",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                };
            };
        });
    }
}
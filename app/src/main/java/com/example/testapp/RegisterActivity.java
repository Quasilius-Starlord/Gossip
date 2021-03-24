package com.example.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private EditText ediusername,edipassword;
    private Button btnregister;
    private FirebaseAuth mauth;
    private FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ediusername=findViewById(R.id.regusername);
        edipassword=findViewById(R.id.regpassword);

        mauth=FirebaseAuth.getInstance();

        btnregister=findViewById(R.id.regbtn);
        database=FirebaseDatabase.getInstance();

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=ediusername.getText().toString().trim();
                String password=edipassword.getText().toString().trim();
                DatabaseReference reference=database.getReference().child("users");

                if(TextUtils.isEmpty(username)){
                    ediusername.setText("can't be empty");
                }else if (TextUtils.isEmpty(password)){
                    edipassword.setText("can't be empty");
                }else{
                    User user=new User(username,password);
                    Task<Void> confirm=reference.push().setValue(user);
//                    reference.push().setValue(user)
                    confirm.addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Intent i=new Intent(RegisterActivity.this,LoginActivity.class);
                            startActivity(i);
                            finish();
                        }
                    });
//                    if(confirm.isSuccessful()){
//
//                    }else{
//                        Toast.makeText(RegisterActivity.this,"fail registration",Toast.LENGTH_SHORT).show();
//                    }
//                    mauth.createUserWithEmailAndPassword(username,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//                            if(task.isSuccessful()){
//                                DatabaseReference reference=database.getReference().child("users");
//                                User user=new User(username,password);
//                                reference.push().setValue(user);
//                                Intent i=new Intent(RegisterActivity.this,LoginActivity.class);
//                                startActivity(i);
//                                finish();
//                            }else{
//                            }
//                        }
//                    });
                };
            };
        });
    }
}
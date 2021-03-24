package com.example.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private EditText ediusername,edipassword;
    private Button btnlogin,btnregister;
    private FirebaseAuth mauth;
    private FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ediusername=findViewById(R.id.ediusername);
        edipassword=findViewById(R.id.edipassword);

        btnlogin=findViewById(R.id.btnlogin);
        btnregister=findViewById(R.id.btnregister);

        mauth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        final Query query=database.getReference();

        final ValueEventListener valueEventListener=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for (DataSnapshot snapshot:dataSnapshot.getChildren())
                        Log.i("check",snapshot.getValue().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        query.addListenerForSingleValueEvent(valueEventListener);

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(LoginActivity.this,RegisterActivity.class);
                //i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                finish();
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=ediusername.getText().toString().trim();
                String password=edipassword.getText().toString().trim();
                if(TextUtils.isEmpty(username)){
                    ediusername.setText("can't be empty");
                }else if (TextUtils.isEmpty(password)){
                    edipassword.setText("can't be empty");
                }else{
                    DatabaseReference users=FirebaseDatabase.getInstance().getReference("users");
                    Query query1=users.orderByChild("name").equalTo(username);
                    query.addListenerForSingleValueEvent(valueEventListener);

//                    mauth.signInWithEmailAndPassword(username,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//                            if (task.isSuccessful()) {
//                                if (task.isSuccessful()) {
//                                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
//                                    startActivity(i);
//                                    finish();
//                                } else {
//                                    Toast.makeText(LoginActivity.this, "authentication error", Toast.LENGTH_LONG).show();
//                                }
//                            }
//                        };
//                    });
                    query.addListenerForSingleValueEvent(valueEventListener);
                }
            }
        });
    }

}
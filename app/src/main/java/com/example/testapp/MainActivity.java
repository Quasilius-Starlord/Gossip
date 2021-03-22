package com.example.testapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mauth;
    //private FirebaseAuth.AuthStateListener mauthlistener;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mauth=FirebaseAuth.getInstance();
//        mauthlistener=new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                if(firebaseAuth.getCurrentUser()==null){
//                    Intent i =new Intent(MainActivity.this,LoginActivity.class);
//                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
//                    startActivity(i);
//                    finish();
//                }else{
//                }
//            };
//        };
    };

    @Override
    protected void onStart() {
        super.onStart();
        //mauth.addAuthStateListener(mauthlistener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        //mauth.removeAuthStateListener(mauthlistener);
    }
}

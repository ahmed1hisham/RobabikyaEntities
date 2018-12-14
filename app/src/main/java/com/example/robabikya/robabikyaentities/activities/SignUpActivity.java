package com.example.robabikya.robabikyaentities.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import com.example.robabikya.robabikyaentities.R;
import com.example.robabikya.robabikyaentities.firebase.FirebaseUtils;
import com.greeninnovators.robabikya.models.Bin;
import com.greeninnovators.robabikya.models.User;
import kotlin.Unit;

import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ImageButton button = (ImageButton) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String email = ((EditText) findViewById(R.id.email)).getText().toString();
                String pass = ((EditText) findViewById(R.id.password)).getText().toString();
                String num = ((EditText) findViewById(R.id.number)).getText().toString();
                String name = ((EditText) findViewById(R.id.name)).getText().toString();
                FirebaseUtils.Companion.signUpWithEmailAndPassword(email,pass, () -> {
                    String uid = FirebaseUtils.Companion.getFirebaseAuth().getCurrentUser().getUid();
                    FirebaseUtils.Companion.createUserInDatabase(new User(uid, name,email,num,"",0,new Bin(0,new HashMap<>()),new HashMap<>()), () ->{
                        Intent intent = new Intent(SignUpActivity.this, HomeScreen.class);
                        startActivity(intent);
                        return Unit.INSTANCE;
                    }, (String message) ->{
                        Toast.makeText(SignUpActivity.this, message, Toast.LENGTH_SHORT).show();
                        return Unit.INSTANCE;
                    });

                    return Unit.INSTANCE;
                }, (message) -> {
                    Toast.makeText(SignUpActivity.this, message, Toast.LENGTH_SHORT).show();
                    return Unit.INSTANCE;
                });

            }
        });

        Button loginBtn = (Button) findViewById(R.id.signin);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}

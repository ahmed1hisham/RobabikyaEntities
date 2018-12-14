package com.example.robabikya.robabikyaentities.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.robabikya.robabikyaentities.R;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import com.example.robabikya.robabikyaentities.firebase.FirebaseUtils;
import kotlin.Unit;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //When Clicking Login Button....
        ImageButton button = (ImageButton) findViewById(R.id.buttonLogin);
        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                EditText email = (EditText) findViewById(R.id.email);
                EditText pass = (EditText) findViewById(R.id.password);
                if (email.getText().toString().equals("admin@admin.com")) {
                    FirebaseUtils.Companion.signInWithEmailAndPassword(email.getText().toString(),pass.getText().toString(), () -> {
                        //TODO startActivity(Intent(LoginActivity.this, AdminActivity.class));
                        finish();
                        return Unit.INSTANCE;
                    }, (message) -> {
                        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                        return Unit.INSTANCE;
                    });

                } else {
                    Intent intent = new Intent(LoginActivity.this, HomeScreen.class);

                    FirebaseUtils.Companion.signInWithEmailAndPassword(email.getText().toString(), pass.getText().toString(), () -> {
                        startActivity(intent);
                        finish();
                        return Unit.INSTANCE;
                    }, (message) -> {
                        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                        return Unit.INSTANCE;
                    });
                }

            }
        });


        //When Clicking Needs To SignUp button
        Button signUp = (Button) findViewById(R.id.buttonSignUp);
        signUp.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }
}
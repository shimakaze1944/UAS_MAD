package com.example.uas_mad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class loginPage extends AppCompatActivity implements TextWatcher{

    Button btn_login;
    TextInputLayout login_email, login_passwd;
    String email = "", pass = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        //findFewById
        btn_login = findViewById(R.id.button_login);
        login_email = findViewById(R.id.login_email);
        login_passwd = findViewById(R.id.login_pass);

        login_email.getEditText().addTextChangedListener(this);
        login_passwd.getEditText().addTextChangedListener(this);

        btn_login.setEnabled(false);

        btn_login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(email)|| TextUtils.isEmpty(pass)){
                    Toast.makeText(loginPage.this, "All field are required!", Toast.LENGTH_SHORT).show();
                }else{
                    .signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Intent intent = new Intent(loginPage.this, MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();
                            }else{
                                Toast.makeText(loginPage.this, "Authentication Failed!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

    }
}
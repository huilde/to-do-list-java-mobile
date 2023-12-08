package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;



public class LoginActivity extends AppCompatActivity {
    private TextView btnEntrar;
    private TextView btnTrocarSenha;
    private EditText email;
    private EditText senha;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SharedPreferencesHelper.saveDefaultCredentials(this);

        btnEntrar = findViewById(R.id.Alterar);
        btnTrocarSenha = findViewById(R.id.esqueciSenha);
        email = findViewById(R.id.novoLogin);
        senha = findViewById(R.id.novaSenha);

        btnEntrar.setOnClickListener(v -> {
            String username = SharedPreferencesHelper.getSavedUsername(this);
            String password = SharedPreferencesHelper.getSavedPassword(this);


            if (email.getText().toString().equals(username) && senha.getText().toString().equals(password)) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            } else {
                Toast
                        .makeText(this, "login ou senha incorreta", Toast.LENGTH_SHORT)
                        .show();
            }
        });

        btnTrocarSenha.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, TrocarSenha.class);
            startActivity(intent);
       });
    }

}

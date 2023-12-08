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

public class TrocarSenha extends AppCompatActivity {
    private TextView trocarSenha;
    private EditText login;
    private EditText senha;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trocar_senha);
        SharedPreferencesHelper.saveDefaultCredentials(this);

    trocarSenha = findViewById(R.id.Alterar);
        login = findViewById(R.id.novoLogin);
        senha = findViewById(R.id.novaSenha);

        trocarSenha.setOnClickListener(v -> {
            String novoLogin = login.getText().toString();
            String novaSenha = senha.getText().toString();
            SharedPreferencesHelper.saveCredentials(getApplicationContext(),novoLogin,novaSenha);
            Toast.makeText(TrocarSenha.this, "Login e senha foram alterados com sucesso.", Toast.LENGTH_SHORT).show();
            finish();

        });

    }


}

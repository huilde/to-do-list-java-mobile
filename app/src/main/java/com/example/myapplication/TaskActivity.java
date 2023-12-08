package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class TaskActivity  extends AppCompatActivity {
    private ArrayList<String> taskList;
    private TaskAdapter taskAdapter;

    private EditText nome;
    private EditText descricao;
    private TextView categoria;
    private String prioridade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        Button btnCancelar = findViewById(R.id.cancelar);
        Button btnAdicionar = findViewById(R.id.Alterar);

        nome = findViewById(R.id.novoLogin);
        descricao = findViewById(R.id.novaSenha);
        categoria = findViewById(R.id.categoria);
        // Valor padrão

        RadioGroup radioGroupPrioridade = findViewById(R.id.radioGroupPrioridade);

// Adicione um ouvinte de mudança ao RadioGroup
        radioGroupPrioridade.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                // Verifique qual RadioButton foi selecionado
                RadioButton radioButton = findViewById(checkedId);
                if (radioButton != null) {
                    // Atualize o valor da prioridade conforme necessário
                     prioridade = radioButton.getText().toString();
                }
            }
        });



        // Defina um ouvinte de clique para o botão "cancelar"
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lógica para voltar para a intent anterior ou realizar qualquer outra ação desejada
                voltarParaIntentAnterior();
            }
        });

        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TarefaRepository repository =  TarefaRepository.getInstance();
                Tarefa novaTarefa = new Tarefa("1", nome.getText().toString(),descricao.getText().toString(),prioridade,
                        categoria.getText().toString());

                repository.criarTarefa(novaTarefa,getApplicationContext().getFilesDir());
                voltarParaIntentAnterior();
                System.out.println(repository.lerTarefas(getApplicationContext().getFilesDir()));


            }
        });
    }

    private void voltarParaIntentAnterior() {
        // Crie um Intent para retornar à intent anterior ou à atividade anterior
        Intent intentAnterior = new Intent(this, MainActivity.class); // Substitua "SuaAtividadeAnterior" pela classe da sua atividade anterior
        startActivity(intentAnterior);

        // Finalize a atividade atual (opcional, dependendo dos requisitos do seu aplicativo)
        finish();
    }

    }



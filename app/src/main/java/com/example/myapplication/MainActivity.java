package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.*;
import com.example.myapplication.Tarefa;
import com.example.myapplication.TarefaRepository;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> taskList;
    private TaskAdapter taskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Tarefa> taskList = TarefaRepository.getInstance().lerTarefas(getApplicationContext().getFilesDir());

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        taskAdapter = new TaskAdapter(taskList);
        recyclerView.setAdapter(taskAdapter);

        Button addButton = findViewById(R.id.addButton);
        ImageButton deletar = findViewById(R.id.delete);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddTask();
            }
        });

        deletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TarefaRepository repository =  TarefaRepository.getInstance();

                repository.excluirTarefas(getApplicationContext().getFilesDir());
                System.out.println(repository.lerTarefas(getApplicationContext().getFilesDir()));
                Toast.makeText(getApplicationContext(), "Tarefa(s) excluída(s)", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);

                finish();

            }
        });
    }

    private void showAddTask() {

        Intent intent = new Intent(MainActivity.this, TaskActivity.class);
        startActivity(intent);

    }
}

package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    public static List<Tarefa> taskList;

    public TaskAdapter(List<Tarefa> taskList) {
        this.taskList = taskList;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Tarefa task = taskList.get(position);
        holder.bind(task);
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder {
        private final CheckBox checkBox;
        private final TextView nomeTextView;
        private final TextView descricaoTextView;
        private final TextView prioridadeTextView;
        private final TextView categoriaTextView;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkBox);
            nomeTextView = itemView.findViewById(R.id.nomeTextView);
            descricaoTextView = itemView.findViewById(R.id.descricaoTextView);
            prioridadeTextView = itemView.findViewById(R.id.prioridadeTextView);
            categoriaTextView = itemView.findViewById(R.id.categoriaTextView);

            // Adicione um ouvinte de clique ao itemView
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TarefaRepository repository =  TarefaRepository.getInstance();

                    System.out.println("fui clicado");
                    // Obtém a posição do item no adaptador
                    int position = getAdapterPosition();
                    System.out.println(position);
                    if (position != RecyclerView.NO_POSITION) {
                        System.out.println("cheguei porra");
                        // Obtém a tarefa correspondente à posição
                        Tarefa task = taskList.get(position);

                        // Inverte o valor de isConcluida
                        task.setConcluida(!task.isConcluida());
                        System.out.println(task);
                        repository.atualizarTarefa(task, itemView.getContext().getFilesDir());
                        // Atualiza a interface do usuário
                        notifyDataSetChanged();
                    }
                }
            });
        }

        public void bind(Tarefa task) {
            checkBox.setChecked(task.isConcluida());
            nomeTextView.setText(task.getNome());
            descricaoTextView.setText(task.getDescricao());
            prioridadeTextView.setText(task.getPrioridade());
            categoriaTextView.setText(task.getCategoria());
        }
    }
}



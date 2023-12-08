package com.example.myapplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class TarefaRepository {

        private static final String ARQUIVO_TAREFAS = "tarefas.dat";
        private static TarefaRepository instance;

        private TarefaRepository() {
            // Construtor privado para evitar instanciação direta
        }

        // Método para obter a instância única da classe
        public static synchronized TarefaRepository getInstance() {
            if (instance == null) {
                instance = new TarefaRepository();
            }
            return instance;
        }

        // Método para criar uma nova tarefa
        public void criarTarefa(Tarefa tarefa, File diretorioInterno) {
            List<Tarefa> tarefas = lerTarefas(diretorioInterno);
            tarefas.add(tarefa);
            salvarTarefas(tarefas, diretorioInterno);
        }

        // Método para ler todas as tarefas do arquivo
        public List<Tarefa> lerTarefas(File diretorioInterno) {
            List<Tarefa> tarefas = new ArrayList<>();

            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(diretorioInterno, ARQUIVO_TAREFAS)))) {
                tarefas = (List<Tarefa>) ois.readObject();
            } catch (FileNotFoundException e) {
                // Se o arquivo não existe, retorna uma lista vazia
                return tarefas;
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                // Tratar outros erros de leitura ou desserialização
            }

            return tarefas;
        }

        // Método para atualizar uma tarefa existente
        public void atualizarTarefa(Tarefa tarefaAtualizada, File diretorioInterno) {
            List<Tarefa> tarefas = lerTarefas(diretorioInterno);
            for (int i = 0; i < tarefas.size(); i++) {
                Tarefa tarefa = tarefas.get(i);
                if (tarefa.getId().equals(tarefaAtualizada.getId())) {
                    tarefas.set(i, tarefaAtualizada);
                    break;
                }
            }
            salvarTarefas(tarefas, diretorioInterno);
        }

        // Método para excluir uma tarefa
        public void excluirTarefas( File diretorioInterno) {
            List<Tarefa> tarefas = lerTarefas(diretorioInterno);
            tarefas.removeIf(tarefa -> tarefa.isConcluida());
            salvarTarefas(tarefas, diretorioInterno);
        }

        // Método para salvar a lista de tarefas no arquivo
        private void salvarTarefas(List<Tarefa> tarefas, File diretorioInterno) {
            File file = new File(diretorioInterno, ARQUIVO_TAREFAS);

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                oos.writeObject(tarefas);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


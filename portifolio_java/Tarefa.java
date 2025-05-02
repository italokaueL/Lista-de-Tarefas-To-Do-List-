package portifolio_java;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

// Classe que representa uma Tarefa
class Tarefa {
    private int id;
    private String titulo;
    private String descricao;
    private String dataCriacao;
    private String status;
    private String dataConclusao; // Data de conclusão (opcional)

    public Tarefa(int id, String titulo, String descricao, String dataCriacao, String status) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
        this.status = status;
    }

    // Getters e Setters
    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getDataConclusao() { return dataConclusao; }
    public void setDataConclusao(String dataConclusao) { this.dataConclusao = dataConclusao; }

    // Exibe os detalhes da tarefa
    @Override
    public String toString() {
        return "ID: " + id + ", Título: " + titulo + ", Descrição: " + descricao + 
               ", Data de Criação: " + dataCriacao + ", Status: " + status + 
               ", Data de Conclusão: " + (dataConclusao != null ? dataConclusao : "Não concluida");
    }
}

// Classe que gerencia as tarefas
class GerenciadorTarefas {
    private List<Tarefa> tarefas; // Lista de tarefas
    private int proximoId; // Contador para IDs únicos

    public GerenciadorTarefas() {
        tarefas = new ArrayList<>();
        proximoId = 1;
    }

    // Adiciona uma nova tarefa
    public void adicionarTarefa(String titulo, String descricao, String dataCriacao, String status) {
        Tarefa tarefa = new Tarefa(proximoId++, titulo, descricao, dataCriacao, status);
        tarefas.add(tarefa);
    }

    // Lista todas as tarefas
    public void listarTarefas() {
        for (Tarefa tarefa : tarefas) {
            System.out.println(tarefa);
        }
    }

    // Atualiza uma tarefa existente
    public void atualizarTarefa(int id, String status) {
        for (Tarefa tarefa : tarefas) {
            if (tarefa.getId() == id) {
                tarefa.setStatus(status);

                // Define a data de conclusão se o status for "concluída"
                if (status.equalsIgnoreCase("concluida")) {
                    tarefa.setDataConclusao(java.time.LocalDate.now().toString());
                }
                break;
            }
        }
    }

    // Exclui uma tarefa
    public void excluirTarefa(int id) {
        tarefas.removeIf(tarefa -> tarefa.getId() == id);
    }

    // Filtra tarefas por status
    public void filtrarTarefasPorStatus(String status) {
        for (Tarefa tarefa : tarefas) {
            if (tarefa.getStatus().equalsIgnoreCase(status)) {
                System.out.println(tarefa);
            }
        }
    }
}
// classe para validar a data
class ValidadorData {
    public static boolean validarData(String data) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate.parse(data, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
// Classe para validação de status
class ValidadorStatus {
    public static boolean validarStatus(String status) {
        return status.equalsIgnoreCase("pendente") ||
               status.equalsIgnoreCase("em andamento") ||
               status.equalsIgnoreCase("concluida");
    }
}

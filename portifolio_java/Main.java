package portifolio_java;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
public class Main {
    public static void main(String[] args) {
        GerenciadorTarefas gerenciador = new GerenciadorTarefas();
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n--- Sistema de Gerenciamento de Tarefas ---");
            System.out.println("1. Adicionar Tarefa");
            System.out.println("2. Listar Tarefas");
            System.out.println("3. Atualizar Tarefa");
            System.out.println("4. Excluir Tarefa");
            System.out.println("5. Filtrar Tarefas por Status");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            switch (opcao) {
                case 1:
                    // Adicionar tarefa
                    System.out.print("Título: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Descrição: ");
                    String descricao = scanner.nextLine();

                    // Validação da data de criação
                    String dataCriacao;
                    do {
                        System.out.print("Data de Criação (dd/MM/yyyy): ");
                        dataCriacao = scanner.nextLine();
                        if (!ValidadorData.validarData(dataCriacao)) {
                            System.out.println("Data inválida! foi usado algum dia, mes ou ano não existente");
                        }
                    } while (!ValidadorData.validarData(dataCriacao));

                    // Validação do status
                    String status;
                    do {
                        System.out.print("Status (pendente, em andamento, concluida): ");
                        status = scanner.nextLine();
                        if (!ValidadorStatus.validarStatus(status)) {
                            System.out.println("Status inválido! Use 'pendente', 'em andamento' ou 'concluída'.");
                        }
                    } while (!ValidadorStatus.validarStatus(status));

                    gerenciador.adicionarTarefa(titulo, descricao, dataCriacao, status);
                    break;

                case 2:
                    // Listar tarefas
                    gerenciador.listarTarefas();
                    break;

                case 3:
                    // Atualizar tarefa
                    System.out.print("ID da Tarefa: ");
                    int idAtualizar;
                    try {
                        idAtualizar = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("ID inválido! Digite um número.");
                    continue; // Retorna ao menu sem quebrar o programa
                    }


                    // Validação do novo status
                    String novoStatus;
                    do {
                        System.out.print("Novo Status (pendente em andamento concluida): ");
                        novoStatus = scanner.nextLine();
                        if (!ValidadorStatus.validarStatus(novoStatus)) {
                            System.out.println("Status inválido! Use 'pendente', 'em andamento' ou 'concluida'.");
                        }
                    } while (!ValidadorStatus.validarStatus(novoStatus));

                    // Define a data de conclusão se o status for "concluída"
                    gerenciador.atualizarTarefa(idAtualizar, novoStatus);
                    break;

                case 4:
                    // Excluir tarefa
                    System.out.print("ID da Tarefa: ");
                    int idExcluir;
                    try {
                        idExcluir = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("ID inválido! Digite um número.");
                        continue; // Retorna ao menu sem quebrar o programa
                    }

                case 5:
                    // Filtrar tarefas por status
                    System.out.print("Status para Filtrar: ");
                    String statusFiltrar = scanner.nextLine();
                    gerenciador.filtrarTarefasPorStatus(statusFiltrar);
                    break;

                case 0:
                    // Sair
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);

        scanner.close();
    }
}
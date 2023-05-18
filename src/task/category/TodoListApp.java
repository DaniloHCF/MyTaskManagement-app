package task.category;

import task.TaskManager;
import task.Task;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

    public class TodoListApp {
        private static Scanner scanner = new Scanner(System.in);
        private static TaskManager taskManager = new TaskManager();

        public static void main(String[] args) {
            int choice = -1;

            while (choice != 0) {
                displayMenu();
                choice = getUserChoice();

                switch (choice) {
                    case 1:
                        addTask();
                        break;
                    case 2:
                        removeTask();
                        break;
                    case 3:
                        listTasks();
                        break;
                    case 0:
                        System.out.println("Saindo do aplicativo...");
                        break;
                    default:
                        System.out.println("Escolha inválida. Por favor, tente novamente.");
                        break;
                }
            }

            scanner.close();
        }

        private static void displayMenu() {
            System.out.println("----- App Lista de Tarefas -----");
            System.out.println("1. Add Tarefa");
            System.out.println("2. Remover Tarefa");
            System.out.println("3. Lista de Tarefas");
            System.out.println("0. Sair");
            System.out.print("Digite a sua escolha: ");
        }

        private static int getUserChoice() {
            return scanner.nextInt();
        }

        private static void addTask() {
            scanner.nextLine(); // Consume the newline character

            System.out.print("Digite o nome da tarefa: ");
            String title = scanner.nextLine();

            System.out.print("Digite a descrição da tarefa: ");
            String description = scanner.nextLine();

            int priority = 0;
            boolean validPriority = false;
            while (!validPriority){
                System.out.print("Qual é a prioridade da tarefa? ");
                if (scanner.hasNextInt()){
                    priority = scanner.nextInt();
                    validPriority = true;
                } else {
                    System.out.println("Prioridade Inválida. Insira um número inteiro válido.");
                    scanner.nextLine(); // Consume the invalid input
                }
            }


            System.out.print("Quando é a previsão de conclusão (dd/MM/yyyy)? ");
            String dueDateStr = scanner.next();

            scanner.nextLine();

            System.out.print("Qual é a categoria da tarefa? ");
            String category = scanner.nextLine();


            // Convert the due date string to a Date object
            Date dueDate = null;
            try {
                dueDate = new SimpleDateFormat("dd/MM/yyyy").parse(dueDateStr);
            } catch (ParseException e) {
                System.out.println("Formato de data inválido. Tarefa não adicionada.");
                return;
            }

            Task task = new Task(title, description, priority, dueDate, category);
            taskManager.addTask(task);

            System.out.println("Tarefa adicionada com sucesso!");
        }

        private static void removeTask() {
            scanner.nextLine(); // Consume the newline character

            System.out.print("Digite o nome da tarefa para remover: ");
            String title = scanner.nextLine();

            Task task = taskManager.getTaskByTitle(title);
            if (task == null) {
                System.out.println("Task not found.");
            } else {
                taskManager.removeTask(task);
                System.out.println("Task removed successfully.");
            }
        }

        private static void listTasks() {
            System.out.println("Lista de Tarefas:");
            System.out.println("-----------");

            if (taskManager.getTasks().isEmpty()) {
                System.out.println("Nenhuma tarefa encontrada!");
            } else {
                for (Task task : taskManager.getTasks()) {
                    System.out.println("Título: " + task.getTitle());
                    System.out.println("Descrição: " + task.getDescription());
                    System.out.println("Prioridade: " + task.getPriority());
                    System.out.println("Data de conclusão: " + task.getDueDate());
                    System.out.println("Categoria: " + task.getCategory());
                    System.out.println("----------------------");
                }
            }
        }
    }



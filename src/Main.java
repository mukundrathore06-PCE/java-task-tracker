import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            run();
        } catch (IOException exception) {
            System.err.println("File error: " + exception.getMessage());
            System.err.println("Check that you have permission to read and write files in this folder.");
        }
    }

    private static void run() throws IOException {
        TaskManager taskManager = new TaskManager();

        try (Scanner scanner = new Scanner(System.in)) {
            boolean running = true;

            System.out.println("=== Java Task Tracker ===");

            while (running) {
                printMenu();
                System.out.print("Choose an option (1-5): ");
                String choice = scanner.nextLine().trim();

                switch (choice) {
                    case "1":
                        System.out.print("Enter task description: ");
                        String description = scanner.nextLine().trim();
                        if (description.isEmpty()) {
                            System.out.println("Task description cannot be empty.");
                        } else {
                            taskManager.addTask(description);
                            System.out.println("Task added successfully.");
                        }
                        break;

                    case "2":
                        showTasks(taskManager.getTasks());
                        break;

                    case "3":
                        System.out.print("Enter the task ID to mark as done: ");
                        int completedId = readId(scanner);
                        if (completedId < 1) {
                            System.out.println("Please enter a valid positive task ID.");
                        } else if (taskManager.markCompleted(completedId)) {
                            System.out.println("Task marked as DONE.");
                        } else {
                            System.out.println("No task found with that ID.");
                        }
                        break;

                    case "4":
                        System.out.print("Enter the task ID to delete: ");
                        int deletedId = readId(scanner);
                        if (deletedId < 1) {
                            System.out.println("Please enter a valid positive task ID.");
                        } else if (taskManager.deleteTask(deletedId)) {
                            System.out.println("Task deleted successfully.");
                        } else {
                            System.out.println("No task found with that ID.");
                        }
                        break;

                    case "5":
                        running = false;
                        System.out.println("Tasks saved. Goodbye!");
                        break;

                    default:
                        System.out.println("Invalid option. Choose a number from 1 to 5.");
                }

                System.out.println();
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n1. Add task");
        System.out.println("2. View all tasks");
        System.out.println("3. Mark task as done");
        System.out.println("4. Delete task");
        System.out.println("5. Exit");
    }

    private static void showTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks yet. Add your first task!");
            return;
        }

        System.out.println("\nYour tasks:");
        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    private static int readId(Scanner scanner) {
        String input = scanner.nextLine().trim();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            return -1;
        }
    }
}

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskManager {
    private final List<Task> tasks = new ArrayList<>();
    private final Path storageFile = Paths.get("tasks.txt");
    private int nextId = 1;

    public TaskManager() throws IOException {
        loadTasks();
    }

    public void addTask(String description) throws IOException {
        Task task = new Task(nextId++, description.trim(), false);
        tasks.add(task);
        saveTasks();
    }

    public List<Task> getTasks() {
        return Collections.unmodifiableList(tasks);
    }

    public boolean markCompleted(int id) throws IOException {
        for (Task task : tasks) {
            if (task.getId() == id) {
                if (!task.isCompleted()) {
                    task.markCompleted();
                    saveTasks();
                }
                return true;
            }
        }
        return false;
    }

    public boolean deleteTask(int id) throws IOException {
        boolean removed = tasks.removeIf(task -> task.getId() == id);
        if (removed) {
            saveTasks();
        }
        return removed;
    }

    private void loadTasks() throws IOException {
        if (!Files.exists(storageFile)) {
            return;
        }

        try (BufferedReader reader = Files.newBufferedReader(storageFile, StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|", 3);
                if (parts.length != 3) {
                    continue; // Ignore malformed lines.
                }

                try {
                    int id = Integer.parseInt(parts[0]);
                    boolean completed = Boolean.parseBoolean(parts[1]);
                    String description = parts[2];
                    tasks.add(new Task(id, description, completed));
                    nextId = Math.max(nextId, id + 1);
                } catch (NumberFormatException exception) {
                    // Ignore a line with an invalid task ID and continue loading.
                }
            }
        }
    }

    private void saveTasks() throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(storageFile, StandardCharsets.UTF_8)) {
            for (Task task : tasks) {
                writer.write(task.getId() + "|" + task.isCompleted() + "|" + task.getDescription());
                writer.newLine();
            }
        }
    }
}

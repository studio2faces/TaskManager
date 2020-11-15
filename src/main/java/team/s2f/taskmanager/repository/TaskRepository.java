package team.s2f.taskmanager.repository;

import team.s2f.taskmanager.model.Task;

import java.util.List;

public interface TaskRepository {
    Task save(Task task);

    void delete(Task task);

    Task get(int id);

    List<Task> getAll();

    List<Task> getAllActual();

    List<Task> getAllDone();
}

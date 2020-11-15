package team.s2f.taskmanager.service;

import org.springframework.stereotype.Service;
import team.s2f.taskmanager.model.Task;
import team.s2f.taskmanager.repository.TaskRepository;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task save(Task task){
        return taskRepository.save(task);
    }

    public Task get(int id) {
        return taskRepository.get(id);
    }

    public void delete(int id) {
        Task task = get(id);
        taskRepository.delete(task);
    }

    public List<Task> getAllActual() {
        return taskRepository.getAllActual();
    }

    public List<Task> getAllDone() {
        return taskRepository.getAllDone();
    }


}

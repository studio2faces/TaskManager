package team.s2f.taskmanager.repository.datajpa;

import org.springframework.stereotype.Repository;
import team.s2f.taskmanager.model.Task;
import team.s2f.taskmanager.repository.TaskRepository;

import java.util.List;

@Repository
public class TaskRepositoryImpl implements TaskRepository {

    private final CrudTaskRepository crudTaskRepository;

    public TaskRepositoryImpl(CrudTaskRepository crudTaskRepository) {
        this.crudTaskRepository = crudTaskRepository;
    }

    @Override
    public Task save(Task task) {
        return crudTaskRepository.save(task);
    }

    @Override
    public void delete(Task task) {
        crudTaskRepository.delete(task);
    }

    @Override
    public Task get(int id) {
        return crudTaskRepository.findById(id);
    }

    @Override
    public List<Task> getAll() {
        return crudTaskRepository.findAll();
    }

    @Override
    public List<Task> getAllActual() {
        return crudTaskRepository.getAllActual();
    }

    @Override
    public List<Task> getAllDone() {
        return crudTaskRepository.getAllDone();
    }
}

package team.s2f.taskmanager.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import team.s2f.taskmanager.model.Task;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudTaskRepository extends JpaRepository<Task, Integer> {

    @Query("SELECT t FROM Task t WHERE t.isDone=FALSE")
    List<Task> getAllActual();

    @Query("SELECT t FROM Task t WHERE t.isDone=TRUE")
    List<Task> getAllDone();

    @Transactional
    Task save(Task task);

    Task findById(int id);
}

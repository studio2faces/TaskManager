package team.s2f.taskmanager.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import team.s2f.taskmanager.model.Task;
import team.s2f.taskmanager.service.TaskService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskViewController {

    @Autowired
    TaskService taskService;

    @GetMapping
    public String getAll(Model model) {
        List<Task> actual = taskService.getAllActual();
        List<Task> done = taskService.getAllDone();
        model.addAttribute("actual", actual);
        model.addAttribute("done", done);
        return "tasks";
    }

    @GetMapping("/done")
    public String setDone(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        Task task = taskService.get(id);
        task.setDone(true);
        taskService.save(task);
        return "redirect:/tasks";
    }

}

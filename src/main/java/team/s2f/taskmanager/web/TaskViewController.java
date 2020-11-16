package team.s2f.taskmanager.web;

import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import team.s2f.taskmanager.model.Task;
import team.s2f.taskmanager.service.TaskService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
        model.addAttribute("task_upd", null);
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

    @PostMapping(path = "/save")
    @ResponseStatus(HttpStatus.OK)
    public void save(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        String description = request.getParameter("desc");
        Task task = new Task();
        task.setDescription(description);
        taskService.save(task);
        response.sendRedirect(request.getContextPath() + "/tasks");
    }

    //doesn't work
    @GetMapping("/update")
    public String update(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        Task taskUpd = taskService.get(id);
        model.addAttribute("task_upd", taskUpd);
        return "redirect:/tasks";
    }

    @GetMapping("/delete")
    public String delete(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        taskService.delete(id);
        return "redirect:/tasks";
    }


}

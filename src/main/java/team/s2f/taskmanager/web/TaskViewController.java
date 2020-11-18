package team.s2f.taskmanager.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import team.s2f.taskmanager.model.Task;
import team.s2f.taskmanager.service.TaskService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
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
    @ResponseStatus(HttpStatus.OK)
    public String getAll(@RequestParam(required = false) String action, @RequestParam(required = false) String id, Model model) {
        if (action != null) {
            Task taskUpd = taskService.get(Integer.parseInt(id));
            model.addAttribute("task_upd", taskUpd);
        }
        List<Task> actual = taskService.getAllActual();
        List<Task> done = taskService.getAllDone();
        model.addAttribute("actual", actual);
        model.addAttribute("done", done);
        return "tasks";
    }

    @GetMapping("/done")
    @ResponseStatus(HttpStatus.OK)
    public void setDone(HttpServletRequest request, HttpServletResponse response, @RequestParam String id) throws IOException {
        Task task = taskService.get(Integer.parseInt(id));
        taskService.save(taskService.setDone(task));

        response.sendRedirect(request.getContextPath() + "/tasks");
        //  return "redirect:/tasks";
    }

    @PostMapping(path = "/save")
    @ResponseStatus(HttpStatus.OK)
    public void save(HttpServletRequest request, HttpServletResponse response,
                     @RequestParam(required = false) String id, @RequestParam String desc) throws IOException {
        Task task = new Task(id.isEmpty() ? null : Integer.parseInt(id), desc);
        taskService.save(task);
        response.sendRedirect(request.getContextPath() + "/tasks");
    }

    @GetMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/tasks");
        dispatcher.forward(request, response);
    }

    @GetMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public void delete(HttpServletRequest request, HttpServletResponse response, @RequestParam String id) throws IOException {
        taskService.delete(Integer.parseInt(id));
        response.sendRedirect(request.getContextPath() + "/tasks");
        //return "redirect:/tasks";
    }
}

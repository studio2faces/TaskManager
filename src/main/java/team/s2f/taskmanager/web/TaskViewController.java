package team.s2f.taskmanager.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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
    public String getAll(HttpServletRequest request, Model model) {
        if (request.getParameter("action") != null) {
            int id = Integer.parseInt(request.getParameter("id"));
            Task taskUpd = taskService.get(id);
            model.addAttribute("task_upd", taskUpd);
        }
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

    @PostMapping(path = "/save")
    @ResponseStatus(HttpStatus.OK)
    public void save(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String description = request.getParameter("desc");

        if (request.getParameterMap().containsKey("id")) {
            int id = Integer.parseInt(request.getParameter("id"));
            Task toUpdate = taskService.get(id);
            toUpdate.setDescription(description);
            taskService.save(toUpdate);
        } else {
            Task task = new Task();
            task.setDescription(description);
            taskService.save(task);
        }

        response.sendRedirect(request.getContextPath() + "/tasks");
    }
    
    @GetMapping("/update")
    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/tasks");
        dispatcher.forward(request, response);
    }

    @GetMapping("/delete")
    public String delete(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        taskService.delete(id);
        return "redirect:/tasks";
    }
}

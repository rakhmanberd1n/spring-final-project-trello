package kz.bitlab.academy.springfinal.commentaries.controller;


import kz.bitlab.academy.springfinal.commentaries.service.CommentariesService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class CommentariesController {
    private final CommentariesService commentariesService;

    @PostMapping("/tasks/{taskId}/edit/add-comment")
    public String addComment(@PathVariable Long taskId,
                             @RequestParam("comment") String comment) {
        commentariesService.create(comment, taskId);

        return "redirect:/tasks/" + taskId;
    }
}

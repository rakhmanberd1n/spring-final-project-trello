package kz.bitlab.academy.springfinal.folders.controller;

import kz.bitlab.academy.springfinal.categories.service.CategoryService;
import kz.bitlab.academy.springfinal.folders.service.FolderService;
import kz.bitlab.academy.springfinal.tasks.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class FolderController {

    private final FolderService folderService;
    private final CategoryService categoryService;

    @GetMapping("/")
    public String indexPage(Model model) {
        model.addAttribute("folders", folderService.findAll());

        return "index";
    }

    @PostMapping("/")
    public String createFolder(@RequestParam("name") String name) {
        return folderService.create(name);
    }

    @GetMapping("/{id}")
    public String detailsPage(@PathVariable Long id, Model model) {
        model.addAttribute("folder", folderService.findById(id));
        model.addAttribute("categories", categoryService.findAllNotContains(id));

        return "details";
    }

    @PostMapping("/{id}/add-category")
    public String addCategory(@PathVariable Long id,
                              @RequestParam("categoryId") Long categoryId) {
        folderService.addCategory(id, categoryId);

        return "redirect:/" + id;
    }

    @PostMapping("/{id}/remove-category")
    public String removeCategory(@PathVariable Long id,
                                 @RequestParam("categoryId") Long categoryId) {
        folderService.removeCategory(id, categoryId);

        return "redirect:/" + id;
    }

    @PostMapping("/{id}/update")
    public String updateFolder(@PathVariable Long id,
                               @RequestParam("name") String name) {
        folderService.update(id, name);

        return "redirect:/";
    }

    @PostMapping("/{id}")
    public String deleteFolder(@PathVariable Long id) {
        folderService.delete(id);

        return "redirect:/";
    }

}

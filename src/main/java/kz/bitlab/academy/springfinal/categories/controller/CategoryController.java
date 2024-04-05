package kz.bitlab.academy.springfinal.categories.controller;

import kz.bitlab.academy.springfinal.categories.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/categories")
    public String allCategories(Model model) {
        model.addAttribute("categories", categoryService.findAll());

        return "categories";
    }

    @PostMapping("/categories")
    public String addCategory(String name) {
        return categoryService.create(name);
    }

    @PostMapping("/categories/{id}")
    public String renameFolder(@PathVariable Long id,
                               @RequestParam("name") String name) {
        return categoryService.update(id, name);
    }

    @PostMapping("/categories/{id}/delete")
    public String deleteFolder(@PathVariable Long id) {
        categoryService.delete(id);
        return "redirect:/categories";
    }

}

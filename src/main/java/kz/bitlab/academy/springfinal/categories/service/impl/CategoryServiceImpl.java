package kz.bitlab.academy.springfinal.categories.service.impl;

import kz.bitlab.academy.springfinal.categories.entity.CategoryEntity;
import kz.bitlab.academy.springfinal.categories.repository.CategoryRepository;
import kz.bitlab.academy.springfinal.categories.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    @Transactional
    @Override
    public String create(String name) {
        if (repository.existsByName(name))
            return "redirect:/categories?categoryAlreadyExists";

        repository.save(new CategoryEntity(name));
        return "redirect:/categories";
    }

    @Transactional
    @Override
    public String update(Long id, String name) {
        CategoryEntity entity = findById(id);

        if (repository.existsByName(name))
            return "redirect:/categories?categoryAlreadyExists";

        entity.setName(name);
        return "redirect:/categories";
    }

    @Transactional(readOnly = true)
    @Override
    public List<CategoryEntity> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public List<CategoryEntity> findAllNotContains(Long folderId) {
        return repository.findAllNotContains(folderId);
    }

    @Transactional(readOnly = true)
    @Override
    public CategoryEntity findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    @Transactional
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

}

package kz.bitlab.academy.springfinal.folders.service.impl;

import kz.bitlab.academy.springfinal.categories.entity.CategoryEntity;
import kz.bitlab.academy.springfinal.categories.service.CategoryService;
import kz.bitlab.academy.springfinal.folders.entity.FolderEntity;
import kz.bitlab.academy.springfinal.folders.repository.FolderRepository;
import kz.bitlab.academy.springfinal.folders.service.FolderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FolderServiceImpl implements FolderService {

    private final FolderRepository repository;
    private final CategoryService categoryService;

    @Transactional
    @Override
    public String create(String name) {
        if (repository.existsByName(name))
            return "redirect:/?folderAlreadyExists";

        repository.save(new FolderEntity(name));
        return "redirect:/";
    }

    @Transactional
    @Override
    public String update(Long id, String name) {
        FolderEntity entity = findById(id);

        if (repository.existsByName(name))
            return "redirect:/" + id + "?folderAlreadyExists";

        entity.setName(name);
        return "redirect:/" + id;
    }

    @Transactional(readOnly = true)
    @Override
    public List<FolderEntity> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public FolderEntity findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Folder not found"));
    }

    @Transactional
    @Override
    public void addCategory(Long id, Long categoryId) {
        FolderEntity entity = findById(id);
        CategoryEntity category = categoryService.findById(categoryId);

        entity.addCategory(category);
    }

    @Transactional
    @Override
    public void removeCategory(Long id, Long categoryId) {
        FolderEntity entity = findById(id);
        CategoryEntity category = categoryService.findById(categoryId);

        entity.getCategories().removeIf(el -> el.getId().equals(category.getId()));
    }

    @Transactional
    @Override
    public void delete(Long id) {
        repository.delete(id);
    }


}

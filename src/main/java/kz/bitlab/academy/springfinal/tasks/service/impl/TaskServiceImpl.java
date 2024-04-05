package kz.bitlab.academy.springfinal.tasks.service.impl;

import kz.bitlab.academy.springfinal.commentaries.repository.CommentariesRepository;
import kz.bitlab.academy.springfinal.folders.entity.FolderEntity;
import kz.bitlab.academy.springfinal.folders.service.FolderService;
import kz.bitlab.academy.springfinal.tasks.entity.TaskEntity;
import kz.bitlab.academy.springfinal.tasks.repository.TaskRepository;
import kz.bitlab.academy.springfinal.tasks.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository repository;
    private final FolderService folderService;
    private final CommentariesRepository commentariesRepository;

    @Transactional
    @Override
    public void create(String title, String description, Long folderId) {
        FolderEntity folder = folderService.findById(folderId);

        repository.save(new TaskEntity(title, description, folder));
    }

    @Transactional
    @Override
    public void update(Long id, String title, String description, int status) {
        TaskEntity entity = findById(id);

        if (entity.getStatus() == 2 || entity.getStatus() == 3)
            throw new RuntimeException("Task cannot be updated");

        entity.setTitle(title);
        entity.setDescription(description);
        entity.setStatus(status);
    }

    @Transactional(readOnly = true)
    @Override
    public List<TaskEntity> findAllByFolder(Long folderId) {
        return repository.findAllByFolderIdOrderById(folderId);
    }

    @Transactional(readOnly = true)
    @Override
    public TaskEntity findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }

    @Transactional
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }


}

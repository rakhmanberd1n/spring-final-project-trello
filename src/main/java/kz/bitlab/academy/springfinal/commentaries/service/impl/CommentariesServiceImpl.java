package kz.bitlab.academy.springfinal.commentaries.service.impl;

import kz.bitlab.academy.springfinal.commentaries.entity.CommentariesEntity;
import kz.bitlab.academy.springfinal.commentaries.repository.CommentariesRepository;
import kz.bitlab.academy.springfinal.commentaries.service.CommentariesService;
import kz.bitlab.academy.springfinal.tasks.service.impl.TaskServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CommentariesServiceImpl implements CommentariesService {
    private final CommentariesRepository repository;
    private final TaskServiceImpl taskService;

    @Override
    @Transactional
    public void create(String comment, Long taskId) {
        var task = taskService.findById(taskId);

        if (task.hasEditableStatus()) {
            repository.save(new CommentariesEntity(comment, task));
        } else {
            throw new RuntimeException("Task is closed");
        }
    }

    @Override
    @Transactional
    public void update(Long id, String commentText) {
        CommentariesEntity comment = findById(id);

        comment.setComment(commentText);
    }

    @Override
    @Transactional(readOnly = true)
    public CommentariesEntity findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentariesEntity> findAllByTaskId(Long taskId) {
        return repository.findAllByTaskId(taskId);
    }
}

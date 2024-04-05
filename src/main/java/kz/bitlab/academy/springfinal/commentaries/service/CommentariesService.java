package kz.bitlab.academy.springfinal.commentaries.service;

import kz.bitlab.academy.springfinal.commentaries.entity.CommentariesEntity;
import org.springframework.scheduling.config.Task;

import java.util.List;

public interface CommentariesService {
    void create(String comment, Long taskId);

    void update(Long id, String comment);

    CommentariesEntity findById(Long id);

    void delete(Long id);

    List<CommentariesEntity> findAllByTaskId(Long taskId);
}

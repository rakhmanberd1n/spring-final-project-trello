package kz.bitlab.academy.springfinal.commentaries.repository;

import kz.bitlab.academy.springfinal.commentaries.entity.CommentariesEntity;
import kz.bitlab.academy.springfinal.tasks.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentariesRepository extends JpaRepository<CommentariesEntity, Long> {
    List<CommentariesEntity> findAllByTaskId(Long taskId);
    void deleteAllByTask(TaskEntity task);
}
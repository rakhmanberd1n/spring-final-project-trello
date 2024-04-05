package kz.bitlab.academy.springfinal.tasks.repository;

import kz.bitlab.academy.springfinal.tasks.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

    List<TaskEntity> findAllByFolderIdOrderById(Long folderId);

    @Modifying
    @Query(nativeQuery = true,
            value = """
                delete from commentaries where commentaries.task_id = (:taskId);
            """)
    void delete(Long taskId);


}

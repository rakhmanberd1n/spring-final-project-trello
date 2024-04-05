package kz.bitlab.academy.springfinal.folders.repository;

import kz.bitlab.academy.springfinal.folders.entity.FolderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
public interface FolderRepository extends JpaRepository<FolderEntity, Long> {

    boolean existsByName(String name);
    @Transactional
    void deleteById(Long id);

    @Transactional
    void deleteByIdAndTasksFolderId(Long id, Long folderId);
    @Query("""
        select f from FolderEntity f
        order by f.id
        """)
    List<FolderEntity> findAll();

    @Modifying
    @Query(nativeQuery = true,
            value = """
                delete from commentaries where task_id in (select id from tasks where folder_id = (:folderId));
                delete from tasks where tasks.folder_id = (:folderId);
                delete from folders_categories where folders_categories.folder_id = (:folderId);
                delete from folders where folders.id = (:folderId);
            """)
    void delete(Long folderId);





}

package kz.bitlab.academy.springfinal.categories.repository;

import kz.bitlab.academy.springfinal.categories.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    boolean existsByName(String name);

    @Query(nativeQuery = true,
            value = """
                select c.* from categories c
                where not exists(select 1 from folders_categories fc where fc.category_id = c.id and fc.folder_id = :folderId)
                order by id
            """)
    List<CategoryEntity> findAllNotContains(Long folderId);

    @Query("""
        select f from CategoryEntity f
        order by f.id
        """)
    List<CategoryEntity> findAll();

}

package kz.bitlab.academy.springfinal.tasks.entity;

import jakarta.persistence.*;
import kz.bitlab.academy.springfinal.commentaries.entity.CommentariesEntity;
import kz.bitlab.academy.springfinal.folders.entity.FolderEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "status", nullable = false)
    private int status = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "folder_id", nullable = false)
    private FolderEntity folder;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommentariesEntity> commentaries;

    public TaskEntity(String title, String description, FolderEntity folder) {
        this.title = title;
        this.description = description;
        this.folder = folder;
    }

    public boolean hasEditableStatus() {
        return status == 0 || status == 1;
    }

}

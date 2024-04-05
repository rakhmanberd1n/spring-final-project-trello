package kz.bitlab.academy.springfinal.commentaries.entity;


import jakarta.persistence.*;
import kz.bitlab.academy.springfinal.folders.entity.FolderEntity;
import kz.bitlab.academy.springfinal.tasks.entity.TaskEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "commentaries")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentariesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "comment", nullable = false)
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id", nullable = false)
    private TaskEntity task;

    public CommentariesEntity(String comment, TaskEntity task) {
        this.comment = comment;
        this.task = task;
    }
}
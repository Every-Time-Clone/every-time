package com.example.everytime.domain.comments;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments_table")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /*@ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "post_id")
    private Post post;*/

    @Column(columnDefinition = "INT", nullable = false, name = "post_id")
    private int postId;

    @Column(columnDefinition = "INT", nullable = false)
    private int depth;

    @Column(columnDefinition = "VARCHAR(30)", nullable = false, name = "writer_nickname")
    private String writer;  //추후 작성자 연결 시 재고려

    @Column(columnDefinition = "VARCHAR(256)", nullable = false)
    private String contents;

    @Column(columnDefinition = "TINYINT", nullable = false, name = "is_deleted")
    private boolean isDeleted;

    @Column(columnDefinition = "INT", nullable = false)
    private int goods;

    @CreationTimestamp
    @Column(nullable = false, name = "uploaded_date")
    private LocalDateTime uploadDate;

    @UpdateTimestamp
    @Column(nullable = false, name = "modified_date")
    private LocalDateTime modifiedDate;

    @Column(nullable = false, name = "deleted_date")
    private LocalDateTime deleteDate;

    @Builder
    public Comment(int postId, String contents, String writer, int goods) {
        this.postId = postId;
        this.contents = contents;
        this.writer = writer;
        this.goods = goods;
    }

    public void update(@NotNull String contents) {
        if(!contents.isEmpty()) {
            this.contents = contents;
        }
    }

    public void delete(boolean isDeleted) {
        this.isDeleted = isDeleted;
        this.deleteDate = LocalDateTime.now();
    }
}

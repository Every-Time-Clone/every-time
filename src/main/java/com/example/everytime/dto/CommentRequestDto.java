package com.example.everytime.dto;

import com.example.everytime.domain.comments.Comment;
import lombok.*;

@Data
@NoArgsConstructor
public class CommentRequestDto {
    private String contents;
    private String writer;      //임시 작성자
    private int postId;      //임시 포스트 ID

    //private User user;
    //private Posts posts;

    public CommentRequestDto(String contents){
        this.contents = contents;
    }

    public Comment toEntity() {
        return Comment.builder()
                .postId(postId)
                .contents(contents)
                .writer(writer)
                .build();
    }
}


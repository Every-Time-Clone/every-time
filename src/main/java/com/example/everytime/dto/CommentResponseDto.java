package com.example.everytime.dto;

import com.example.everytime.domain.comments.Comment;
import lombok.Getter;

@Getter
public class CommentResponseDto {

    private int id;
    private String contents;
    private String writer;
    private int postsId;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.contents = comment.getContents();
        this.writer = comment.getWriter(); //comment.getUser().getNickname();
        this.postsId = comment.getPostId(); //comment.getPosts().getId();
    }
}
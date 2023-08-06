package com.example.everytime.service;

import com.example.everytime.domain.comments.Comment;
import com.example.everytime.dto.CommentRequestDto;
import com.example.everytime.dto.CommentResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {
    List<CommentResponseDto> getCommentList(int postId);   // return all comments
    int createComment(CommentRequestDto item);             // return postId
    CommentResponseDto updateComment(int commentId, Comment comment);
    boolean deleteComment(int commentId);
}

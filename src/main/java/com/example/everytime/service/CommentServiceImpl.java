package com.example.everytime.service;

import com.example.everytime.domain.comments.Comment;
import com.example.everytime.domain.comments.CommentRepository;
import com.example.everytime.dto.CommentRequestDto;
import com.example.everytime.dto.CommentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public List<CommentResponseDto> getCommentList(int postId) {
        List<Comment> comments = commentRepository.findByPostIdAndIsDeleted(postId, false);
        List<CommentResponseDto> response = new ArrayList<>();

        if(!comments.isEmpty()){
            response = comments.stream().map(
                    item -> new CommentResponseDto(item)
            ).collect(Collectors.toList());
        }

        return response;
    }


    @Override
    @Transactional
    public int createComment(CommentRequestDto item) {
        try {
            return commentRepository.save(item.toEntity()).getId();
        }catch (DataAccessException e){
            throw new CommentServiceImpl.CommentCreationException("댓글 등록에 실패하였습니다.");
        }
    }

    @Override
    @Transactional
    public CommentResponseDto updateComment(int commentId, Comment request) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다. Comment Id = " + commentId));

        //contents가 null이 아닌 경우 업데이트 진행
        if(request.getContents() != null) {
            String contests = Optional.ofNullable(request.getContents()).orElse(comment.getContents());
            comment.update(contests);
        } else {
            throw new IllegalArgumentException("내용을 입력해주세요.");
        }
        return new CommentResponseDto(commentRepository.save(comment));
    }

    @Override
    @Transactional
    public boolean deleteComment(int commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new NoSuchElementException("해당 코멘트가 없습니다. Comment Id = " + commentId));

        if(!comment.isDeleted()){
            comment.delete(true);
        }

        return comment.isDeleted();
    }

    public class CommentCreationException extends RuntimeException {
        public CommentCreationException(String message) {
            super(message);
        }
    }

    public class CommentNotFoundException extends RuntimeException {
        public CommentNotFoundException(String message) {
            super(message);
        }
    }
}

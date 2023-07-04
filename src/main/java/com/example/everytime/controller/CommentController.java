package com.example.everytime.controller;

import com.example.everytime.dto.CommentRequestDto;
import com.example.everytime.dto.CommentResponseDto;
import com.example.everytime.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("comments")
public class CommentController {

    final private CommentService commentService;
    final private Validator validator;

    @GetMapping("{postId}") //모든 리소스를 가져옵니다
    public ResponseEntity<List<CommentResponseDto>> commentList(@PathVariable("postId") int postId){
        List<CommentResponseDto> comments = commentService.getCommentList(postId);
        if(!comments.isEmpty()){
            return ResponseEntity.ok(comments);
        }else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping()
    public ResponseEntity createComment(@RequestBody() @Valid CommentRequestDto item) {
        Set<ConstraintViolation<CommentRequestDto>> violation = validator.validate(item);
        if(!violation.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.accepted().body(commentService.createComment(item));
    }
}

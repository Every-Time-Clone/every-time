package com.example.everytime.controller;

import com.example.everytime.dto.CommentRequestDto;
import com.example.everytime.dto.CommentResponseDto;
import com.example.everytime.response.DefaultRes;
import com.example.everytime.response.ResponseMessage;
import com.example.everytime.response.StatusCode;
import com.example.everytime.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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

    @PatchMapping("{commentId}")//게시글을 수정합니다
    public ResponseEntity updateComment(@PathVariable int commentId , @RequestBody() @Valid CommentRequestDto request, BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.FAIL_DEFAUL_RES,bindingResult.getAllErrors()),HttpStatus.BAD_REQUEST);
        }

        CommentResponseDto updatedComment = commentService.updateComment(commentId, request.toEntity());

        if (updatedComment == null) {
            return new ResponseEntity(DefaultRes.res(StatusCode.NOT_FOUND, ResponseMessage.NO_POST, ""), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.UPDATE_COMMENT, updatedComment), HttpStatus.OK);
    }

    @DeleteMapping("{commentId}")//게시글을 삭제합니다
    public ResponseEntity deleteComment(@PathVariable("commentId") int commentId) {
        boolean is_deleted = commentService.deleteComment(commentId);

        if(is_deleted){
            return new ResponseEntity(DefaultRes.res(StatusCode.OK,ResponseMessage.DELETE_COMMENT,""),HttpStatus.OK);
        }else{
            return new ResponseEntity(DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR,ResponseMessage.DELETE_COMMENT_FAILED,""),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}


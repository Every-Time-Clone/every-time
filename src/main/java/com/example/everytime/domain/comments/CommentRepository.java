package com.example.everytime.domain.comments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository <Comment, Integer> {
    List<Comment> findByPostIdAndIsDeleted(int postId, boolean isDeleted);
}

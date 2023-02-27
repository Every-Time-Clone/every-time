package com.example.everytime.dto;

import com.example.everytime.domain.posts.Post;
import lombok.Builder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public class PostUpdateRequestDto {

    @NotNull(message = "제목은 필수 입력 항목입니다.")
    private String title;
    @NotNull(message = "내용은 필수 입력 항목입니다.")
    private String contents;

    public PostUpdateRequestDto(String title, String contents){
        this.title = title;
        this.contents = contents;
    }

    public Post toEntity(){
        return Post.builder()
                .title(title)
                .contents(contents)
                .build();
    }

}

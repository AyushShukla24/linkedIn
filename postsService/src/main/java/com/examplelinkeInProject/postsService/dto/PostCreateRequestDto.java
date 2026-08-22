package com.examplelinkeInProject.postsService.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostCreateRequestDto {
    private String content;
}

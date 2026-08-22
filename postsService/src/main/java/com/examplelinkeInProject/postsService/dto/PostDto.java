package com.examplelinkeInProject.postsService.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostDto {
    private Long id;
    private Long userId;
    private String content;
    private LocalDateTime createdAt;
}

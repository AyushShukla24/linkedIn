package com.examplelinkeInProject.postsService.controller;

import com.examplelinkeInProject.postsService.dto.PostLikeRequestDto;
import com.examplelinkeInProject.postsService.service.PostLikeService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/likes")
public class postLikeController {
    private final PostLikeService postLikeService;

    @PostMapping
    public ResponseEntity<Void> likePost(@RequestBody PostLikeRequestDto postLikeRequestDto) {
        postLikeService.likePost(postLikeRequestDto.getPostId(), 1L);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/unlike")
    public ResponseEntity<Void> unlikePost(@RequestBody PostLikeRequestDto postLikeRequestDt) {
        postLikeService.unlikePost(postLikeRequestDt.getPostId(), 1L);
        return ResponseEntity.noContent().build();
    }
}

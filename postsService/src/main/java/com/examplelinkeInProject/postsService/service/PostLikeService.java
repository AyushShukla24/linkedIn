package com.examplelinkeInProject.postsService.service;

import com.examplelinkeInProject.postsService.entity.PostEntity;
import com.examplelinkeInProject.postsService.entity.PostLike;
import com.examplelinkeInProject.postsService.exception.BadRequestException;
import com.examplelinkeInProject.postsService.exception.ResourceNotFoundException;
import com.examplelinkeInProject.postsService.repository.PostLikeRepository;
import com.examplelinkeInProject.postsService.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostLikeService {

    private final PostLikeRepository postLikeRepository;
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public void likePost(Long postId, Long userId) {
        postRepository.findById(postId)
                .orElseThrow(()-> new ResourceNotFoundException("No post found with id: "+postId));

        boolean isPostAlreadyLiked = postLikeRepository.existsByUserIdAndPostId(userId,postId);

        if(isPostAlreadyLiked) throw new BadRequestException("Post Already Liked");

        PostLike postLike = new PostLike();
        postLike.setPostId(postId);
        postLike.setUserId(userId);

        postLikeRepository.save(postLike);

        //TODO: send notifications to owner of post
    }

    @Transactional
    public void unlikePost(Long postId, Long userId) {
        postRepository.findById(postId)
                .orElseThrow(()-> new ResourceNotFoundException("No post found with id: "+postId));

        boolean isPostAlreadyLiked = postLikeRepository.existsByUserIdAndPostId(userId,postId);

        if(!isPostAlreadyLiked) throw new BadRequestException("Cannot unliked the Post which is not liked");

        postLikeRepository.deleteByUserIdAndPostId(postId,userId);
    }
}

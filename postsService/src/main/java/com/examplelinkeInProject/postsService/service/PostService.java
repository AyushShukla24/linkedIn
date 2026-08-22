package com.examplelinkeInProject.postsService.service;

import com.examplelinkeInProject.postsService.dto.PostCreateRequestDto;
import com.examplelinkeInProject.postsService.dto.PostDto;
import com.examplelinkeInProject.postsService.entity.PostEntity;
import com.examplelinkeInProject.postsService.exception.ResourceNotFoundException;
import com.examplelinkeInProject.postsService.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.lang.Long;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {

    private final ModelMapper modelMapper;
    private final PostRepository postRepository;

    public PostDto createPost(PostCreateRequestDto postCreateRequestDto, Long userId) {
        PostEntity postEntity = modelMapper.map(postCreateRequestDto,PostEntity.class);
        postEntity.setUserId(userId);

        return modelMapper.map(postRepository.save(postEntity),PostDto.class);
    }


    public PostDto getPost(Long postId) {
        PostEntity postEntity = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("No post found with id: "+ postId));
        return modelMapper.map(postEntity,PostDto.class);

    }

    public List<PostDto> getAllPostsOfUsers(Long userId) {
        List<PostEntity> postEntity = postRepository.findByUserId(userId);

        return postEntity
                .stream()
                .map(post -> modelMapper.map(post,PostDto.class))
                .toList();
    }
}

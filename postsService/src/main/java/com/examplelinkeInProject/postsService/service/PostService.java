package com.examplelinkeInProject.postsService.service;

import com.examplelinkeInProject.postsService.client.ConnectionServiceClient;
import com.examplelinkeInProject.postsService.dto.PersonDto;
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

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {

    private final ModelMapper modelMapper;
    private final PostRepository postRepository;
    private final ConnectionServiceClient connectionServiceClient;

    public PostDto createPost(PostCreateRequestDto postCreateRequestDto, Long userId) {
        PostEntity postEntity = modelMapper.map(postCreateRequestDto,PostEntity.class);
        postEntity.setUserId(userId);

        return modelMapper.map(postRepository.save(postEntity),PostDto.class);
    }


    public PostDto getPost(Long postId, Long userId) {

        // TODO: remove in future
        // we call connection service from user service to pass userId in header

        List<PersonDto> personDtoList = connectionServiceClient.getAllFirstDegreeConnections(userId);

        log.info("First degree connections count = {}", personDtoList.size());

        personDtoList.forEach(p ->
                log.info("data {}", p.getName())
        );

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

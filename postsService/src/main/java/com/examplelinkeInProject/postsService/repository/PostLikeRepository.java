package com.examplelinkeInProject.postsService.repository;

import com.examplelinkeInProject.postsService.entity.PostEntity;
import com.examplelinkeInProject.postsService.entity.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    boolean existsByUserIdAndPostId(Long userId, Long postId);

    void deleteByUserIdAndPostId(Long postId, Long userId);
}

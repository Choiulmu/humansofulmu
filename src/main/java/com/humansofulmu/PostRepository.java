package com.humansofulmu;

import com.humansofulmu.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value = "SELECT id, content FROM posts", nativeQuery = true)
    List<PostResponseDTO> findAllPosts();

    @Query(value = "SELECT id, content FROM posts WHERE id = :id", nativeQuery = true)
    Optional<PostResponseDTO> findPostById(@Param("id") long id);
}

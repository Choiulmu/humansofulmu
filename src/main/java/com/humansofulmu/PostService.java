package com.humansofulmu;

import com.humansofulmu.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepo;

    public List<PostResponseDTO> getAllPosts() {
        return postRepo.findAllPosts();
    }

    public PostResponseDTO getPostById(long id) {
        return postRepo.findPostById(id)
                .orElseThrow(() -> new NoSuchElementException("No Post: " + id));
    }

    @Transactional
    public void createPost(PostDTO postDTO) {
        Post post = postDTO.toEntity();
        postRepo.save(post);
    }

    @Transactional
    public void updatePostUser(long id, String userName) {
        Post post = postRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No Post: " + id));

        post.setUser(userName);
    }

    @Transactional
    public void updatePostContent(long id, String content) {
        Post post = postRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No Post: " + id));

        post.setContent(content);
    }

    @Transactional
    public void deletePost(long id) {
        boolean isExist = postRepo.existsById(id);

        if(isExist) {
            postRepo.deleteById(id);
        } else {
            throw new NoSuchElementException("No Post: " + id);
        }
    }
}

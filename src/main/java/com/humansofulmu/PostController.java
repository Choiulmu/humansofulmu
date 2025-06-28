package com.humansofulmu;

import com.humansofulmu.common.resonse.ResultVO;
import com.humansofulmu.common.resonse.SuccessCode;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
@Tag(name = "Posts API", description = "블로그 post 관련 API")
public class PostController {

    private final PostService postService;

    @GetMapping("")
    public ResponseEntity<ResultVO<List<PostResponseDTO>>> getAllPosts() {
        List<PostResponseDTO> posts = postService.getAllPosts();
        return ResponseEntity.ok(new ResultVO<>(SuccessCode.SELECT_SUCCESS, posts));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResultVO<PostResponseDTO>> getPost(@PathVariable long id) {
        PostResponseDTO post = postService.getPostById(id);
        return ResponseEntity.ok(new ResultVO<>(SuccessCode.SELECT_SUCCESS, post));
    }


    @PostMapping("")
    public ResponseEntity<ResultVO<Void>> createPost(@RequestBody PostRequestDTO postRequestDTO) {
        postService.createPost(postRequestDTO);
        return ResponseEntity.ok(new ResultVO<>(SuccessCode.INSERT_SUCCESS));
    }

    @PutMapping("/{id}/user")
    public ResponseEntity<ResultVO<Void>> updatePostUser(@PathVariable long id, @RequestParam String userName) {
        postService.updatePostUser(id, userName);
        return ResponseEntity.ok(new ResultVO<>(SuccessCode.UPDATE_SUCCESS));
    }


    @PutMapping("/{id}/content")
    public ResponseEntity<ResultVO<Void>> updatePostContent(@PathVariable long id, @RequestParam String content) {
        postService.updatePostContent(id, content);
        return ResponseEntity.ok(new ResultVO<>(SuccessCode.UPDATE_SUCCESS));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ResultVO<Void>> deletePost(@PathVariable long id) {
        postService.deletePost(id);
        return ResponseEntity.ok(new ResultVO<>(SuccessCode.DELETE_SUCCESS));
    }
}

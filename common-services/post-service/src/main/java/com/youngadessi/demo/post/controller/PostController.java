package com.youngadessi.demo.post.controller;

import com.youngadessi.demo.post.model.dto.PostDTO;
import com.youngadessi.demo.post.model.entity.Post;
import com.youngadessi.demo.post.model.mapper.PostMapper;
import com.youngadessi.demo.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.stream.Collectors;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    private final PostService postService;

    private static final PostMapper POST_MAPPER = Mappers.getMapper(PostMapper.class);

    /*@GetMapping(value = "/{id}")
    public ResponseEntity<Post> getSamplePost(@PathVariable Long id) {
        return new ResponseEntity(HttpStatus.OK);
    }*/

    @GetMapping(value = "/getOK")
    public HttpStatus getSamplePost() {
        return HttpStatus.OK;
    }

    @GetMapping(value = "/all")
    public List<PostDTO> getAllPosts() {
        List<Post> allPosts = postService.getAllPosts();
        return allPosts.stream().map(POST_MAPPER::toDto).collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    public PostDTO getPost(@PathVariable @Min(1) Long id) {
        return POST_MAPPER.toDto(postService.getPost(id));
    }

    @PostMapping(value = "/create")
    public void savePost(@Valid @RequestBody PostDTO post) {
        postService.addPost(POST_MAPPER.toEntity(post));
    }

    @PutMapping(value = "/update/{id}")
    public PostDTO updatePost(@Valid @RequestBody Post post) {
        if (post.getId() == null) {
            throw new RuntimeException("Post id can not be null for update!");
        }
        return POST_MAPPER.toDto(postService.updatePost(post));
    }

    @DeleteMapping(value = "/delete")
    public boolean deletePost(@RequestParam @Min(1) Long id) {
        return postService.deletePost(id);
    }
}
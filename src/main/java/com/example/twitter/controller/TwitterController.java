package com.example.twitter.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import com.example.twitter.model.Post;
import com.example.twitter.model.PostRequest;
import com.example.twitter.model.Stream;
import com.example.twitter.model.User;

@RestController
@RequestMapping("/twitter")
public class TwitterController {
    private final Stream stream = new Stream();

    @PostMapping("/createPost")
    public ResponseEntity<?> createPost(@RequestBody PostRequest postRequest, @AuthenticationPrincipal Jwt jwt) {

        if (jwt == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Debe iniciar sesión para publicar tweets.");
        }

        User user = new User();
        user.setId(jwt.getSubject());
        user.setUsername(jwt.getClaim("cognito:username"));

        Post post = new Post(UUID.randomUUID().toString(), postRequest.getText(), user);
        stream.addPost(post);
        return ResponseEntity.ok(post);
    }

    @GetMapping("/getPosts")
    public List<Post> getPosts() {
        List<Post> posts = stream.getPosts();
        return posts != null ? posts : new ArrayList<>();
    }

}



package com.unica.rest.webservices.restful_web_services.posts;

//import NoContentException;
import com.unica.rest.webservices.restful_web_services.repository.PostRepository;
import com.unica.rest.webservices.restful_web_services.repository.UserRepository;
import com.unica.rest.webservices.restful_web_services.user.User;
import com.unica.rest.webservices.restful_web_services.user.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class PostController {
    @Autowired
    private PostServices postServices;

    @Autowired
    private PostRepository postRepo;

    @Autowired
    private UserRepository userRepository;

    @GetMapping(path="/view_all/{id}/posts")
    public List<Post> userPostsList(@PathVariable Integer id){
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()){
//            System.out.println("this user has no post yet");
            throw new UserNotFoundException("User with ID: "+id+" Not found");
        }
        return user.get().getPosts();
    }

    @PostMapping(path = "/view_all/{id}/post")
    public ResponseEntity<Object> user(@PathVariable Integer id, @RequestBody Post post){
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()){
            throw new UserNotFoundException("User with ID: "+id+" Not Found");
        }
        User retrievedUser = user.get();

        post.setUser(retrievedUser);

        postRepo.save(post);

        URI location = ServletUriComponentsBuilder.
                fromCurrentRequest().path("/{id}").
                buildAndExpand(post.getId()).
                toUri();


        return ResponseEntity.created(location).build();
    }

}

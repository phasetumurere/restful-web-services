package com.unica.rest.webservices.restful_web_services.user;

import com.unica.rest.webservices.restful_web_services.repository.UserRepository;
import com.unica.rest.webservices.restful_web_services.user.exception.UserNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJpaController {
    @Autowired
    UserDaoService service;

    @Autowired
    UserRepository repo;
    @GetMapping(path = "/jpa/view_all")
//    public EntityModel<List<User>> retrieveAllUsers(){
    public List<User> retrieveAllUsers() {
        List<User> users = repo.findAll();
        User user1 = users.get(1);
//        EntityModel<List<User>> view_all = EntityModel.of(users);
//        WebMvcLinkBuilder linkToAddUser = linkTo(methodOn(this.getClass()).createUser(user1));
//        view_all.add(linkToAddUser.withRel("Add-new-User"));
//        return (EntityModel<User>) service.findAll();
        return users;
    }

    //    Specific user
    @GetMapping(path = "/jpa/view_all/{id}")
    public EntityModel<User> getUser(@PathVariable int id) {
        Optional<User> user = repo.findById(id);
        if (!user.isPresent()) {
            throw new UserNotFoundException("id: " + id);
        }
        EntityModel<User> userEntityModel = EntityModel.of(user.get());

        WebMvcLinkBuilder linkToUsers = linkTo(methodOn(this.getClass()).retrieveAllUsers());

        userEntityModel.add(linkToUsers.withRel("all-users"));

        WebMvcLinkBuilder linkToDeleteThisUsers = linkTo(methodOn(this.getClass()).deleteUser(id));

        userEntityModel.add(linkToDeleteThisUsers.withRel("DELETE-User "+id));

        return userEntityModel;
    }

    @PostMapping(path = "/jpa/view_all")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User savedUser = repo.save(user);
        URI location = ServletUriComponentsBuilder.
                fromCurrentRequest().path("/{id}").
                buildAndExpand(savedUser.getId()).
                toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "/jpa/view_all/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable int id){
//    public Object deleteUser(@PathVariable int id) {

        if (!repo.existsById(id)) {
            throw new UserNotFoundException("id: " + id);
        }
        else if (repo.existsById(id)) {
            repo.deleteById(id);
            return ResponseEntity.noContent().build();
        }
//        return ResponseEntity.noContent().build();
        return null;
    }

}
//    public ResponseEntity<User> deleteUser(@PathVariable int id){
//            return ResponseEntity.noContent().build();

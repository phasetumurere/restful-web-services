package com.unica.rest.webservices.restful_web_services.user;

import com.unica.rest.webservices.restful_web_services.user.exception.UserNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserDaoService service;

    //    All users
    @GetMapping(path = "/view_all")
//    public EntityModel<List<User>> retrieveAllUsers(){
    public List<User> retrieveAllUsers() {
        List<User> users = service.findAll();
        User user1 = users.get(1);
//        EntityModel<List<User>> view_all = EntityModel.of(users);
//        WebMvcLinkBuilder linkToAddUser = linkTo(methodOn(this.getClass()).createUser(user1));
//        view_all.add(linkToAddUser.withRel("Add-new-User"));
//        return (EntityModel<User>) service.findAll();
        return users;
    }

    //    Specific user
    @GetMapping(path = "view_all/{id}")
    public EntityModel<User> getUser(@PathVariable int id) {
        User user = service.findOne(id);
        if (user == null) {
            throw new UserNotFoundException("id: " + id);
        }
        EntityModel<User> userEntityModel = EntityModel.of(user);

        WebMvcLinkBuilder linkToUsers = linkTo(methodOn(this.getClass()).retrieveAllUsers());

        userEntityModel.add(linkToUsers.withRel("all-users"));

        WebMvcLinkBuilder linkToDeleteThisUsers = linkTo(methodOn(this.getClass()).deleteUser(id));

        userEntityModel.add(linkToDeleteThisUsers.withRel("DELETE-User "+id));

        return userEntityModel;
    }

    @PostMapping(path = "/view_all")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User savedUser = service.save(user);
        URI location = ServletUriComponentsBuilder.
                fromCurrentRequest().path("/{id}").
                buildAndExpand(savedUser.getId()).
                toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "/view_all/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable int id){
//    public Object deleteUser(@PathVariable int id) {
        User deletedUser = service.deleteById(id);

        if (deletedUser == null) {
            throw new UserNotFoundException("id: " + id);
        }
        return ResponseEntity.noContent().build();
    }

}
//    public ResponseEntity<User> deleteUser(@PathVariable int id){
//            return ResponseEntity.noContent().build();

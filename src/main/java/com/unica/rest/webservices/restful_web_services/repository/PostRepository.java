package com.unica.rest.webservices.restful_web_services.repository;

import com.unica.rest.webservices.restful_web_services.posts.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

}

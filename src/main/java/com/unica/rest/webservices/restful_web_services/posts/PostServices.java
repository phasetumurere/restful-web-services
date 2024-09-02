package com.unica.rest.webservices.restful_web_services.posts;

//import NoContentException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PostServices {

    private static List<Post> posts = new ArrayList<>();


    public List<Post>findAllPosts(){
        return posts;
    }
}

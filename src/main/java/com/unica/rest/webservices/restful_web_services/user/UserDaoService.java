package com.unica.rest.webservices.restful_web_services.user;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class UserDaoService {

    private static List<User> users = new ArrayList<>();
    private static int userCount =3;

    static {
        users.add(new User(1, "Phase", new Date()));
        users.add(new User(2, "Joseph", new Date()));
        users.add(new User(3, "Daniel", new Date()));
    }

//    Return All

    public List<User> findAll() {
        return users;
    }

//    Save User

    public User save(User user){
        if(user.getId() == null){
            user.setId(++userCount);
        }
        users.add(user);
        return user;
    }
//    Find one
    public User findOne(int id){
        for(User user:users){
            if(user.getId() == id){
                return user;
            }
        }
        return null;
    }
    public User deleteById(int id){
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()){
            User user = iterator.next();
            if (user.getId() == id){
                iterator.remove();
                return user ;
            }
        }
        return null;
    }


}

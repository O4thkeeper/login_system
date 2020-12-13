package com.example.login_system;

import com.example.login_system.entity.User;
import com.example.login_system.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserTest {

    @Autowired
    UserService userService;

    @Test
    public void listTest() {
        List<User> allUsers = userService.findAllUsers();
        for (User allUser : allUsers) {
            System.out.println(allUser);
        }
    }

    @Test
    public void addTest() {
        userService.addUser("c", "c", "3");
    }

    @Test
    public void updateTest(){
        userService.updateUser(3,"d","4");
    }

    @Test
    public void phoneTest(){
        System.out.println(userService.findUserByPhone("1"));
    }

}

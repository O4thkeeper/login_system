package com.example.login_system.service;

import com.example.login_system.dao.UserMapper;
import com.example.login_system.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public boolean userExists(String username) {
        return userMapper.findUserByName(username) != null;
    }

    public boolean userAuthenticate(String username, String password) {
        return userMapper.findUserByNameAndPassword(username, password) != null;
    }

    public void updatePassword(String username, String newPass) {
        userMapper.updatePassword(username, newPass);
    }

    public void updateUser(int id, String username, String phone) {
        userMapper.updateUser(id, username, phone);
    }

    public void rootUpdateUser(int id,String password,String phone){
        userMapper.rootUpdateUser(id, password, phone);
    }

    public List<User> findAllUsers() {
        return userMapper.findAllUsers();
    }

    public void addUser(String username, String password, String phone) {
        userMapper.addUser(username, password, phone);
    }

    public void deleteById(int id) {
        userMapper.deleteById(id);
    }

    public User findUserById(int id) {
        return userMapper.findUserById(id);
    }

    public User findUserByPhone(String phone) {
        return userMapper.findUserByPhone(phone);
    }
}

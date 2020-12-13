package com.example.login_system.dao;

import com.example.login_system.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    public User findUserByName(String username);

    public User findUserById(int id);

    public User findUserByPhone(String phone);

    public User findUserByNameAndPassword(String username, String password);

    public void updatePassword(String username, String password);

    public void updateUser(int id,String username,String phone);

    public void rootUpdateUser(int id,String password,String phone);

    public List<User> findAllUsers();

    public void addUser(String username, String password, String phone);

    public void deleteById(int id);

}

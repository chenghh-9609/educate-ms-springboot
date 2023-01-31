package com.example.demo.dao;

import java.util.List;

import com.example.demo.model.User;
public interface UserDAO {
  public int register(String name, String pwd);
  public int login(String name, String pwd);
  public List<User> getAllUsers();
  public int delete(int id);
  public int update(Integer id, String name, String pwd);
  public User getUser(int id);
}

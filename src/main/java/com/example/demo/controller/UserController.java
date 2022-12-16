package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.User;
import com.example.demo.dao.UserDAO;

@CrossOrigin
@RestController
public class UserController {
  @Autowired
  private UserDAO userDAO;

  @RequestMapping("/getalluser")
  public List<User> getAllUser() {
    return userDAO.getAllUsers();
  }

  @RequestMapping(value = "/getuser/{id}", method = RequestMethod.GET)
  public Map<String, User> getUser(@PathVariable("id") Integer id) {
    User u = userDAO.getUser(id);
    Map<String, User> res = new HashMap<>();
    res.put("user", u);
    return res;
  }

  @PostMapping("/register")
  public int register(@RequestParam(value = "id", required = false) String name,
      @RequestParam(value = "password", required = false) String password) {
    return userDAO.register(name, password);
  }

  @PostMapping("/login")
  public int login(@RequestParam(value = "name", required = false) String name,
      @RequestParam(value = "password", required = false) String password) {
    return userDAO.login(name, password);
  }

  @PostMapping("/updateuser")
  public Map<String, String> update(@RequestBody Map<String, Object> params) {
    Map<String, String> res = new HashMap<>();
    int id = (int) params.get("id");
    String name = (String) params.get("name");
    String password = (String) params.get("password");
    System.out.println(id + "," + name + "," + password);
    if (userDAO.update(id, name, password) > 0) {
      res.put("msg", "修改成功！");
    } else {
      res.put("msg", "修改失败！");
    }
    return res;
  }
}

package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.StudentDAO;

@CrossOrigin
@RestController
public class StudentController {
  @Autowired
  private StudentDAO dao;

  @RequestMapping("/api/getstudentlist")
  public Map<String, Object> getAllStudents() {
    Map<String, Object> res = new HashMap<>();
    res.put("data", dao.getAll());
    return res;
  }
}

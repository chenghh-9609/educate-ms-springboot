package com.example.demo.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dao.TeacherDAO;
import com.example.demo.model.Teacher;

@CrossOrigin
@RestController
public class TeacherController {
  @Autowired
  private TeacherDAO dao;

  @RequestMapping("/api/getteacherlist")
  public Map<String, Object> getAllTeachers() {
    Map<String, Object> res = new HashMap();
    res.put("data", dao.getAll());
    return res;
  }

  // 提交包含文件的表单至数据库
  @PostMapping("/api/createteacher")
  public boolean createTeacher(
      @RequestParam(value = "name", required = true) String name,
      @RequestParam(value = "avatar", required = true) MultipartFile avatar,
      @RequestParam(value = "desc", required = false) String desc,
      @RequestParam(value = "detail", required = false) String detail) {
    try {
      String fileName = avatar.getOriginalFilename();
      String prefix = fileName.substring(fileName.lastIndexOf("."));
      File file = File.createTempFile(fileName, prefix);
      avatar.transferTo(file);
      Map<String, Object> res = new HashMap<>();
      boolean result = dao.createTeacher(name, file, desc, detail);
      res.put("data", result);
      return result;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

}

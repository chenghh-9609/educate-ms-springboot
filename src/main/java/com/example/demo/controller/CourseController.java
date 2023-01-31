package com.example.demo.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dao.CourseDAO;

@CrossOrigin
@RestController
public class CourseController {
  @Autowired
  CourseDAO dao;

  @RequestMapping("/api/getcourselist")
  public Map<String, Object> getAllCourses() {
    Map<String, Object> res = new HashMap<>();
    res.put("data", dao.getAll());
    return res;
  }

  @PostMapping("/api/createcourse")
  public Map<String, Object> createCourse(
      @RequestParam(value = "name", required = true) String name,
      @RequestParam(value = "cover", required = true) MultipartFile cover,
      @RequestParam(value = "status", required = true) String status,
      @RequestParam(value = "price", required = true) int price,
      @RequestParam(value = "courseDesc", required = true) String courseDesc,
      @RequestParam(value = "teacherDesc", required = true) String teacherDesc,
      @RequestParam(value = "courseContent", required = true) String courseContent) {
    Map<String, Object> res = new HashMap<>();
    String filename = cover.getOriginalFilename();
    try {
      byte[] b = cover.getBytes();
      File file = new File(".\\image");
      if (!file.exists()) {
        file.mkdirs();
      }
      FileOutputStream out = new FileOutputStream(file + File.separator + filename);
      out.write(b, 0, b.length);
      out.close();
      res.put("data",
          dao.createCourse(name, out, filename, status, price, "admin", courseDesc, teacherDesc, courseContent));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return res;
  }

  @RequestMapping("/updateimage")
  public void updateImage() {
    dao.updateImage();
  }
}

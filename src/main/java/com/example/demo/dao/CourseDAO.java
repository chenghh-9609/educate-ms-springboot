package com.example.demo.dao;

import java.io.FileOutputStream;
import java.util.List;

import com.example.demo.model.Course;

public interface CourseDAO {

  public List<Course> getAll();

  public boolean createCourse(String name, FileOutputStream cover, String filename, String status, int price,
      String uploader,
      String courseDesc,
      String teacherDesc,
      String courseConten);

  public void updateImage();
}

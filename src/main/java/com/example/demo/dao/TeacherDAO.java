package com.example.demo.dao;

import java.io.File;
import java.util.List;

import com.example.demo.model.Teacher;

public interface TeacherDAO {
  public List<Teacher> getAll();

  public boolean createTeacher(String name, File blob, String desc, String detail);
}

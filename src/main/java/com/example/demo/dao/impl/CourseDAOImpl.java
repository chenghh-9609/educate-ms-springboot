package com.example.demo.dao.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.CourseDAO;
import com.example.demo.model.Course;

@Repository
public class CourseDAOImpl implements CourseDAO {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public boolean createCourse(String name, FileOutputStream cover, String filename, String status, int price,
      String uploader,
      String courseDesc,
      String teacherDesc,
      String courseContent) {
    String sql = "insert into course (name, cover,coverName, status, price, uploader, time, courseDesc, teacherDesc, courseContent) values(?,?,?,?,?,?,?,?,?,?)";
    Object[] params = new Object[] { name, cover, filename, status, price, uploader, new Date(), courseDesc,
        teacherDesc,
        courseContent };
    int res = jdbcTemplate.update(sql, params);
    return res != 0;
  }

  @Override
  public List<Course> getAll() {
    String sql = "select c.id, c.name, c.cover,c.coverName, c.status, c.price, o.sales, c.uploader, c.time from course c, (select coursename, count(coursename) sales from orders group by coursename) o where c.name = o.coursename;";
    List<Course> res = jdbcTemplate.query(sql, new RowMapper<Course>() {

      @Override
      @Nullable
      public Course mapRow(ResultSet rs, int i) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String status = rs.getString("status");
        int price = rs.getInt("price");
        int sales = rs.getInt("sales");
        String uploader = rs.getString("uploader");
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rs.getTimestamp("time"));
        String filename = rs.getString("coverName");
        try {
          File file = new File(".\\image" + File.separator + filename);
          FileInputStream fis = new FileInputStream(file);
          return new Course(id, name, fis, status, price, sales, uploader, time);
        } catch (Exception e) {
          System.out.println("文件错误");
          e.printStackTrace();
        }
        return null;
      }

    });

    return res;
  }

  @Override
  public void updateImage() {
    String sql = "update course set cover = ? where id = 4";
    FileInputStream bytes = null;
    try {
      File file = new File("E:\\Download\\主页2\\ruowang.png");
      bytes = new FileInputStream(file);
    } catch (Exception e) {
      e.printStackTrace();
    }
    jdbcTemplate.update(sql, new Object[] { bytes });

  }

}

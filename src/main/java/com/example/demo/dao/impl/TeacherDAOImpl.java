package com.example.demo.dao.impl;

import java.io.File;
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

import com.example.demo.dao.TeacherDAO;
import com.example.demo.model.Teacher;

@Repository
public class TeacherDAOImpl implements TeacherDAO {
  @Autowired
  JdbcTemplate jdbcTemplate;

  @Override
  public List<Teacher> getAll() {
    String sql = "select * from teacher";
    List<Teacher> list = jdbcTemplate.query(sql, new RowMapper<Teacher>() {

      @Override
      @Nullable
      public Teacher mapRow(ResultSet rs, int i) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String desc = rs.getString("description");
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rs.getTimestamp("time"));
        Teacher t = new Teacher(id, name, desc, date);
        return t;
      }

    });
    return list;
  }

  @Override
  public boolean createTeacher(String name, File file, String desc, String detail) {
    String sql = "insert into teacher (name, avatar, description, detail, time) values (?, ?, ?, ?, ?)";
    Date time = new Date();
    Object[] params = new Object[] { name, file, desc, detail, time };
    int res = jdbcTemplate.update(sql, params);
    return res == 1;
  }

}

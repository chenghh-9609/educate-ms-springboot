package com.example.demo.dao.impl;

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

import com.example.demo.dao.StudentDAO;
import com.example.demo.model.Student;

@Repository
public class StudentDAOImpl implements StudentDAO {

  @Autowired
  JdbcTemplate jdbcTemplate;

  @Override
  public List<Student> getAll() {
    String sql = "select s.id, s.name, s.phonenumber, o.amount, o.quantity, o.time, s.disabled from student s, (select username, sum(price) amount, time, count(*) quantity from orders group by username) o where o.username = s.name ";
    List<Student> res = jdbcTemplate.query(sql, new RowMapper<Student>() {

      @Override
      @Nullable
      public Student mapRow(ResultSet rs, int i) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String phonenumber = rs.getString("phonenumber");
        int amount = rs.getInt("amount");
        int quantity = rs.getInt("quantity");
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rs.getTimestamp("time"));
        boolean disabled = rs.getBoolean("disabled");
        return new Student(id, name, phonenumber, amount, time, quantity, disabled);
      }

    });

    return res;
  }

}

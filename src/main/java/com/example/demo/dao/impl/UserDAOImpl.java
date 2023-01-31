package com.example.demo.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.UserDAO;
import com.example.demo.model.User;

@Repository
public class UserDAOImpl implements UserDAO {
  @Autowired
  private JdbcTemplate jdbcTemplate;
  @Override
  public int delete(int id) {
    String sql = "delete from user where id = ?;";
    Object args[] = new Object[] { id };
    int tmp = jdbcTemplate.update(sql, args);
    if (tmp > 0) {
      return 1;
    }
    return 0;
  }

  @Override
  public List<User> getAllUsers() {
    String sql = "SELECT * FROM USER;";
    List<User> map = jdbcTemplate.query(sql, new RowMapper<User>() {
      public User mapRow(ResultSet rs, int i) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String password = rs.getString("password");
        User u = new User(id, name, password);
        return u;
      }
    });
    return map;
  }

  @Override
  public int login(String name, String pwd) {
    String sql = "SELECT * FROM USER WHERE id = ?;";
    Map<String, Object> user = jdbcTemplate.queryForMap(sql, 1);
    return user.size();
  }

  @Override
  public int register(String name, String pwd) {
    String sql = "insert into user(name, password) " + "values(:name, :password);";
    Map<String, Object> params = new HashMap<>();
    params.put("name", name);
    params.put("password", pwd);
    return (int) jdbcTemplate.update(sql, params);
  }

  @Transactional(rollbackFor=Exception.class)
  @Override
  public int update(Integer id, String name, String pwd) {

    String sql = "update user set name = ? , password = ?  where id = ?;";
    Object[] args = new Object[] { name, pwd, id };
    int res = jdbcTemplate.update(sql, args);
    return res;
  }

  @Override
  public User getUser(int id) {
    return jdbcTemplate.queryForObject("select * from user where id = ?;", new RowMapper<User>() {
      public User mapRow(ResultSet rs, int i) throws SQLException {
        return new User(rs.getInt("id"), rs.getString("name"), rs.getString("password"));
      }
    }, id);
  }

}

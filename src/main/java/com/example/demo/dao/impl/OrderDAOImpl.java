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

import com.example.demo.dao.OrderDAO;
import com.example.demo.model.Order;

@Repository
public class OrderDAOImpl implements OrderDAO {
  @Autowired
  private JdbcTemplate jTemplate;

  @Override
  public boolean createOrder(String user, int amount, String status, Date time, String course) {
    String sql = "insert into orders (username, amount, status, time, coursename) values(?, ?, ?, ?, ?)";
    Object[] params = new Object[] { user, amount, status, time, course };
    int res = jTemplate.update(sql, params);
    return res == 1;
  }

  @Override
  public boolean delById(int id) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public List<Order> getAll() {
    String sql = "select o.id, o.username, o.price, o.status, o.time, o.coursename, s.phonenumber from orders o, student s where o.username = s.name;";
    List<Order> map = jTemplate.query(sql, new RowMapper<Order>() {
      @Override
      @Nullable
      public Order mapRow(ResultSet rs, int i) throws SQLException {
        int id = rs.getInt("id");
        String username = rs.getString("username");
        int price = rs.getInt("price");
        String status = rs.getString("status");
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rs.getTimestamp("time"));
        String coursename = rs.getString("coursename");
        String phone = rs.getString("phonenumber");
        Order order = new Order(id, username, price, coursename, status, time, phone);
        return order;
      }

    });
    return map;
  }

  @Override
  public List<Order> getRefund() {
    String sql = "select o.id, o.username, o.price, o.status, o.time, o.coursename, s.phonenumber from orders o, student s where o.username = s.name and o.status='已取消';";
    List<Order> map = jTemplate.query(sql, new RowMapper<Order>() {
      @Override
      @Nullable
      public Order mapRow(ResultSet rs, int i) throws SQLException {
        int id = rs.getInt("id");
        String username = rs.getString("username");
        int price = rs.getInt("price");
        String status = rs.getString("status");
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rs.getTimestamp("time"));
        String coursename = rs.getString("coursename");
        String phone = rs.getString("phonenumber");
        Order order = new Order(id, username, price, coursename, status, time, phone);
        return order;
      }
    });
    return map;
  }

  @Override
  public List<Order> getByConditions(Date date, String status, String keyword) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Order getById(int id) {
    // TODO Auto-generated method stub
    return null;
  }

}

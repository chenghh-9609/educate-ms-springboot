package com.example.demo.dao;

import java.util.Date;
import java.util.List;

import com.example.demo.model.Order;

public interface OrderDAO {
  public List<Order> getAll();

  public List<Order> getRefund();

  public List<Order> getByConditions(Date date, String status, String keyword);

  public Order getById(int id);

  public boolean delById(int id);

  public boolean createOrder(String user, int amount, String status, Date time, String course);
}

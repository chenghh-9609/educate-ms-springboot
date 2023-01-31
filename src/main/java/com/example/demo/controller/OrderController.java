package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.OrderDAO;

@CrossOrigin
@RestController
public class OrderController {
  @Autowired
  private OrderDAO orderDAO;

  @RequestMapping("/api/getallorders")
  public Map<String, Object> getAllOrders() {
    Map<String, Object> map = new HashMap();
    map.put("data", orderDAO.getAll());
    return map;
  }

  @RequestMapping("/api/getrefundlist")
  public Map<String, Object> getRefundOrders() {
    Map<String, Object> map = new HashMap();
    map.put("data", orderDAO.getRefund());
    return map;
  }
}

package com.example.demo.model;

import java.util.Date;

public class Order {
  private int id;
  private String username;
  private int price;
  private String coursename;
  private String status;
  private String time;
  private String phonenumber;

  public Order(int id, String username, int price, String coursename, String status, String time, String phonenumber) {
    this.id = id;
    this.username = username;
    this.price = price;
    this.coursename = coursename;
    this.status = status;
    this.time = time;
    this.phonenumber = phonenumber;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getCoursename() {
    return coursename;
  }

  public void setCoursename(String coursename) {
    this.coursename = coursename;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public String getPhonenumber() {
    return phonenumber;
  }

  public void setPhonenumber(String phonenumber) {
    this.phonenumber = phonenumber;
  }

  

}

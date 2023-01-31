package com.example.demo.model;

public class Student {
  private int id;
  private String username;
  private String phonenumber;
  private int amount;
  private String time;
  private int quantity;
  private boolean disable;

  
  public Student(int id, String username, String phonenumber, int amount, String time, int quantity, boolean disable) {
    this.id = id;
    this.username = username;
    this.phonenumber = phonenumber;
    this.amount = amount;
    this.time = time;
    this.quantity = quantity;
    this.disable = disable;
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
  public String getPhonenumber() {
    return phonenumber;
  }
  public void setPhonenumber(String phonenumber) {
    this.phonenumber = phonenumber;
  }
  public int getAmount() {
    return amount;
  }
  public void setAmount(int amount) {
    this.amount = amount;
  }
  public String getTime() {
    return time;
  }
  public void setTime(String time) {
    this.time = time;
  }
  public boolean isDisable() {
    return disable;
  }
  public void setDisable(boolean disable) {
    this.disable = disable;
  }
  public int getQuantity() {
    return quantity;
  }
  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  
  
}

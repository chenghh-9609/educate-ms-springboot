package com.example.demo.model;

import java.io.File;
import java.io.FileInputStream;

public class Course {
  private int id;
  private String name;
  private FileInputStream cover;
  private String status;
  private int price;
  private int quantity;
  private String uploader;
  private String time;

  

  public Course(int id, String name, FileInputStream cover, String status, int price, int quantity, String uploader, String time) {
    this.id = id;
    this.name = name;
    this.cover = cover;
    this.status = status;
    this.price = price;
    this.quantity = quantity;
    this.uploader = uploader;
    this.time = time;
  }
  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public FileInputStream getCover() {
    return cover;
  }
  public void setCover(FileInputStream cover) {
    this.cover = cover;
  }
  public String getStatus() {
    return status;
  }
  public void setStatus(String status) {
    this.status = status;
  }
  public int getPrice() {
    return price;
  }
  public void setPrice(int price) {
    this.price = price;
  }
  public int getQuantity() {
    return quantity;
  }
  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
  public String getUploader() {
    return uploader;
  }
  public void setUploader(String uploader) {
    this.uploader = uploader;
  }
  public String getTime() {
    return time;
  }
  public void setTime(String time) {
    this.time = time;
  }
  
  
}

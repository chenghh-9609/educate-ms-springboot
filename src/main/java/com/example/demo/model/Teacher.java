package com.example.demo.model;

import java.util.Date;

public class Teacher {
  private int id;
  private String name;
  private String desc;
  private String time;
  private String detail;

  public Teacher(int id, String name, String desc, String time) {
    this.id = id;
    this.name = name;
    this.desc = desc;
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

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public String getDetail() {
    return detail;
  }

  public void setDetail(String detail) {
    this.detail = detail;
  }

}

package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
  @RequestMapping("/hello")
  public Map<String, Object> hello() {
    Map<String, Object> map = new HashMap<>();
    map.put("hello", "欢迎");
    return map;
  }
}

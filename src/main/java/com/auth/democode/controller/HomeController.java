package com.auth.democode.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

  @GetMapping("/")
  public String greetings() {
    return "Welcome to the Home page....";
  }

  @GetMapping("/afterauth")
  public String afterAuth() {
    return "Authenticated";
  }
}

package com.restfit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.restfit.model.User;
import com.restfit.service.SecurityService;
import com.restfit.service.UserService;



@RestController
public class UserRegistrationController {

 @Autowired
 private UserService userService;
 
 @Autowired
 private SecurityService securityService;
 
 @RequestMapping(value= {"/", "/login"}, method=RequestMethod.GET)
 public ModelAndView login() {
  ModelAndView model = new ModelAndView();
  
  model.setViewName("/login");
  return model;
 }
 
 @RequestMapping(value= {"/signup"}, method=RequestMethod.GET)
 public ModelAndView signup() {
  ModelAndView model = new ModelAndView();
  User user = new User();
  model.addObject("user", user);
  model.setViewName("/signup");
  
  return model;
 }
 
 @RequestMapping(value= {"/signup"}, method=RequestMethod.POST)
 public ModelAndView createUser(User user, BindingResult bindingResult) {
  ModelAndView model = new ModelAndView();
  String pw = user.getPassword();
  User userExists = userService.findUserByLogin(user.getLogin());
  
  if(userExists != null) {
   bindingResult.rejectValue("login", "error.user", "This login already exists!");
  }
  if(bindingResult.hasErrors()) {
   model.setViewName("/signup");
  } else {
   userService.saveUser(user);
   model.addObject("msg", "User has been registered successfully!");
   model.addObject("user", new User());
   securityService.autoLogin(user.getLogin(), pw);
   model.setViewName("home/home"); //redirect:/home
  }
  
  return model;
 }
 
 
 @RequestMapping(value= {"/home/home"}, method=RequestMethod.GET)
 public ModelAndView home() {
  ModelAndView model = new ModelAndView();
  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
  User user = userService.findUserByLogin(auth.getName());
  
  model.addObject("userName", user.getLogin());
  model.setViewName("home/home");
  return model;
 }
 
 @RequestMapping(value= {"/access_denied"}, method=RequestMethod.GET)
 public ModelAndView accessDenied() {
  ModelAndView model = new ModelAndView();
  model.setViewName("errors/access_denied");
  return model;
 }
}
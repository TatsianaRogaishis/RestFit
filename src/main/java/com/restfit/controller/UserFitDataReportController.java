package com.restfit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.restfit.model.User;
import com.restfit.model.UserFitReport;
import com.restfit.repository.UserFitReportRepository;

@RestController
@RequestMapping("/")
public class UserFitDataReportController {
	@Autowired
    UserFitReportRepository userFitReportRepository;

	@GetMapping("/home/fitreport")
	public ModelAndView getReport() {
		ModelAndView model = new ModelAndView();
		System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//User user = new User("test", "123456");
		//user.setId(3L);
		if (user!=null) {
			List<UserFitReport> userFitReport = userFitReportRepository.findByUser(user);
			model.addObject("userName", user.getLogin());
			model.addObject("userFitReport", userFitReport);
		}		
		model.setViewName("/home/fitreport");
		return model;
	}
}

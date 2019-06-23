package com.restfit.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.restfit.model.User;
import com.restfit.model.UserFitData;
import com.restfit.repository.UserFitDataRepository;

import javassist.NotFoundException;

@RestController
@RequestMapping("/")
public class UserFitDataController {

	@Autowired
    UserFitDataRepository userFitDataRepository;

    // Get All
	@GetMapping("/home/notes")
	public ModelAndView getAllFitNotes() {
		ModelAndView model = new ModelAndView();
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	     List<UserFitData> userFitDatas = userFitDataRepository.findByUser(user);
	     model.addObject("userFitDatas", userFitDatas);
	     model.addObject("userName", user.getLogin());
	     model.setViewName("home/notes");
	     return model;
	}

	// Create
		@GetMapping("/home/newnote")
		public ModelAndView createFitNotePage() {
			ModelAndView model = new ModelAndView();
			User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			model.addObject("userName", user.getLogin());
		     model.setViewName("home/newnote");
		     return model;
		}
	
    // Create
	@PostMapping("/home/newnote")
	public ModelAndView createFitNote(@Valid @RequestBody UserFitData userFitData) {
		ModelAndView model = new ModelAndView();
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		userFitData.setUser(user);
	    userFitDataRepository.save(userFitData);
	    List<UserFitData> userFitDatas = userFitDataRepository.findAll();
	     model.addObject("userFitDatas", userFitDatas);
	     model.setViewName("redirect:home/notes");
	     return model;
	}

    // Get one
	@GetMapping("/home/notes/{id}")
	public ModelAndView getNoteById(@PathVariable(value = "id") Long id) throws NotFoundException {
		ModelAndView model = new ModelAndView();
		UserFitData userFitData = userFitDataRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Data not found, id "+ id));
		model.addObject("userFitData", userFitData);
	     model.setViewName("redirect:home/notes");
	     return model;
	}

    // Update 
	@PutMapping("/home/notes/{id}")
	public ModelAndView updateFitNote(@PathVariable(value = "id") Long id, @Valid @RequestBody UserFitData userFitData)
			throws NotFoundException {
		ModelAndView model = new ModelAndView();
		UserFitData userFitData2 = userFitDataRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Data not found, id " + id));

		userFitData2.setDistance(userFitData.getDistance());
		userFitData2.setRace_date(userFitData.getRace_date());
		userFitData2.setRun_time(userFitData.getRun_time());
		UserFitData updatedData = userFitDataRepository.save(userFitData2);

		List<UserFitData> userFitDatas = userFitDataRepository.findAll();
		model.addObject("userFitDatas", userFitDatas);
		model.setViewName("home/notes");
		return model;
	}

    // Delete 
	@DeleteMapping("/home/notes/{id}")
	public ModelAndView deleteNote(@PathVariable(value = "id") Long id) throws NotFoundException {
		UserFitData userFitData = userFitDataRepository.findById(id)
	            .orElseThrow(() -> new NotFoundException("Data not found, id "+ id));
		ModelAndView model = new ModelAndView();
		userFitDataRepository.delete(userFitData);

		model.setViewName("redirect:home/notes");
		return model;
	}
}

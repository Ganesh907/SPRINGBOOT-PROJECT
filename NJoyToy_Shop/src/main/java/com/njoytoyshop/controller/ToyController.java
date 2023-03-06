package com.njoytoyshop.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.njoytoyshop.models.CustomUser;
import com.njoytoyshop.models.Toy;
import com.njoytoyshop.services.CustomUserService;
import com.njoytoyshop.services.ToyService;

@Controller 
public class ToyController {
	
	@Autowired
	private ToyService service;
	
	@Autowired
	private CustomUserService service1;
	
//	@Autowired
//	CustomUserRepo repo;

	@Autowired
	PasswordEncoder encoder;
	
//	 add toy data 
	@GetMapping("/add")
	public String add(Model model) {
		
		Toy toy = new Toy();
		model.addAttribute("toy", toy);
		return "add";
	}

	
//		add toy data save in db
	@PostMapping("/save")
	public String save(@Valid @ModelAttribute("toy") Toy toy,BindingResult result,Model model) {
		
		
		if(result.hasErrors()) {	

			model.addAttribute("toy", toy);
			return "add";
		}
		else {
		
		service.saveToy(toy);
		return "redirect:/home";
		}
	}
	
//		return home page after add
	@GetMapping("/home")
	public String index(Model model) {
		List<Toy> toys=service.getToys();
		
		model.addAttribute("toys",toys);
//		model.addAttribute("gani","gani");
		return "home";
	}
	
	@GetMapping("/update/{id}")
	public String updateToy(Model model,@PathVariable int id) {
		
		Optional<Toy> toy=service.getToyById(id);
		model.addAttribute("toy",toy.get());
		return "updatetoy"; 
	}
	
	@PostMapping("/updatetoy/{id}")
	public String updateToyDetails(@Valid @ModelAttribute("toy") Toy toy,@PathVariable int id,BindingResult result,Model model) {
		if(result.hasErrors()) {	
			Optional<Toy> toy1=service.getToyById(id);
			model.addAttribute("toy",toy1.get());
			return "updatetoy";
		}
		else {
		toy.setId(id);
		service.saveToy(toy);
		return "redirect:/home";
		}
	}
	
	@GetMapping("/delete/{id}")
	public String deleteToy(@PathVariable int id){
		service.deleteToyById(id);
		return "redirect:/home";
	}
	
	
//		registration and login 
	

//		add user data
	@GetMapping("/register")
	public String Register(Model model) {
		
		CustomUser user = new CustomUser();
		model.addAttribute("user", user);
		
		return "register";
	}   

//		save user data
	@PostMapping("/save1")
	public String save(@Valid @ModelAttribute("user") CustomUser user,BindingResult result,Model model) {
		
		if(result.hasErrors()) {	
			model.addAttribute("user", user);
			return "register";
		}
		else {
		user.setPasscode(encoder.encode(user.getPasscode()));
		service1.saveUser(user);
		return "redirect:/logout";
		}
	}
	
	
//	login page
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	
//	access denied page
	@GetMapping("/access")
	public String access(Model model) {
		
		Toy toy = new Toy();
		model.addAttribute("toy", toy);
		
		return "access";
	}
	
	
}


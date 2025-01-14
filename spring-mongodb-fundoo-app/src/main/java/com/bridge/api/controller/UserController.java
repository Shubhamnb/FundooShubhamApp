package com.bridge.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridge.api.dto.LoginDto;
import com.bridge.api.dto.UserDto;
import com.bridge.api.service.Service;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	Service service;
	

	@PostMapping("/saveUser")
	public String saveUser(@RequestBody UserDto userDto) {
		boolean flag = service.saveService(userDto);
		if(flag == true) {
			return "Resgistration Done Successfully";
		}else {
			return "Resgistration Not Successfully";
		}
		
	}

	@GetMapping("/emailvalidation")
	public String emailValidation(@RequestParam String id) {
		service.setVerify(id);
		return "Registration Sussecfully Done";
	}
	
	@PostMapping("/login")
	public String login(@RequestBody LoginDto loginDto) {
		
		String str = service.loginService(loginDto.getEmail(), loginDto.getPassWord());
		return str;
	}
	
	@PostMapping("/forgetPassword")
	public String forgetPassword(@RequestParam String email) {
		System.out.println("SHhubham Bohari");
		boolean flag = service.isAvilabe(email);
		if(flag == true) {
			return "Verfication link sent on your mail ";
		}
		else {
		return "Entered Email not valid";
		}
	}
	
	@GetMapping("/change")
	public String changePassword() {
		String str = "<html>" + "<center><H1 color:red>Shubham</H1></center>"
				+ "</html>";
		return str;
	}
	 
}

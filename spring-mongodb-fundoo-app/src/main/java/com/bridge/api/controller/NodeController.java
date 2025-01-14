package com.bridge.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridge.api.dto.NoteDto;
import com.bridge.api.service.Service;

@RestController
@RequestMapping("/note")
public class NodeController {
		@Autowired
		Service service;
		
		
		@PostMapping("/create")
		public String addNote(@RequestBody NoteDto noteDto,@RequestParam String token) {
			service.saveNote(noteDto, token);
			return "Note Created Successfully";
		}
		
		@PostMapping("/update")
		public String upadateNote(@RequestBody NoteDto noteDto) {
			service.updateNote(noteDto);
			return null;
		}
}

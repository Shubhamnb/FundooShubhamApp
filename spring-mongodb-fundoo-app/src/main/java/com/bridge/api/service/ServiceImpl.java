package com.bridge.api.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import javax.mail.MessagingException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.bridge.api.dto.NoteDto;
import com.bridge.api.dto.UserDto;
import com.bridge.api.model.Email;
import com.bridge.api.model.Note;
import com.bridge.api.model.User;
import com.bridge.api.mongo.reposetory.NoteRepository;
import com.bridge.api.mongo.reposetory.UserRepository;
import com.bridge.api.util.UserToken;

@org.springframework.stereotype.Service
public class ServiceImpl implements Service {

	@Autowired
	private UserRepository userRepository;
	
	
	@Autowired
	private ModelMapper mapper;
	
	
	
	@Autowired
	MailService mailService;
	
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	UserToken userToken;
	
	@Autowired
	NoteRepository noteRepository;
	
	@Override
	public boolean saveService(UserDto userDto) {
		String idToken = null;
		Email email = new Email();
		User user = mapper.map(userDto, User.class);
		user.setCurrentDate(LocalDate.now());
		user.setPassWord(passwordEncoder.encode(user.getPassWord()));
		User getuser = userRepository.save(user);
		if(getuser != null) {
			idToken = userToken.generateToken(getuser.getUserId());
			System.out.println(idToken);
		}
		else {
			return false;
		}
		
		email.setFrom("bohari2@gmail.com");
		email.setTo(userDto.getEmail());
		email.setSubject("Email Verification ");
		try {
			mailService.send(email, idToken);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public void setVerify(String id) {
		String tokenId = userToken.tokenVerify(id);
		Optional<User> user = userRepository.findById(tokenId);
		User user1 = user.get();
		user1.setVerified(true);
		userRepository.save(user1);
	}

	@Override
	public String loginService(String userName, String passWord) {
		Optional<User> user = userRepository.findByEmail(userName);
		
	
		if (user.isPresent()) {
			String userNameToken = userToken.generateToken(user.get().getUserId());
			
			if (passwordEncoder.matches(passWord, user.get().getPassWord()))
				return userNameToken;
		}
			return "Invalid Credenthial";
		
	}

	/*
	 * Method is cheack where sender is present or not	
	*/	@Override
	public boolean isAvilabe(String email) {
		Email emailUser = new Email();
		Optional<User> user = userRepository.findByEmail(email);
		
		if(user.isPresent()) {
			emailUser.setFrom("bohari2@gmail.com");
			emailUser.setTo(user.get().getEmail());
			emailUser.setSubject("FOrget Password Verification");
			
			try {
				mailService.sendFogetPassWordLink(emailUser);
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true; 
		}
		return false;
	}

	/*Method for Save a note in Mongod */
	@Override
	public void saveNote(NoteDto noteDto, String token) {
		String noteId = userToken.tokenVerify(token);
		Note note = mapper.map(noteDto, Note.class);
		note.setCurrentTime(LocalDateTime.now());
		note.setUpdatedTime(LocalDateTime.now());
		note.setUserId(noteId);
		
		noteRepository.save(note);
		Optional<User> user = userRepository.findById(noteId);
		
	}

	@Override
	public String updateNote(NoteDto noteDto) {
		
		return null;
	}

}

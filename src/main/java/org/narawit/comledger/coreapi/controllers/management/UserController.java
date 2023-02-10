package org.narawit.comledger.coreapi.controllers.management;

import java.util.Optional;

import org.narawit.comledger.coreapi.domain.User;
import org.narawit.comledger.coreapi.repos.UserRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class UserController {
	//@Autowired
	private UserRepo userRepo;
	
	public UserController(UserRepo userRepo) {
		this.userRepo = userRepo;
	}
	
	@GetMapping("/takeAll")
	public ResponseEntity<Iterable<User>> takeUsers(){
		return new ResponseEntity<Iterable<User>>(userRepo.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/find/{id}")
	public ResponseEntity<User> takeUser(@PathVariable long userId){
		Optional<User> result = userRepo.findById(userId);
		if(result.isPresent()) {
			return new ResponseEntity<User>(result.get(), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody User userInfo) {
		Optional<User> result = userRepo.findByUsername(userInfo.getUsername());
		if(result.isPresent()) {
			return new ResponseEntity<String>("Generated KWT Token", HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("User not found", HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/register")
	public ResponseEntity<User> registerUser(@RequestBody User newUser) {
		User user = newUser;
		String encodePassword = this.EncodePassword(user.getPassword());
		user.setPassword(encodePassword);
		User savedUser = userRepo.save(user);
		return new ResponseEntity<User>(savedUser, HttpStatus.CREATED);
	}
		
	@PutMapping("/update/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user){
		Optional<User> result = userRepo.findById(id);
		if(result.isPresent()) {
			userRepo.save(user);
			return new ResponseEntity<User>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<User> removeUser(@PathVariable Long id, @RequestBody User user){
		Optional<User> result = userRepo.findById(id);
		if(result.isPresent()) {
			userRepo.delete(result.get());
			return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
	}
	
	//PlaceHolder
	// Should be in User Service class
	private String EncodePassword(String plainPassword) {
		String encodedPassword = "encode Password Impl";
		return encodedPassword;
	}
}
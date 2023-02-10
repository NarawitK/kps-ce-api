package org.narawit.comledger.coreapi.controllers.management;

import java.util.List;
import java.util.Optional;

import org.narawit.comledger.coreapi.domain.PersonInitial;
import org.narawit.comledger.coreapi.repos.PersonInitialRepo;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/admin/personinitials")
public class PersonInitialController {
	@Autowired
	private PersonInitialRepo repo;
	
	@GetMapping("")
	public List<PersonInitial> getAllPersonInitials(){
		return (List<PersonInitial>) repo.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PersonInitial> getById(@PathVariable("id") long id) {
		Optional<PersonInitial> result_instance = repo.findById(id);
		if(result_instance.isPresent()) {
			PersonInitial result =  result_instance.get();
			return new ResponseEntity<PersonInitial>(result, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<PersonInitial>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/add")
	public ResponseEntity<PersonInitial> newPersonInitial(@RequestBody PersonInitial entity) {
		try {
			PersonInitial savedInstance =  repo.save(entity);
			return new ResponseEntity<PersonInitial>(savedInstance, HttpStatus.CREATED);
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
			return new ResponseEntity<PersonInitial>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/edit/{id}")
	public ResponseEntity<PersonInitial> editPersonInitial(@PathVariable Long id, @RequestBody PersonInitial editInitial){
		Optional<PersonInitial> result = repo.findById(id);
		if(result.isPresent()) {
			
			return new ResponseEntity<PersonInitial>(repo.save(editInitial), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<PersonInitial>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<PersonInitial> removePersonInitial(@PathVariable Long id){
		Optional<PersonInitial> result = repo.findById(id);
		if(result.isPresent()) {
			repo.delete(result.get());
			return new ResponseEntity<PersonInitial>(HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<PersonInitial>(HttpStatus.NOT_FOUND);
		}
	}
}

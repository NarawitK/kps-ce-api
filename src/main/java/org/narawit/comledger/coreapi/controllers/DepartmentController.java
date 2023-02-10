package org.narawit.comledger.coreapi.controllers;

import java.util.Optional;

import org.narawit.comledger.coreapi.contracts.DepartmentContract;
import org.narawit.comledger.coreapi.domain.Department;
import org.narawit.comledger.coreapi.repos.DepartmentRepo;
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
@RequestMapping("/department")
public class DepartmentController {
	@Autowired
	private DepartmentRepo deptRepo;
	
	@GetMapping("")
	public ResponseEntity<Iterable<Department>> getAllDepartment(){
		return new ResponseEntity<Iterable<Department>>(deptRepo.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Department> getDepartmentById(@PathVariable Integer id){
		Optional<Department> result = deptRepo.findById(id);
		if(result.isPresent()) {
			return new ResponseEntity<Department>(result.get(), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Department>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/add")
	public ResponseEntity<Department> addNewDepartment(@RequestBody DepartmentContract dept){
		if(dept.name() != null) {
			Department newDept = new Department(dept.name(), dept.active());
			return new ResponseEntity<Department>(deptRepo.save(newDept), HttpStatus.CREATED);			
		}
		else {
			return new ResponseEntity<Department>(HttpStatus.BAD_REQUEST);	
		}
	}
	
	@PutMapping("/edit/{id}")
	public ResponseEntity<Department> editDepartment(@PathVariable Integer id, @RequestBody DepartmentContract dept){
		Optional<Department> result = deptRepo.findById(id);
		if(result.isPresent()) {
			Department editDept = new Department(id, dept.name(), dept.active());
			return new ResponseEntity<Department>(deptRepo.save(editDept), HttpStatus.CREATED);			
		}
		else {
			return new ResponseEntity<Department>(HttpStatus.BAD_REQUEST);	
		}
	}
	
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<Department> removeDepartment(@PathVariable Integer id){
		Optional<Department> result = deptRepo.findById(id);
		if(result.isPresent()) {
			deptRepo.delete(result.get());
			return new ResponseEntity<Department>(HttpStatus.NO_CONTENT);			
		}
		else {
			return new ResponseEntity<Department>(HttpStatus.BAD_REQUEST);	
		}
	}

}

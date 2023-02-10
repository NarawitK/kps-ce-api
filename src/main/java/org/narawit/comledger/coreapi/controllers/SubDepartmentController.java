package org.narawit.comledger.coreapi.controllers;

import java.util.Optional;

import org.narawit.comledger.coreapi.contracts.SubDepartmentRequest;
import org.narawit.comledger.coreapi.domain.Department;
import org.narawit.comledger.coreapi.domain.SubDepartment;
import org.narawit.comledger.coreapi.repos.SubDepartmentRepo;
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
@RequestMapping("/subdepartment")
public class SubDepartmentController {
	@Autowired
	private SubDepartmentRepo subDeptRepo;
	@GetMapping("")
	public ResponseEntity<Iterable<SubDepartment>> getAll(){
		return new ResponseEntity<Iterable<SubDepartment>>(subDeptRepo.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<SubDepartment> getById(@PathVariable Integer subDeptId){
		Optional<SubDepartment> subDept = subDeptRepo.findById(subDeptId);
		if(subDept.isPresent())
			return new ResponseEntity<SubDepartment>(subDept.get(), HttpStatus.OK);
		else
			return new ResponseEntity<SubDepartment>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/add")
	public ResponseEntity<SubDepartment> AddNewSubDepartment(@RequestBody SubDepartmentRequest newSubDept){
		if(newSubDept.name() != null && newSubDept.deptId() != null) {
			SubDepartment subDepartment = new SubDepartment();
			Department dept = new Department();
			dept.setId(newSubDept.deptId());
			subDepartment.setName(newSubDept.name());
			subDepartment.setDepartment(dept);
			return new ResponseEntity<SubDepartment>(subDeptRepo.save(subDepartment), HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<SubDepartment>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/edit/{id}")
	public ResponseEntity<SubDepartment> editSubDepartment(@PathVariable Integer subdeptId, @RequestBody SubDepartmentRequest editedSubDept){
		Optional<SubDepartment> subDeptResult = subDeptRepo.findById(subdeptId);
		if(subDeptResult.isPresent()) {
			Department dept = new Department();
			dept.setId(editedSubDept.deptId());
			SubDepartment subdept = new SubDepartment();
			subdept.setDepartment(dept);
			subdept.setId(subdeptId);
			subdept.setName(editedSubDept.name());
			subdept.setActive(editedSubDept.active());
			return new ResponseEntity<SubDepartment>(subDeptRepo.save(subdept), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<SubDepartment>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<String> removeSubDept(@PathVariable Integer id){
		Optional<SubDepartment> subdept = subDeptRepo.findById(id);
		if(subdept.isPresent()) {
			subDeptRepo.delete(subdept.get());
			return new ResponseEntity<String>("SubDept has been removed", HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<String>("SubDepartment not found", HttpStatus.NOT_FOUND);
		}
	}
}

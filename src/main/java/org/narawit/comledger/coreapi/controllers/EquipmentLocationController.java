package org.narawit.comledger.coreapi.controllers;

import java.util.Optional;

import org.narawit.comledger.coreapi.contracts.BaseResponse;
import org.narawit.comledger.coreapi.contracts.EquipmentLocationContract;
import org.narawit.comledger.coreapi.contracts.EquipmentLocationRequest;
import org.narawit.comledger.coreapi.domain.Department;
import org.narawit.comledger.coreapi.domain.Equipment;
import org.narawit.comledger.coreapi.domain.EquipmentLocation;
import org.narawit.comledger.coreapi.domain.SubDepartment;
import org.narawit.comledger.coreapi.repos.EquipmentLocationRepo;
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
@RequestMapping("/equipmentlocation")
public class EquipmentLocationController {
	private EquipmentLocationRepo repo;
	
	public EquipmentLocationController(EquipmentLocationRepo repo) {
		this.repo = repo;
	}
	
	@GetMapping
	public ResponseEntity<BaseResponse<Iterable<EquipmentLocation>>> getAllEquipmentLocation(){
		Iterable<EquipmentLocation> resultSet = repo.findAll();
		BaseResponse<Iterable<EquipmentLocation>> response = new BaseResponse<Iterable<EquipmentLocation>>(resultSet);
		return new ResponseEntity<BaseResponse<Iterable<EquipmentLocation>>>(response, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<BaseResponse<EquipmentLocationContract>> getEquipmentLocation(@PathVariable Long id){
		Optional<EquipmentLocation> result = repo.findById(id);
		if(result.isPresent()) {
			EquipmentLocationContract contract = new EquipmentLocationContract(result.get());
			BaseResponse<EquipmentLocationContract> response = new BaseResponse<EquipmentLocationContract>(contract);
			return new ResponseEntity<BaseResponse<EquipmentLocationContract>>(response, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<BaseResponse<EquipmentLocationContract>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/add")
	public ResponseEntity<BaseResponse<EquipmentLocation>> addEquipmentLocation(@RequestBody EquipmentLocationRequest newEquipmentLocation){
		EquipmentLocation savedEntity = repo.save(this.mapToEntity(null, newEquipmentLocation));
		BaseResponse<EquipmentLocation> response = new BaseResponse<EquipmentLocation>(savedEntity);
		return new ResponseEntity<BaseResponse<EquipmentLocation>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/edit/{id}")
	public ResponseEntity<BaseResponse<EquipmentLocation>> editEquipmentLocation(@PathVariable Long id, @RequestBody EquipmentLocationRequest request){
		Optional<EquipmentLocation> result = repo.findById(id);
		if(result.isPresent()) {
			EquipmentLocation savedEntity = repo.save(this.mapToEntity(id, request));
			BaseResponse<EquipmentLocation> response = new BaseResponse<EquipmentLocation>(savedEntity);
			return new ResponseEntity<BaseResponse<EquipmentLocation>>(response, HttpStatus.OK);
		}
		else {
			BaseResponse<EquipmentLocation> response = new BaseResponse<EquipmentLocation>(null , new RuntimeException("Entity not found"));
			return new ResponseEntity<BaseResponse<EquipmentLocation>>(response, HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<BaseResponse<String>> removeEquipmentLocation(@PathVariable Long id){
		repo.deleteById(id);
		BaseResponse<String> response = new BaseResponse<String>("Entity has been removed");
		return new ResponseEntity<BaseResponse<String>>(response, HttpStatus.NO_CONTENT);
	}
	
	private EquipmentLocation mapToEntity(Long id, EquipmentLocationRequest src) {
		EquipmentLocation entity = new EquipmentLocation();
		entity.setId(id);
		entity.setActive(src.active());
		entity.setAssigned_date(src.assignedDate());
		if(src.deptId() != null) {
			Department dept = new Department();
			dept.setId(src.deptId());
			entity.setDepartment(dept);			
		}
		else {
			entity.setDepartment(null);
		}
		if(src.subDeptId() != null) {
			SubDepartment subDept = new SubDepartment();
			subDept.setId(src.subDeptId());
			entity.setSubDepartment(subDept);			
		}
		else {
			entity.setSubDepartment(null);
		}
		if(src.equipmentId() != null) {
			Equipment equip = new Equipment();
			equip.setId(src.equipmentId());
			entity.setEquipment(equip);			
		}
		else {
			entity.setEquipment(null);
		}
		return entity;
	}
}

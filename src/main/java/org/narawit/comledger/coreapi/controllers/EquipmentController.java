package org.narawit.comledger.coreapi.controllers;

import java.util.Optional;

import org.narawit.comledger.coreapi.contracts.EquipmentContract;
import org.narawit.comledger.coreapi.contracts.EquipmentRequest;
import org.narawit.comledger.coreapi.domain.Equipment;
import org.narawit.comledger.coreapi.repos.EquipmentRepo;
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
@RequestMapping("/equipment")
public class EquipmentController {
	
	private EquipmentRepo repo;
	
	public EquipmentController(EquipmentRepo repo) {
		this.repo = repo;
	}
	
	@GetMapping("")
	public ResponseEntity<Iterable<Equipment>> getAll(){
		Iterable<Equipment> result = repo.findAll();
		return new ResponseEntity<Iterable<Equipment>>(result, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EquipmentContract> getById(@PathVariable Long id){
		Optional<Equipment> res = repo.findById(id);
		if(res.isPresent()) {
			Equipment equipment = res.get();
			return new ResponseEntity<EquipmentContract>(new EquipmentContract(equipment), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<EquipmentContract>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/add")
	public ResponseEntity<EquipmentContract> addEquipment(@RequestBody EquipmentRequest newEquipment){
		if(newEquipment.name() != null) {
			Equipment equipment = mapToEquipment(newEquipment);
			equipment = repo.save(equipment);
			return new ResponseEntity<EquipmentContract>(new EquipmentContract(equipment), HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<EquipmentContract>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/edit/{id}")
	public ResponseEntity<EquipmentContract> editEquipment(@PathVariable Integer id , @RequestBody EquipmentRequest editedEquipment){
		if(id != null && editedEquipment.name() != null) {
			Equipment equipment = this.mapToEquipment(editedEquipment);
			equipment = repo.save(equipment);
			return new ResponseEntity<EquipmentContract>(new EquipmentContract(equipment), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<EquipmentContract>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<String> removeEquipment(@PathVariable Long id){
		Optional<Equipment> queriedEquipment = repo.findById(id);
		if(queriedEquipment.isPresent()) {
			repo.delete(queriedEquipment.get());
			return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<String>("Entity not found", HttpStatus.NOT_FOUND);
		}
	}
	
	// Should be in service Class
	private Equipment mapToEquipment(EquipmentRequest instance) {
		Equipment equipment = new Equipment();
		equipment.setName(instance.name());
		equipment.setDescription(instance.description());
		equipment.setActive(instance.active());
		equipment.setInternalIdentifier(instance.identifier());
		equipment.setSerialNumber(instance.serialNumber());
		equipment.setImportDate(instance.importDate());
		equipment.setRegisterDate(instance.registerDate());
		return equipment;
	}
}
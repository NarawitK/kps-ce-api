package org.narawit.comledger.coreapi.controller;

import org.narawit.comledger.coreapi.contract.BaseResponse;
import org.narawit.comledger.coreapi.contract.EquipmentLocationContract;
import org.narawit.comledger.coreapi.contract.EquipmentLocationRequest;
import org.narawit.comledger.coreapi.controller.common.ControllerHelper;
import org.narawit.comledger.coreapi.service.EquipmentLocationService;
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
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/equipmentlocation")
public class EquipmentLocationController {
	
	private final EquipmentLocationService svc;
	
	public EquipmentLocationController(EquipmentLocationService svc) {
		this.svc = svc;
	}
	
	@GetMapping
	public ResponseEntity<BaseResponse<Iterable<EquipmentLocationContract>>> getAllEquipmentLocations(){
		return ControllerHelper.getResponseEntity(svc.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<BaseResponse<EquipmentLocationContract>> getEquipmentLocation(@PathVariable Long id){
		return ControllerHelper.getResponseEntity(svc.findById(id), HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<BaseResponse<EquipmentLocationContract>> add(@RequestBody EquipmentLocationRequest newEquipmentLocation){
		try {
			return ControllerHelper.getResponseEntity(svc.add(newEquipmentLocation), HttpStatus.CREATED);
		} catch (ResponseStatusException e) {
			return ControllerHelper.getHandleResponseException(e);
		}
	}
	
	@PutMapping("/edit/{id}")
	public ResponseEntity<BaseResponse<EquipmentLocationContract>> editEquipmentLocation(@PathVariable Long id, @RequestBody EquipmentLocationRequest request){
		try {
			return ControllerHelper.getResponseEntity(svc.edit(id, request), HttpStatus.NO_CONTENT);
			
		} catch (ResponseStatusException e) {
			return ControllerHelper.getHandleResponseException(e);
		}
	}
	
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<BaseResponse<String>> removeEquipmentLocation(@PathVariable Long id){
		svc.remove(id);
		return ControllerHelper.getResponseEntity(null, HttpStatus.NO_CONTENT);
	}
}

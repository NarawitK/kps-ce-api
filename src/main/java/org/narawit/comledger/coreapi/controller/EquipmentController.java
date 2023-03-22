package org.narawit.comledger.coreapi.controller;

import org.narawit.comledger.coreapi.contract.BaseResponse;
import org.narawit.comledger.coreapi.contract.EquipmentContract;
import org.narawit.comledger.coreapi.contract.EquipmentRequest;
import org.narawit.comledger.coreapi.controller.common.ControllerHelper;
import org.narawit.comledger.coreapi.service.EquipmentService;
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
@RequestMapping("/equipment")
public class EquipmentController {
	
	private final EquipmentService svc;
	
	public EquipmentController(EquipmentService svc) {
		this.svc = svc;
	}
	
	@GetMapping()
	public ResponseEntity<BaseResponse<Iterable<EquipmentContract>>> getAll(){
		BaseResponse<Iterable<EquipmentContract>> response = new BaseResponse<Iterable<EquipmentContract>>(svc.findAll());
		return new ResponseEntity<BaseResponse<Iterable<EquipmentContract>>>(response, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<BaseResponse<EquipmentContract>> getById(@PathVariable Long id){
		BaseResponse<EquipmentContract> response = new BaseResponse<EquipmentContract>(svc.findById(id));
		return new ResponseEntity<BaseResponse<EquipmentContract>>(response, HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<BaseResponse<EquipmentContract>> add(@RequestBody EquipmentRequest newEquipment){
		try {
			EquipmentContract data =  svc.add(newEquipment);
			BaseResponse<EquipmentContract> response = new BaseResponse<EquipmentContract>(data);
			return new ResponseEntity<BaseResponse<EquipmentContract>>(response, HttpStatus.CREATED);
		}
		catch (ResponseStatusException e) {
			return ControllerHelper.getHandleResponseException(e);
		}
	}
	
	@PutMapping("/edit/{id}")
	public ResponseEntity<BaseResponse<EquipmentContract>> edit(@PathVariable Integer id , @RequestBody EquipmentRequest editedEquipment){
		try {
			EquipmentContract data = svc.edit(null, editedEquipment);
			BaseResponse<EquipmentContract> response = new BaseResponse<EquipmentContract>(data);
			return new ResponseEntity<BaseResponse<EquipmentContract>>(response, HttpStatus.NO_CONTENT);
		} catch (ResponseStatusException e) {
			return ControllerHelper.getHandleResponseException(e);
		}
	}
	
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<BaseResponse<EquipmentContract>> remove(@PathVariable Long id){
		svc.remove(id);
		return ControllerHelper.getResponseEntity(null, HttpStatus.NO_CONTENT);
	}
}
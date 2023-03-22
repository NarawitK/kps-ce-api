package org.narawit.comledger.coreapi.controller.equipment;

import org.narawit.comledger.coreapi.contract.BaseResponse;
import org.narawit.comledger.coreapi.contracts.equipment.LaptopContract;
import org.narawit.comledger.coreapi.contracts.equipment.LaptopRequest;
import org.narawit.comledger.coreapi.controller.common.ControllerHelper;
import org.narawit.comledger.coreapi.service.equipment.LaptopService;

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
@RequestMapping("/laptop")
public class LaptopController {
	
	private final LaptopService svc;
	
	public LaptopController(LaptopService svc) {
		this.svc = svc;
	}
	
	@GetMapping
	public ResponseEntity<BaseResponse<Iterable<LaptopContract>>> getAll(){
		return ControllerHelper.getResponseEntity(svc.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<BaseResponse<LaptopContract>> getById(@PathVariable Long id){
		return ControllerHelper.getResponseEntity(svc.findById(id), HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<BaseResponse<LaptopContract>> add(@RequestBody LaptopRequest req){
		try {
			return ControllerHelper.getResponseEntity(svc.add(req), HttpStatus.CREATED);
		} catch (ResponseStatusException e) {
			return ControllerHelper.getHandleResponseException(e);
		}
	}
	
	@PutMapping("/edit/[id}")
	public ResponseEntity<BaseResponse<LaptopContract>> edit(@PathVariable Long id, @RequestBody LaptopRequest req){
		try {
			return ControllerHelper.getResponseEntity(svc.edit(id, req), HttpStatus.NO_CONTENT);
		} catch (ResponseStatusException e) {
			return ControllerHelper.getHandleResponseException(e);
		}
	}
	
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<BaseResponse<LaptopContract>> remove(@PathVariable Long id){
		svc.remove(id);
		return ControllerHelper.getResponseEntity(null, HttpStatus.NO_CONTENT);
	}
}
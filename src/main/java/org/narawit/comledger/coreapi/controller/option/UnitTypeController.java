package org.narawit.comledger.coreapi.controller.option;

import org.narawit.comledger.coreapi.contract.BaseResponse;
import org.narawit.comledger.coreapi.contract.option.UnitTypeContract;
import org.narawit.comledger.coreapi.contract.option.UnitTypeRequest;
import org.narawit.comledger.coreapi.controller.common.ControllerHelper;
import org.narawit.comledger.coreapi.service.option.UnitTypeService;
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
@RequestMapping("/unit_type")
public class UnitTypeController {
	
	private final UnitTypeService service;
	
	public UnitTypeController(UnitTypeService service) {
		this.service = service;
	}
	
	@GetMapping
	public ResponseEntity<BaseResponse<Iterable<UnitTypeContract>>> getAll(){
		return ControllerHelper.getResponseEntity(service.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id]")
	public ResponseEntity<BaseResponse<UnitTypeContract>> getById(@PathVariable Integer id){
		return ControllerHelper.getResponseEntity(service.findById(id), HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<BaseResponse<UnitTypeContract>> add(@RequestBody UnitTypeRequest req){
		try {
			return ControllerHelper.getResponseEntity(service.add(req), HttpStatus.CREATED);
		} catch (ResponseStatusException e) {
			return ControllerHelper.getHandleResponseException(e);
		}
	}
	
	@PutMapping("/edit/{id}")
	public ResponseEntity<BaseResponse<UnitTypeContract>> edit(@PathVariable Integer id, @RequestBody UnitTypeRequest req){
		try {
			return ControllerHelper.getResponseEntity(service.edit(id, req), HttpStatus.NO_CONTENT);
		} catch (ResponseStatusException e) {
			return ControllerHelper.getHandleResponseException(e);
		}
	}
	
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<BaseResponse<String>> remove(@PathVariable Integer id){
		service.remove(id);
		return ControllerHelper.getResponseEntity(null, HttpStatus.NO_CONTENT);
	}
}

package org.narawit.comledger.coreapi.controller.option;

import static org.narawit.comledger.coreapi.constant.MessageConstants.*;
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

import jakarta.validation.Valid;

@RestController
@RequestMapping("/unit_type")
public class UnitTypeController {
	
	private final UnitTypeService service;
	
	public UnitTypeController(UnitTypeService service) {
		this.service = service;
	}
	
	@GetMapping
	public ResponseEntity<BaseResponse<Iterable<UnitTypeContract>>> getAll(){
		return ControllerHelper.getOkResponseEntity(FETCH_SUCCESSFULLY, service.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<BaseResponse<UnitTypeContract>> getById(@PathVariable Integer id){
		return ControllerHelper.getOkResponseEntity(FETCH_SUCCESSFULLY, service.findById(id));
	}
	
	@PostMapping("/add")
	public ResponseEntity<BaseResponse<UnitTypeContract>> add(@Valid @RequestBody UnitTypeRequest req){
		try {
			return ControllerHelper.getResponseEntity(ADDED_ENTRY_SUCCESSFULLY, service.add(req), HttpStatus.CREATED);
		} catch (ResponseStatusException e) {
			return ControllerHelper.getHandleResponseException(e);
		}
	}
	
	@PutMapping("/edit/{id}")
	public ResponseEntity<BaseResponse<UnitTypeContract>> edit(@PathVariable Integer id, @Valid @RequestBody UnitTypeRequest req){
		try {
			return ControllerHelper.getResponseEntity(EDITED_ENTRY_SUCCESSFULLY, service.edit(id, req), HttpStatus.NO_CONTENT);
		} catch (ResponseStatusException e) {
			return ControllerHelper.getHandleResponseException(e);
		}
	}
	
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<BaseResponse<String>> remove(@PathVariable Integer id){
		service.remove(id);
		return ControllerHelper.getResponseEntity(REMOVE_ENTRY_SUCCESSFULLY, null, HttpStatus.NO_CONTENT);
	}
}

package org.narawit.comledger.coreapi.controller.option;

import static org.narawit.comledger.coreapi.constant.MessageConstants.*;

import org.narawit.comledger.coreapi.contract.BaseResponse;
import org.narawit.comledger.coreapi.contract.option.ManufactureCatContract;
import org.narawit.comledger.coreapi.contract.option.ManufactureCatRequest;
import org.narawit.comledger.coreapi.controller.common.ControllerHelper;
import org.narawit.comledger.coreapi.service.option.ManufactureCatService;
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
@RequestMapping("/manufacture_cat")
public class ManufactureCatController {
	
	private final ManufactureCatService service;
	
	public ManufactureCatController(ManufactureCatService service) {
		this.service = service;
	}
	
	@GetMapping
	public ResponseEntity<BaseResponse<Iterable<ManufactureCatContract>>> getAll(){
		return ControllerHelper.getOkResponseEntity(FETCH_SUCCESSFULLY, service.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<BaseResponse<ManufactureCatContract>> getById(@PathVariable Integer id){
		return ControllerHelper.getOkResponseEntity(FETCH_SUCCESSFULLY, service.findById(id));
	}
	
	@PostMapping("/add")
	public ResponseEntity<BaseResponse<ManufactureCatContract>> add(@Valid @RequestBody ManufactureCatRequest req){
		try {
			return ControllerHelper.getResponseEntity(ADDED_ENTRY_SUCCESSFULLY, service.add(req), HttpStatus.CREATED, true);
		} catch (ResponseStatusException e) {
			return ControllerHelper.getHandleResponseException(e);
		}
	}
	
	@PutMapping("/edit/{id}")
	public ResponseEntity<BaseResponse<ManufactureCatContract>> edit(@PathVariable Integer id, @Valid @RequestBody ManufactureCatRequest req){
		try {
			return ControllerHelper.getResponseEntity(ADDED_ENTRY_SUCCESSFULLY, service.add(req), HttpStatus.CREATED, true);
		} catch (ResponseStatusException e) {
			return ControllerHelper.getHandleResponseException(e);
		}
	}
	
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<BaseResponse<ManufactureCatContract>> remove(@PathVariable Integer id){
		service.remove(id);
		return ControllerHelper.getResponseEntity(REMOVE_ENTRY_SUCCESSFULLY, null, HttpStatus.NO_CONTENT, true);
	}
}

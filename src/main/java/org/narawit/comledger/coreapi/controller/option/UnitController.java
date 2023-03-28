package org.narawit.comledger.coreapi.controller.option;

import static org.narawit.comledger.coreapi.constant.MessageConstants.*;
import org.narawit.comledger.coreapi.contract.BaseResponse;
import org.narawit.comledger.coreapi.contract.option.UnitContract;
import org.narawit.comledger.coreapi.contract.option.UnitRequest;
import org.narawit.comledger.coreapi.controller.common.ControllerHelper;
import org.narawit.comledger.coreapi.service.option.UnitService;
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
@RequestMapping("/unit")
public class UnitController {
	
	private final UnitService svc;
	
	public UnitController(UnitService svc) {
		this.svc = svc;
	}
	
	@GetMapping
	public ResponseEntity<BaseResponse<Iterable<UnitContract>>> getAll(){
		return ControllerHelper.getOkResponseEntity(FETCH_SUCCESSFULLY, svc.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<BaseResponse<UnitContract>> getById(@PathVariable Integer id){
		return ControllerHelper.getOkResponseEntity(FETCH_SUCCESSFULLY, svc.findById(id));
	}
	
	@PostMapping("/add")
	public ResponseEntity<BaseResponse<UnitContract>> add(@Valid @RequestBody UnitRequest req){
		try {
			return ControllerHelper.getResponseEntity(ADDED_ENTRY_SUCCESSFULLY, svc.add(req), HttpStatus.CREATED);
		} catch (ResponseStatusException e) {
			return ControllerHelper.getHandleResponseException(e);
		}
	}
	
	@PutMapping("/edit/{id}")
	public ResponseEntity<BaseResponse<UnitContract>> edit(@PathVariable Integer id, @Valid @RequestBody UnitRequest req){
		try {
			return ControllerHelper.getResponseEntity(EDITED_ENTRY_SUCCESSFULLY, svc.edit(id, req), HttpStatus.NO_CONTENT);
		} catch (ResponseStatusException e) {
			return ControllerHelper.getHandleResponseException(e);
		}
	}
	
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<BaseResponse<UnitContract>> remove(@PathVariable Integer id){
		svc.remove(id);
		return ControllerHelper.getResponseEntity(REMOVE_ENTRY_SUCCESSFULLY, null, HttpStatus.NO_CONTENT);
	}
}

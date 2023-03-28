package org.narawit.comledger.coreapi.controller;

import static org.narawit.comledger.coreapi.constant.MessageConstants.*;
import org.narawit.comledger.coreapi.contract.BaseResponse;
import org.narawit.comledger.coreapi.contract.SubDepartmentContract;
import org.narawit.comledger.coreapi.contract.SubDepartmentRequest;
import org.narawit.comledger.coreapi.controller.common.ControllerHelper;
import org.narawit.comledger.coreapi.service.SubDepartmentService;
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
@RequestMapping("/subdepartment")
public class SubDepartmentController {
	
	private SubDepartmentService svc;
	
	public SubDepartmentController(SubDepartmentService svc) {
		this.svc = svc;
	}
	
	@GetMapping
	public ResponseEntity<BaseResponse<Iterable<SubDepartmentContract>>> getAll(){
		return ControllerHelper.getOkResponseEntity(FETCH_SUCCESSFULLY, svc.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<BaseResponse<SubDepartmentContract>> getById(@PathVariable Integer subDeptId){
		return ControllerHelper.getOkResponseEntity(FETCH_SUCCESSFULLY, svc.findById(subDeptId));
	}
	
	@PostMapping("/add")
	public ResponseEntity<BaseResponse<SubDepartmentContract>> add(@Valid @RequestBody SubDepartmentRequest newSubDept){
		try {
			return ControllerHelper.getResponseEntity(ADDED_ENTRY_SUCCESSFULLY, svc.add(newSubDept), HttpStatus.OK);
		} catch (ResponseStatusException e) {
			return ControllerHelper.getHandleResponseException(e);
		}
	}
	
	@PutMapping("/edit/{id}")
	public ResponseEntity<BaseResponse<SubDepartmentContract>> edit(@PathVariable Integer subdeptId, @Valid @RequestBody SubDepartmentRequest editedSubDept){
		try {
			return ControllerHelper.getResponseEntity(EDITED_ENTRY_SUCCESSFULLY, svc.edit(subdeptId, editedSubDept), HttpStatus.NO_CONTENT);
		} catch (ResponseStatusException e) {
			return ControllerHelper.getHandleResponseException(e);
		}
	}
	
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<BaseResponse<SubDepartmentContract>> remove(@PathVariable Integer id){
		svc.remove(id);
		return ControllerHelper.getResponseEntity(REMOVE_ENTRY_SUCCESSFULLY, null, HttpStatus.NO_CONTENT);
	}
}

package org.narawit.comledger.coreapi.controller;

import static org.narawit.comledger.coreapi.constant.MessageConstants.*;

import org.narawit.comledger.coreapi.contract.BaseResponse;
import org.narawit.comledger.coreapi.contract.EventLogContract;
import org.narawit.comledger.coreapi.contract.EventLogRequest;
import org.narawit.comledger.coreapi.controller.common.ControllerHelper;
import org.narawit.comledger.coreapi.service.EventLogService;
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
@RequestMapping("/eventlog")
public class EventLogController {
	
	private final EventLogService svc;
	
	public EventLogController(EventLogService svc) {
		this.svc = svc;
	}
	
	@GetMapping
	public ResponseEntity<BaseResponse<Iterable<EventLogContract>>> getAll(){
		return ControllerHelper.getOkResponseEntity(FETCH_SUCCESSFULLY, svc.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<BaseResponse<EventLogContract>> getById(@PathVariable Long id){
		return ControllerHelper.getOkResponseEntity(FETCH_SUCCESSFULLY, svc.findById(id));
	}
	
	@PostMapping("/add")
	public ResponseEntity<BaseResponse<EventLogContract>> add(@Valid @RequestBody EventLogRequest req){
		try {
			return ControllerHelper.getResponseEntity(ADDED_ENTRY_SUCCESSFULLY, svc.add(req), HttpStatus.CREATED, true);
		} catch (ResponseStatusException e) {
			return ControllerHelper.getHandleResponseException(e);
		}
	}
	
	@PutMapping("/edit/{id}")
	public ResponseEntity<BaseResponse<EventLogContract>> edit(@PathVariable Long id, @Valid @RequestBody EventLogRequest req){
		try {
			return ControllerHelper.getResponseEntity(EDITED_ENTRY_SUCCESSFULLY, svc.edit(id, req), HttpStatus.NO_CONTENT, true);
		} catch (ResponseStatusException e) {
			return ControllerHelper.getHandleResponseException(e);
		}
	}
	
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<BaseResponse<EventLogContract>> remove(@PathVariable Long id){
		svc.remove(id);
		return ControllerHelper.getResponseEntity(REMOVE_ENTRY_SUCCESSFULLY, null, HttpStatus.NO_CONTENT, true);
	}
}

package org.narawit.comledger.coreapi.controller.option;

import org.narawit.comledger.coreapi.contract.BaseResponse;
import org.narawit.comledger.coreapi.contract.option.CdromTypeContract;
import org.narawit.comledger.coreapi.contract.option.CdromTypeRequest;
import org.narawit.comledger.coreapi.controller.common.ControllerHelper;
import org.narawit.comledger.coreapi.service.option.CdromTypeService;
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
@RequestMapping("/cdrom_type")
public class CdRomTypeController {
	
	private final CdromTypeService svc;
	
	public CdRomTypeController(CdromTypeService svc) {
		this.svc = svc;
	}
	
	@GetMapping
	public ResponseEntity<BaseResponse<Iterable<CdromTypeContract>>> getAll(){
		return ControllerHelper.getResponseEntity(svc.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<BaseResponse<CdromTypeContract>> getById(@PathVariable Integer id){
		return ControllerHelper.getResponseEntity(svc.findById(id), HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<BaseResponse<CdromTypeContract>> add(@RequestBody CdromTypeRequest req){
		try {
			return ControllerHelper.getResponseEntity(svc.add(req), HttpStatus.CREATED);
		} catch (ResponseStatusException e) {
			return ControllerHelper.getHandleResponseException(e);
		}
	}
	
	@PutMapping("/edit/[id}")
	public ResponseEntity<BaseResponse<CdromTypeContract>> edit(@PathVariable Integer id, @RequestBody CdromTypeRequest req){
		try {
			return ControllerHelper.getResponseEntity(svc.edit(id, req), HttpStatus.NO_CONTENT);
		} catch (ResponseStatusException e) {
			return ControllerHelper.getHandleResponseException(e);
		}
	}
	
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<BaseResponse<CdromTypeContract>> remove(@PathVariable Integer id){
		svc.remove(id);
		return ControllerHelper.getResponseEntity(null, HttpStatus.NO_CONTENT);
	}
}

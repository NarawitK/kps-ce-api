package org.narawit.comledger.coreapi.controller.option;

import org.narawit.comledger.coreapi.contract.BaseResponse;
import org.narawit.comledger.coreapi.contract.option.PrinterTypeContract;
import org.narawit.comledger.coreapi.contract.option.PrinterTypeRequest;
import org.narawit.comledger.coreapi.controller.common.ControllerHelper;
import org.narawit.comledger.coreapi.service.option.PrinterTypeService;
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
@RequestMapping("/printer_type")
public class PrinterTypeController {
	
	private final PrinterTypeService svc;
	
	public PrinterTypeController(PrinterTypeService svc) {
		this.svc = svc;
	}
	
	@GetMapping
	public ResponseEntity<BaseResponse<Iterable<PrinterTypeContract>>> getAll(){
		return ControllerHelper.getResponseEntity(svc.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<BaseResponse<PrinterTypeContract>> getById(@PathVariable Integer id){
		return ControllerHelper.getResponseEntity(svc.findById(id), HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<BaseResponse<PrinterTypeContract>> add(@RequestBody PrinterTypeRequest req){
		try {
			return ControllerHelper.getResponseEntity(svc.add(req), HttpStatus.CREATED);
		} catch (ResponseStatusException e) {
			return ControllerHelper.getHandleResponseException(e);
		}
	}
	
	@PutMapping("/edit/[id}")
	public ResponseEntity<BaseResponse<PrinterTypeContract>> edit(@PathVariable Integer id, @RequestBody PrinterTypeRequest req){
		try {
			return ControllerHelper.getResponseEntity(svc.edit(id, req), HttpStatus.NO_CONTENT);
		} catch (ResponseStatusException e) {
			return ControllerHelper.getHandleResponseException(e);
		}
	}
	
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<BaseResponse<PrinterTypeContract>> remove(@PathVariable Integer id){
		svc.remove(id);
		return ControllerHelper.getResponseEntity(null, HttpStatus.NO_CONTENT);
	}
}

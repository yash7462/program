package com.services.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.services.beans.Category;
import com.services.beans.Services;
import com.services.business.ServiceBusiness;
import com.services.exception.ResourceNotFoundException;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/category")
public class ServiceController {

	@Autowired
	private ServiceBusiness servicebusiness;

	@GetMapping("/")
	@ApiOperation(value = "", notes = "Get all services category")
	public List<Category> GetAllCategory() {
		// m.addAttribute("list", plantmasterbusiness.readAllPlantMaster());
		return servicebusiness.readAllCategory();
	}

	@PostMapping("/")
	@ApiOperation(value = "", notes = "insert category")
	public Category createCategory(@Valid Category category) {

		return servicebusiness.addCategory(category);

	}

	@PutMapping("/{categoryid}")
	@ApiOperation(value = "", notes = "edit category name")
	public ResponseEntity<Category> editCategory(@PathVariable(value = "categoryid") long categoryid,
			@Valid @RequestBody Category category) throws ResourceNotFoundException {
		return servicebusiness.updateCategory(categoryid, category);
	}

	@DeleteMapping("/{categoryid}")
	@ApiOperation(value = "", notes = "delete whole category column")
	public ResponseEntity<?> deleteCategory(@PathVariable(value = "categoryid") long categoryid) throws Exception {
		return servicebusiness.deleteCategory(categoryid);
	}

	////////////////////////////////////////// SERVICES
	////////////////////////////////////////// /////////////////////////////////////////////////

	@GetMapping("/service/{categoryid}")
	@ApiOperation(value = "", notes = "number of services based on category")
	public List<Services> getAllServiceByCategory(@PathVariable(value = "categoryid") long categoryid) {
		// m.addAttribute("list", plantmasterbusiness.readAllPlantMaster());
		return servicebusiness.readAllService(categoryid);
	}

	@PostMapping("/{categoryid}/service")
	@ApiOperation(value = "", notes = "insert serviceitem")
	public Services createServices(@PathVariable(value = "categoryid") long categoryid,
			@Valid @RequestBody Services service) throws ResourceNotFoundException {
		return servicebusiness.addService(categoryid, service);

	}

	@PutMapping("/{categoryid}/service/{serviceid}")
	@ApiOperation(value = "", notes = "edit service based on its category and serviceid")
	public ResponseEntity<Services> updateServices(@PathVariable(value = "categoryid") long categoryid,
			@PathVariable(value = "serviceid") long serviceid, @Valid @RequestBody Services service)
			throws ResourceNotFoundException {

		return servicebusiness.updateService(categoryid, serviceid, service);

	}

	@DeleteMapping("/service/{serviceid}")
	@ApiOperation(value = "", notes = "delete only serviceitem based on its serviceid")
	public ResponseEntity<?> deleteServices(@PathVariable(value = "serviceid") long serviceid)
			throws ResourceNotFoundException {

		return servicebusiness.deleteService(serviceid);
	}

}

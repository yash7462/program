package com.services.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.services.beans.Category;
import com.services.beans.Services;
import com.services.exception.ResourceNotFoundException;
import com.services.repository.CategoryRepository;
import com.services.repository.ServicesRepository;

@Component
@Qualifier("ServiceDao")
public class ServiceDaoImpl implements ServiceDao {
	@Autowired
	public ServicesRepository servicesrepository;

	@Autowired
	public CategoryRepository categoryrepository;

	public List<Category> readAllCategory() {
		return categoryrepository.findAll();

	}

	public Category addCategory(@Valid Category categoryname) {
		return categoryrepository.save(categoryname);
	}

	public ResponseEntity<Category> updateCategory(@PathVariable(value = "categoryid") long categoryid,
			@Valid @RequestBody Category categorydetails) throws ResourceNotFoundException {
		Category cg = categoryrepository.findById(categoryid)
				.orElseThrow(() -> new ResourceNotFoundException("categoryid not found on : " + categoryid));
		cg.setCategoryname(categorydetails.getCategoryname());

		final Category updatedCategory = categoryrepository.save(cg);
		return ResponseEntity.ok(updatedCategory);

	}

	public ResponseEntity<?> deleteCategory(@PathVariable(value = "categoryid") long categoryid) throws Exception {
		Category category = categoryrepository.findById(categoryid)
				.orElseThrow(() -> new ResourceNotFoundException(" customer not found  on : " + categoryid));
		categoryrepository.deleteByCategoryId(categoryid);
		return ResponseEntity.ok().build();
	}

	/**
	 * work fine
	 */
	public List<Services> readAllService(@PathVariable(value = "categoryid") long categoryid) {
		return servicesrepository.findByCategoryId(categoryid);
	}

	public Services addService(@PathVariable(value = "categoryid") long categoryid,
			@Valid @RequestBody Services service) throws ResourceNotFoundException {
		return categoryrepository.findById(categoryid).map(category -> {
			service.setCategory(category);
			return servicesrepository.save(service);
		}).orElseThrow(() -> new ResourceNotFoundException("category  " + categoryid + " not found"));

	}

	public ResponseEntity<Services> updateService(@PathVariable (value = "categoryid") long categoryid,
            @PathVariable (value = "serviceid") long serviceid,
            @Valid @RequestBody Services service) throws ResourceNotFoundException {
		
		if(!categoryrepository.existsById(categoryid)) {
            throw new ResourceNotFoundException("category  " + categoryid + " not found");
        }
        Services sg = servicesrepository.findById(serviceid)
				.orElseThrow(() -> new ResourceNotFoundException("services not found on : " + serviceid));
        	
        	sg.setServicename(service.getServicename());
    		sg.setServicedescription(service.getServicedescription());
    		sg.setServiceduration(service.getServiceduration());
    		sg.setServicecost(service.getServicecost());
    		sg.setComments(service.getComments());
    		
    		final Services updatedServices = servicesrepository.save(sg);
    		return ResponseEntity.ok(updatedServices);
		
		/*
		 * Services sg = servicesrepository.findById(serviceid) .orElseThrow(() -> new
		 * ResourceNotFoundException("services not found on : " + serviceid));
		 * 
		 * sg.setServicename(service.getServicename());
		 * sg.setServicedescription(service.getServicedescription());
		 * sg.setServiceduration(service.getServiceduration());
		 * sg.setServicecost(service.getServicecost());
		 * sg.setComments(service.getComments());
		 * 
		 * final Services updatedServices = servicesrepository.save(sg); return
		 * ResponseEntity.ok(updatedServices);
		 */

	}

	public ResponseEntity<?> deleteService(@PathVariable (value = "serviceid") long serviceid) throws ResourceNotFoundException {
		Services service = servicesrepository.findById(serviceid)
				.orElseThrow(() -> new ResourceNotFoundException(" service not found  on : " + serviceid));
					servicesrepository.deleteByserviceId(serviceid);
			        return ResponseEntity.ok().build();
	}

}

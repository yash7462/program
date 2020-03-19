package com.services.business;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.services.beans.Category;
import com.services.beans.Services;
import com.services.exception.ResourceNotFoundException;
import com.services.service.ServicesService;
@Component
public class ServiceBusiness {

	@Autowired
	public ServicesService serviceservice;
	
	public List<Category> readAllCategory() {
		return serviceservice.readAllCategory();
	}

	public ResponseEntity<Category> updateCategory(long categoryid, Category category) throws ResourceNotFoundException {
		return serviceservice.updateCategory(categoryid , category);
	}

	public ResponseEntity<?> deleteCategory(long categoryid) throws Exception {
		return serviceservice.deleteCategory(categoryid);

	}

	public List<Services> readAllService(long categoryid) {
		return serviceservice.readAllService(categoryid);

	}

	public Services addService(long categoryid, @Valid Services service) throws ResourceNotFoundException {
		return serviceservice.addService(categoryid,service);

	}

	public ResponseEntity<Services> updateService(long categoryid, long serviceid, Services service) throws ResourceNotFoundException {
		return serviceservice.updateService(categoryid, serviceid , service);

	}

	public ResponseEntity<?> deleteService(long serviceid) throws ResourceNotFoundException {
		return serviceservice.deleteService(serviceid);

	}

	public Category addCategory( @Valid Category category) {
		return serviceservice.addCategory(category);
	}

}

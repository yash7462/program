package com.services.dao;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import com.services.beans.Category;
import com.services.beans.Services;
import com.services.exception.ResourceNotFoundException;

public interface ServiceDao {

	public List<Category> readAllCategory();

	public ResponseEntity<Category> updateCategory(long categoryid, Category category) throws ResourceNotFoundException;

	public ResponseEntity<?> deleteCategory(long categoryid) throws Exception;
	

	public List<Services> readAllService(long categoryid);

	public Services addService(long categoryid, @Valid Services services) throws ResourceNotFoundException;

	public ResponseEntity<Services> updateService(long categoryid, long serviceid, Services service) throws ResourceNotFoundException;

	public ResponseEntity<?> deleteService(long serviceid) throws ResourceNotFoundException;

	public Category addCategory(@Valid Category category);

}

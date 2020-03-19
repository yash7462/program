package com.services.repository;

import java.util.List;
import java.util.Optional;

import com.services.beans.Services;

public interface ServicesRepositoryCustom {
	
	List<Services> findByCategoryId(long categoryid);
	
	//Optional<Services> findByIdAndPostId(long categoryid, long serviceid);

}

package com.marketingapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marketingapp.entities.Lead;
import com.marketingapp.entities.repositories.LeadRepository;
@Service
public class LeadServiceImp implements LeadService {
    @Autowired
    private LeadRepository leadrepo;
	@Override
	public void saveLead(Lead lead) {
		leadrepo.save(lead);

	}
	@Override
	public List<Lead> ListAll() {
		List<Lead> leads = leadrepo.findAll();
			return leads;
	}
	@Override
	public void deleteById(long id) {
		leadrepo.deleteById(id);
		
	}
	@Override
	public Lead findOneLeadById(long id) {
		Optional<Lead> findById = leadrepo.findById(id);
		if(findById.isPresent()) {
		Lead lead = findById.get();
		return lead;
		}else {
			return null;
		}
		
		
	}
	

}

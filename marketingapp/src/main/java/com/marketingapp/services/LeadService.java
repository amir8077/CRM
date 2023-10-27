package com.marketingapp.services;

import java.util.List;

import com.marketingapp.entities.Lead;



public interface LeadService {
	public void saveLead(Lead lead);
	public List<Lead>ListAll();
	public void deleteById(long id);
	public Lead findOneLeadById(long id);

}

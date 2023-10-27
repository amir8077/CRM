package com.marketingapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.marketingapp.dto.LeadData;
import com.marketingapp.entities.Lead;
import com.marketingapp.services.LeadService;
import com.marketingapp.util.EmailService;

@Controller
public class LeadController {
	@Autowired
	private EmailService emailService;
	
	
	
	
	
	  @Autowired
		private LeadService leadService;
	@RequestMapping("/createLead")
	public String viewCreateLeadPage() {
		return  "create_new_lead";
	}  
	//method no.1 @ModelAttribute

	//method no.2 Data Transfer Object dto
/*
    @RequestMapping("/saveLead")
	public String saveOneLead(LeadData data {
		Lead l = new Lead();
		l.setFirstName(data.getFirstName());
		l.setLastName(data.getLastName());
		l.setEmail(data.getEmail());
		l.setMobile(data.getMobile());
		leadService.saveLead(l);
		
		return "create_new_jsp"; } 
}
*/

//method no 3 @Requestparam
/*@RequestMapping("/saveLead")
public String saveOneLead(@RequestParam("firstName") String fName,@RequestParam("lastName") String lName,@RequestParam("email") String emailId,@RequestParam("mobile") long mobile) {
	Lead l = new Lead();
	l.setFirstName(fName);
	l.setLastName(lName);
	l.setEmail(emailId);
	l.setMobile(mobile);
	leadService.saveLead(l);
	return "create_new_jsp"; 
}*/
	 @RequestMapping("/saveLead")
		public String saveOneLead(@ModelAttribute("lead") Lead lead, ModelMap model  ) {
			leadService.saveLead(lead);
			emailService.sendSimpleMessage("amirarsh.aa11@gmail.com", "test", "welcome");
			model.addAttribute("saveMsg", "Record is Saved!!");
		    return "create_new_jsp"; 
			} 
	    @RequestMapping("/listlead")
		public String getAllLeads(ModelMap model) {
			List<Lead> leads = leadService.ListAll();
			//System.out.println(leads);
			model.addAttribute("leads",leads);
			return"search_result";
		}
	    @RequestMapping("/delete")
	    public String deleteOneLead(@RequestParam("id") long id , ModelMap model) {
	    	leadService.deleteById(id);
	    	
	    	List<Lead> leads = leadService.ListAll();
			model.addAttribute("leads",leads);
			return"search_result";
	    }
	    @RequestMapping("/update")
	    public String updateOneLead(@RequestParam("id") long id , ModelMap model) {
	    	Lead lead = leadService.findOneLeadById(id);
	    	if(lead!=null) {
	    		model.addAttribute("Lead", lead);
	    		return "update_Lead";
	    	}else {
	    		model.addAttribute("error","No Record Found");
	    		return "Error_page";
	    	}
	    }
	    	
	    
	    @RequestMapping("/updateLead")	
	    public String updateLead(LeadData data , ModelMap model) {
	    	Lead l = new Lead();
	    	l.setId(data.getId());
	    	l.setFirstName(data.getFirstName());
	    	l.setLastName(data.getLastName());
	    	l.setEmail(data.getEmail());
	    	l.setMobile(data.getMobile());
	    	
	    	leadService.saveLead(l);
	    	
	    	List<Lead> leads = leadService.ListAll();
			model.addAttribute("leads",leads);
			return"search_result";
	    	
	    }
	
	
	
  
}
	
	
	
	
	
	
 
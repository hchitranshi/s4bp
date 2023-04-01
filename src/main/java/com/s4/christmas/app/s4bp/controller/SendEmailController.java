package com.s4.christmas.app.s4bp.controller;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.s4.christmas.app.s4bp.entities.MailHistory;
import com.s4.christmas.app.s4bp.model.BusinessPartnerModel;
import com.s4.christmas.app.s4bp.service.MailHistoryService;
import com.s4.christmas.app.s4bp.service.S4BusinessPartnerMailService;
import com.s4.christmas.app.s4bp.service.S4HanaBusinessPartnerService;

@RestController
public class SendEmailController {
	
	@Autowired
	private S4HanaBusinessPartnerService partnerService;
	
	@Autowired
	private S4BusinessPartnerMailService mailService;
	
	@Autowired
	private MailHistoryService mailHistoryService;
	
	@PostMapping("/api/sendMessage")
	public ResponseEntity sendEmail() {
		List<BusinessPartnerModel> businessPartners = partnerService.readS4HANABusinessPartner();
		if(businessPartners.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		businessPartners.stream().forEach(partner -> {
			boolean status = mailService.sendMail(partner.getEmailId(), 
					"Wish You Merry Christmas 2022", 
					"This message has been sent for Extensibility Demo.");
			MailHistory mailHistoryEntity;
			if(status) {
				mailHistoryEntity = MailHistory.builder()
						.status("COMPLETED")
						.toAddress(partner.getEmailId())
						.sentDate(new Timestamp(System.currentTimeMillis()))
						.build();
				
			}else {
				mailHistoryEntity = MailHistory.builder()
						.status("FAILED")
						.toAddress(partner.getEmailId())
						.sentDate(new Timestamp(System.currentTimeMillis()))
						.build();
			}
			mailHistoryService.save(mailHistoryEntity);
		});
		return ResponseEntity.ok().body(null);		
	}

}

package com.s4.christmas.app.s4bp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.s4.christmas.app.s4bp.entities.MailHistory;
import com.s4.christmas.app.s4bp.repo.MailHistoryRepo;

@Service
public class MailHistoryService {
	
	@Autowired
	private MailHistoryRepo mailHistoryRepo;
	
	public void save(MailHistory mailHistory) {
		mailHistoryRepo.save(mailHistory);
	}

}

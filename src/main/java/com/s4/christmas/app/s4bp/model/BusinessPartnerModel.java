package com.s4.christmas.app.s4bp.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BusinessPartnerModel {
	
	private String partnerId;
	
	private String emailId;

}

package com.s4.christmas.app.s4bp.entities;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class MailHistory {
	
	private Long id;
	
	private Timestamp sentDate;
	
	private String toAddress;
	
	private String status;

}

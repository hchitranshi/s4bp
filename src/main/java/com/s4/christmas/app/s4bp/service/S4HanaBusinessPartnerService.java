package com.s4.christmas.app.s4bp.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Service;
import com.s4.christmas.app.s4bp.model.BusinessPartnerModel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class S4HanaBusinessPartnerService {
	
	public List<BusinessPartnerModel> readS4HANABusinessPartner() {
        Connection connection = null;
        List<BusinessPartnerModel> returnList = new ArrayList<BusinessPartnerModel>();
        try {  
            connection = DriverManager.getConnection(  
                "jdbc:sap://dummy_host:0/?KEY=USER1UserKey&encrypt=true&validateCertificate=false");
        }
        catch (SQLException e) {
        	log.error("Get Connection Failed {}",e.getMessage());
            return Collections.emptyList();
        }
        if(connection == null) {
        	log.error("Connection is Null");
        	return Collections.emptyList();
        }
        try {
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt
            		.executeQuery("SELECT BUT020.PARTNER, ADR6.SMTP_ADDR, BUT020.ADDRNUMBER "
            				+ "FROM SAPABAP1.BUT020 "
            				+ "INNER JOIN SAPABAP1.ADR6 ON BUT020.ADDRNUMBER=ADR6.ADDRNUMBER;");
            while (resultSet.next()) {
            	BusinessPartnerModel partnerModel = BusinessPartnerModel.builder()
            			.partnerId(resultSet.getString(1))
            			.emailId(resultSet.getString(2))           			
            			.build();
            	returnList.add(partnerModel);
            }
        }
        catch (SQLException e) {
            System.err.println("Query failed!");
            return Collections.emptyList();
        }
        
        return returnList;
    }
}

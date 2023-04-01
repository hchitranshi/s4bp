package com.s4.christmas.app.s4bp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.s4.christmas.app.s4bp.entities.MailHistory;

@Repository
public interface MailHistoryRepo extends JpaRepository<MailHistory, Long>{

}

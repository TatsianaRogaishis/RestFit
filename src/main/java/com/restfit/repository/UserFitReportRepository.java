package com.restfit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restfit.model.User;
import com.restfit.model.UserFitReport;

@Repository("userFitReportRepository")
public interface UserFitReportRepository extends JpaRepository<UserFitReport, Long> {
	List<UserFitReport> findByUser(User user);
}

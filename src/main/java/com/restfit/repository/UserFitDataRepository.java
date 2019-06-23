package com.restfit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restfit.model.User;
import com.restfit.model.UserFitData;

@Repository("userFitDataRepository")
public interface UserFitDataRepository extends JpaRepository<UserFitData, Long> {
	List<UserFitData> findByUser(User user);
}

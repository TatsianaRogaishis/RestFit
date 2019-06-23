package com.restfit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restfit.model.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository < User, Long > {
    User findByLogin(String login);
}
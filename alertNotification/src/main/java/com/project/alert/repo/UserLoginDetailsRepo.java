package com.project.alert.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.alert.model.UserLoginDetails;

public interface UserLoginDetailsRepo extends JpaRepository<UserLoginDetails, String> {

}

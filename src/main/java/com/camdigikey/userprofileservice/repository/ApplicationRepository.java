package com.camdigikey.userprofileservice.repository;

import com.camdigikey.userprofileservice.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application, Integer> {

  Optional<Application> findApplicationByAppId(String appId);
}

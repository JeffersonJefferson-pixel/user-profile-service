package com.camdigikey.userprofileservice.repository;

import com.camdigikey.userprofileservice.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Integer> {
}

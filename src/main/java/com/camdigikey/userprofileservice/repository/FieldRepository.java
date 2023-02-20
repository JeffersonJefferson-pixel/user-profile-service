package com.camdigikey.userprofileservice.repository;

import com.camdigikey.userprofileservice.model.Field;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FieldRepository extends JpaRepository<Field, Integer> {
}

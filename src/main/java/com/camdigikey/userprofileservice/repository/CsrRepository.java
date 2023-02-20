package com.camdigikey.userprofileservice.repository;

import com.camdigikey.userprofileservice.model.Csr;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CsrRepository extends JpaRepository<Csr, Integer> {
}

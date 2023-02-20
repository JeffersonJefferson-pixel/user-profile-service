package com.camdigikey.userprofileservice.repository;

import com.camdigikey.userprofileservice.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfile, Integer> {

  @Query("select u from UserProfile u where u.application.appId = :appId")
  Optional<UserProfile> getUserProfileByAppId(@Param("appId") String appId);

  Optional<UserProfile> getUserProfileByCamDigiKeyId(String camDigiKeyID);
}

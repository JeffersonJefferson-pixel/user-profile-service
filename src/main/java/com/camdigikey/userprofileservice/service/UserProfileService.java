package com.camdigikey.userprofileservice.service;

import com.camdigikey.userprofileservice.model.UserProfile;
import com.camdigikey.userprofileservice.repository.ApplicationRepository;
import com.camdigikey.userprofileservice.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {

  UserProfileRepository userProfileRepo;

  ApplicationRepository appRepo;

  @Autowired
  public UserProfileService(UserProfileRepository userProfileRepo, ApplicationRepository appRepo) {
    this.userProfileRepo = userProfileRepo;
    this.appRepo = appRepo;
  }

  public String generateCamDigiKeyId(String appId) {
    // get user profile associated with the application
    UserProfile userProfile = userProfileRepo.getUserProfileByAppId(appId).orElseThrow();

    // generate camdigikey id based on the user profile info
    return "abc";
  }

  public void notifyUser() {

  }
}

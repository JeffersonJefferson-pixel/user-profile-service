package com.camdigikey.userprofileservice.service;

import com.camdigikey.userprofileservice.dto.ApplicationReviewDto;
import com.camdigikey.userprofileservice.model.Application;
import com.camdigikey.userprofileservice.model.Field;
import com.camdigikey.userprofileservice.model.Status;
import com.camdigikey.userprofileservice.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicationService {

  ApplicationRepository applicationRepo;

  @Autowired
  public ApplicationService(ApplicationRepository applicationRepo) {
    this.applicationRepo = applicationRepo;
  }

  public Application updateApp(ApplicationReviewDto appReviewDto) {
    Application app = applicationRepo.findApplicationByAppId(appReviewDto.getAppId()).orElseThrow();

    Status statusObj = Status.builder()
        .id(appReviewDto.getStatus())
        .build();
    List<Field> incorrectFieldObjs = appReviewDto.getIncorrectFields()
        .stream()
        .map(field -> Field.builder().id(field).build())
        .collect(Collectors.toList());

    app.setStatus(statusObj);
    app.setReviewMessage(appReviewDto.getReviewMessage());
    app.getIncorrectFields().addAll(incorrectFieldObjs);
    applicationRepo.save(app);

    return app;
  }

}

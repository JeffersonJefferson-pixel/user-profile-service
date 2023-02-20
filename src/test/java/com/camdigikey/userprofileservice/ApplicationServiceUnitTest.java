package com.camdigikey.userprofileservice;

import com.camdigikey.userprofileservice.dto.ApplicationReviewDto;
import com.camdigikey.userprofileservice.model.Application;
import com.camdigikey.userprofileservice.model.Field;
import com.camdigikey.userprofileservice.model.Status;
import com.camdigikey.userprofileservice.repository.ApplicationRepository;
import com.camdigikey.userprofileservice.repository.FieldRepository;
import com.camdigikey.userprofileservice.repository.StatusRepository;
import com.camdigikey.userprofileservice.service.ApplicationService;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@ActiveProfiles("test")
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ApplicationServiceUnitTest {

  @Autowired
  private ApplicationService appSvc;

  @Autowired
  private StatusRepository statusRepo;

  @Autowired
  private FieldRepository fieldRepo;

  @Autowired
  private ApplicationRepository appRepo;

  private Status testStatus;

  private Field testField;

  private Application testApp;

  private ApplicationReviewDto appReviewDto;

  @BeforeAll
  public void setUp() {
    testStatus = Status.builder()
        .key("test")
        .value("test")
        .build();
    statusRepo.save(testStatus);

    testField = Field.builder()
        .key("test")
        .value("test")
        .build();
    fieldRepo.save(testField);

    testApp = Application.builder()
        .build();
    appRepo.save(testApp);

    appReviewDto = ApplicationReviewDto.builder()
        .appId(testApp.getAppId())
        .reviewMessage("test")
        .incorrectFields(List.of(testField.getId()))
        .status(testStatus.getId())
        .build();
  }

  @Test
  public void givenAppExist_whenUpdate_thenSucceed() {
    Application expectedApp = Application.builder()
        .appId(testApp.getAppId())
        .status(testStatus)
        .incorrectFields(List.of(testField))
        .reviewMessage("test")
        .build();

    Application updatedApp = appSvc.updateApp(appReviewDto);

    assertEquals(expectedApp, updatedApp);
  }
}

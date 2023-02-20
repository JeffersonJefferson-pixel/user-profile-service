package com.camdigikey.userprofileservice.listener;

import com.camdigikey.userprofileservice.dto.ApplicationReviewDto;
import com.camdigikey.userprofileservice.mapper.MapStructMapper;
import com.camdigikey.userprofileservice.model.Application;
import com.camdigikey.userprofileservice.service.ApplicationService;
import com.camdigikey.userprofileservice.service.UserProfileService;
import com.camdigikey.userprofileservice.schema.ApplicationReviewMsg;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
@Slf4j
public class ReviewListener {

  private ApplicationService appSvc;

  private UserProfileService userProfileSvc;

  private MapStructMapper mapper;

  @Getter
  private CountDownLatch latch;

  @Autowired
  public ReviewListener(ApplicationService appSvc, UserProfileService userProfileSvc, MapStructMapper mapper) {
    this.mapper = mapper;
    this.appSvc = appSvc;
    this.userProfileSvc = userProfileSvc;
    this.latch = new CountDownLatch(1);
  }

  @KafkaListener(
      topics = "${kafka.topic.application-review}",
      groupId = "groupId",
      containerFactory = "genericListenerFactory"
  )
  void listen(ApplicationReviewMsg message) {
    log.info("Consuming application-reviewed message");

    ApplicationReviewDto appReviewDto = mapper.appReviewMsgToAppReviewDto(message);

    try {
      String camDigiKeyId = userProfileSvc.generateCamDigiKeyId(appReviewDto.getAppId());
      Application updatedApp = appSvc.updateApp(appReviewDto);



    } catch (Exception e) {
      e.printStackTrace();
    }

    latch.countDown();
  }

  public void resetLatch() {
    latch = new CountDownLatch(1);
  }
}

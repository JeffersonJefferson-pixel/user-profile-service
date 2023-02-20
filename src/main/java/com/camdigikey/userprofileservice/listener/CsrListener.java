package com.camdigikey.userprofileservice.listener;

import com.camdigikey.userprofileservice.dto.ApplicationReviewDto;
import com.camdigikey.userprofileservice.dto.CsrReplyDto;
import com.camdigikey.userprofileservice.mapper.MapStructMapper;
import com.camdigikey.userprofileservice.schema.CsrReplyMsg;
import com.camdigikey.userprofileservice.service.CsrService;
import com.camdigikey.userprofileservice.service.UserProfileService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

@Component
@Slf4j
public class CsrListener {

  private CsrService csrSvc;

  private MapStructMapper mapper;

  @Getter
  private CountDownLatch latch;

  @Autowired
  public CsrListener(CsrService csrSvc, MapStructMapper mapper) {
    this.mapper = mapper;
    this.csrSvc = csrSvc;
    this.latch = new CountDownLatch(1);
  }

  @KafkaListener(
      topics = "${kafka.topic.csr.reply}",
      groupId = "groupId",
      containerFactory = "genericListenerFactory"
  )
  void listen(CsrReplyMsg message) {
    log.info("Consuming csr-reply message");

    CsrReplyDto csrReplyDto = mapper.csrReplyMsgToCsrReplyDto(message);

    try {
      String downloadUrl = UUID.randomUUID().toString();
      csrSvc.updateCsr(
          csrReplyDto.getAppId(),
          csrReplyDto.getCertChain(),
          downloadUrl
      );
    } catch (Exception e) {
      e.printStackTrace();
    }

    latch.countDown();
  }

  public void resetLatch() {
    latch = new CountDownLatch(1);
  }
}

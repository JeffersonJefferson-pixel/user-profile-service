package com.camdigikey.userprofileservice.producer;

import com.camdigikey.userprofileservice.dto.NotificationDto;
import com.camdigikey.userprofileservice.mapper.MapStructMapper;
import com.camdigikey.userprofileservice.schema.NotificationMsg;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
@Slf4j
public class NotificationProducer {

  @Value("${kafka.topic.notification}")
  private String topic;

  private KafkaTemplate genericKafkaTemplate;

  private MapStructMapper mapper;
  @Getter
  private CountDownLatch latch;

  @Autowired
  public NotificationProducer(KafkaTemplate genericKafkaTemplate, MapStructMapper mapper) {
    this.genericKafkaTemplate = genericKafkaTemplate;
    this.mapper = mapper;
    this.latch = new CountDownLatch(1);
  }

  public void produce(NotificationDto notifDto) {
    log.info("Producing notification message");
    NotificationMsg notifMsg = mapper.notifDtoToNotifMsg(notifDto);

    genericKafkaTemplate.send(topic, notifMsg);
  }

  public void resetLatch() {
    latch = new CountDownLatch(1);
  }
}

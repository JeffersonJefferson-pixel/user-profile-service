package com.camdigikey.userprofileservice.producer;

import com.camdigikey.userprofileservice.mapper.MapStructMapper;
import com.camdigikey.userprofileservice.dto.CsrRequestDto;
import com.camdigikey.userprofileservice.schema.CsrMsg;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
@Slf4j
public class CsrProducer {

  @Value("${kafka.topic.csr.request}")
  private String topic;

  private KafkaTemplate genericKafkaTemplate;

  private MapStructMapper mapper;
  @Getter
  private CountDownLatch latch;

  @Autowired
  public CsrProducer(KafkaTemplate genericKafkaTemplate, MapStructMapper mapper) {
    this.genericKafkaTemplate = genericKafkaTemplate;
    this.mapper = mapper;
    this.latch = new CountDownLatch(1);
  }

  public void produce(CsrRequestDto csrRequestDto) {
    log.info("Producing csr-request message");
    CsrMsg csrMsg = mapper.csrRequestDtoToCsrRequestMsg(csrRequestDto);

    genericKafkaTemplate.send(topic, csrMsg);
  }

  public void resetLatch() {
    latch = new CountDownLatch(1);
  }
}

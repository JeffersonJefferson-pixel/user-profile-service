package com.camdigikey.userprofileservice.mapper;

import com.camdigikey.userprofileservice.dto.ApplicationReviewDto;
import com.camdigikey.userprofileservice.dto.CsrReplyDto;
import com.camdigikey.userprofileservice.dto.CsrRequestDto;
import com.camdigikey.userprofileservice.dto.NotificationDto;
import com.camdigikey.userprofileservice.schema.ApplicationReviewMsg;
import com.camdigikey.userprofileservice.schema.CsrMsg;
import com.camdigikey.userprofileservice.schema.CsrReplyMsg;
import com.camdigikey.userprofileservice.schema.NotificationMsg;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapStructMapper {
  ApplicationReviewDto appReviewMsgToAppReviewDto(ApplicationReviewMsg appReviewMsg);

  CsrMsg csrRequestDtoToCsrRequestMsg(CsrRequestDto csrRequestDto);

  CsrReplyDto csrReplyMsgToCsrReplyDto(CsrReplyMsg csrReplyMsg);

  NotificationMsg notifDtoToNotifMsg(NotificationDto notifDto);
}

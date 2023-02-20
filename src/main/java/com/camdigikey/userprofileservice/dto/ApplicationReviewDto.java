package com.camdigikey.userprofileservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationReviewDto {

  private String appId;
  private Integer status;
  private String reviewMessage;
  private List<Integer> incorrectFields;
}

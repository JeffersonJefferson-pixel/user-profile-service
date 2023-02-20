package com.camdigikey.userprofileservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CsrRequestDto {

  private String appId;

  private String camDigiKeyId;

  private String csr;
}

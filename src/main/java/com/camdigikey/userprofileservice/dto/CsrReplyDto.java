package com.camdigikey.userprofileservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CsrReplyDto {

  private String appId;

  private String camDigiKeyId;

  private String csr;

  private String certChain;
}

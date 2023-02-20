package com.camdigikey.userprofileservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Csr {

  @Id
  @SequenceGenerator(
      name = "csr_sequence",
      sequenceName = "csr_sequence"
  )
  @GeneratedValue(
      strategy = GenerationType.SEQUENCE,
      generator = "csr_sequence"
  )
  private Integer id;

  private String csr;

  private String certChain;

  private String downloadUrl;

}

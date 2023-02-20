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
public class Status {

  @Id
  @SequenceGenerator(
      name = "status_sequence",
      sequenceName = "status_sequence"
  )
  @GeneratedValue(
      strategy = GenerationType.SEQUENCE,
      generator = "status_sequence"
  )
  private Integer id;
  private String key;
  private String value;
}

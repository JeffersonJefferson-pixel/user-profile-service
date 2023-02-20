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
public class Field {
  @Id
  @SequenceGenerator(
      name = "field_sequence",
      sequenceName = "field_sequence"
  )
  @GeneratedValue(
      strategy = GenerationType.SEQUENCE,
      generator = "field_sequence"
  )
  private Integer id;
  private String key;
  private String value;
}

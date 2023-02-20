package com.camdigikey.userprofileservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserProfile {
  @Id
  @SequenceGenerator(
      name = "user_profile_sequence",
      sequenceName = "user_profile_sequence"
  )
  @GeneratedValue(
      strategy = GenerationType.SEQUENCE,
      generator = "user_profile_sequence"
  )
  private Integer id;

  private String identityNumber;

  private String name;

  private LocalDate dateOfBirth;

  private String gender;

  private String nationality;

  private LocalDate dateOfIssue;

  private LocalDate dateOfExpiry;

  private String camDigiKeyId;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "application_id", referencedColumnName = "id")
  private Application application;
}

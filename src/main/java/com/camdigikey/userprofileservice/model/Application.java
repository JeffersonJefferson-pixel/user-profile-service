package com.camdigikey.userprofileservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="APPLICATION")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Application {

  @Id
  @SequenceGenerator(
      name = "application_sequence",
      sequenceName = "application_sequence"
  )
  @GeneratedValue(
      strategy = GenerationType.SEQUENCE,
      generator = "application_sequence"
  )
  private Integer id;

  private String appId;

  private String reviewMessage;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "user_profile_id", referencedColumnName = "id")
  private UserProfile userProfile;

  @OneToMany(mappedBy = "application")
  private List<Field> incorrectFields;

  @ManyToOne
  @JoinColumn(name = "status_id")
  private Status status;

  @ManyToOne
  @JoinColumn(name = "csr_id")
  private Csr csr;



  @ManyToOne
  private Application application;

}

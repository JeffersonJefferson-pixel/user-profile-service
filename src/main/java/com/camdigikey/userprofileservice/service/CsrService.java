package com.camdigikey.userprofileservice.service;

import com.camdigikey.userprofileservice.model.Application;
import com.camdigikey.userprofileservice.model.Csr;
import com.camdigikey.userprofileservice.repository.ApplicationRepository;
import com.camdigikey.userprofileservice.repository.CsrRepository;
import com.camdigikey.userprofileservice.repository.UserProfileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CsrService {

  private CsrRepository csrRepo;

  private ApplicationRepository appRepo;

  private UserProfileRepository userProfileRepo;

  @Autowired
  public CsrService(
      CsrRepository csrRepo,
      ApplicationRepository appRepo,
      UserProfileRepository userProfileRepo
  ) {
    this.csrRepo = csrRepo;
    this.appRepo = appRepo;
    this.userProfileRepo = userProfileRepo;
  }
  public Csr insertCsr(String camDigiKeyId, String csr) {
    Application app = userProfileRepo.getUserProfileByCamDigiKeyId(camDigiKeyId)
        .orElseThrow()
        .getApplication();

    Csr csrObj = Csr.builder()
        .csr(csr)
        .build();
    csrRepo.save(csrObj);

    app.setCsr(csrObj);
    appRepo.save(app);

    return csrObj;
  }

  public Csr updateCsr(String appId, String certChain, String downloadUrl) {
    Csr csr = appRepo.findApplicationByAppId(appId)
        .orElseThrow()
        .getCsr();

    csr.setCertChain(certChain);
    csr.setDownloadUrl(downloadUrl);

    return csrRepo.save(csr);
  }
}

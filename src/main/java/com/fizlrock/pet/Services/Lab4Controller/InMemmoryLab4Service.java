package com.fizlrock.pet.Services.Lab4Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.fizlrock.pet.Domain.DTO.Lab4Version;

/**
 * InMemmoryLab4Controller
 */
@Component
public class InMemmoryLab4Service implements Lab4Service {

  List<Lab4Version> versions;

  {
    versions = List.of(
        new Lab4Version(1, "123", "123"),
        new Lab4Version(2, "1292433", "123"),
        new Lab4Version(3, "122403", "123320"),
        new Lab4Version(4, "arstn43123", "arst123"));
  }

  @Override
  public List<Lab4Version> getAllSavedVersions() {
    return versions;
  }

  @Override
  public void addVersion(Lab4Version v) {
    versions.add(v);
  }

  @Override
  public Optional<Lab4Version> getVersionById(int id) {
    return versions.stream()
        .filter(o -> o.versionNum == id)
        .findAny();
  }

}

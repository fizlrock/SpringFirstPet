package com.fizlrock.pet.Services.Lab4Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fizlrock.pet.Domain.Lab4Option;

/**
 * InMemmoryLab4Controller
 */
@Component
public class InMemmoryLab4Service implements Lab4Service {

  List<Lab4Option> versions;

  {
    versions = List.of(
        new Lab4Option(1, "123", "123"),
        new Lab4Option(2, "1292433", "123"),
        new Lab4Option(3, "122403", "123320"),
        new Lab4Option(4, "arstn43123", "arst123"));
  }

  @Override
  public List<Lab4Option> getAllSavedVersions() {
    return versions;
  }

  @Override
  public void addVersion(Lab4Option v) {
    versions.add(v);
  }

}

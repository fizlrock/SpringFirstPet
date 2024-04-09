
package com.fizlrock.pet.Services.Lab4Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fizlrock.pet.Domain.DTO.Lab4Version;
import com.fizlrock.pet.Domain.DTO.Task1Report;
import com.fizlrock.pet.Domain.DTO.Task2Report;

/**
 * Lab4Controller
 */
@Component
@Scope("singleton")
public interface Lab4Service {
  public List<Lab4Version> getAllSavedVersions();

  public void addVersion(Lab4Version v);

  public Optional<Lab4Version> getVersionById(int id);

  public Optional<Task1Report> getTask1Report(int id);
  public Optional<Task2Report> getTask2Report(int id);

}

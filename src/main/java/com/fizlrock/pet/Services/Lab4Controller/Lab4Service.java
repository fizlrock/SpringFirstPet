
package com.fizlrock.pet.Services.Lab4Controller;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fizlrock.pet.Domain.Lab4Option;

/**
 * Lab4Controller
 */
@Component
@Scope("singleton")
public interface Lab4Service {
  public List<Lab4Option> getAllSavedVersions();
  public void addVersion(Lab4Option v);

}

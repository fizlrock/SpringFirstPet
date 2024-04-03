
package com.fizlrock.pet.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fizlrock.pet.Domain.DTO.Lab4Version;
import com.fizlrock.pet.Services.Lab4Controller.Lab4Service;

import lombok.AllArgsConstructor;

/**
 * VersionsController
 */
@Controller
@RequestMapping("/version")
@AllArgsConstructor
public class VersionsController {

  private Lab4Service service;

  @GetMapping("/all")
  public String getAll(Model model) {
    model.addAttribute("allVersions", service.getAllSavedVersions());
    return "version/all";
  }

  @GetMapping("/new")
  public String versionConstructor(Model model) {
    model.addAttribute("version", new Lab4Version());
    return "version/new";
  }

  @GetMapping("/{id}")
  public String showVersion(@PathVariable("id") int id, Model model) {
    var version = service.getVersionById(id);
    if (version.isPresent()) {
      model.addAttribute("version", version.get());
      return "version/card";
    }
    return "404";
  }

  @PostMapping("/create")
  public String addVersion(Lab4Version version) {

    service.addVersion(version);

    return "version/all";
  }

}


package com.fizlrock.pet.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fizlrock.pet.Domain.Lab4Option;
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
    model.addAttribute("version", new Lab4Option());
    return "version/new";
  }

  @PostMapping("/create")
  public String addVersion(Lab4Option version) {

    service.addVersion(version);

    return "allversions";
  }

}

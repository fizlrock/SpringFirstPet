
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
import lombok.extern.slf4j.Slf4j;

/**
 * VersionsController
 */
@Slf4j
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
    var report1 = service.getTask1Report(id);
    var report2 = service.getTask2Report(id);
    if (report1.isEmpty()) {
      log.error("Отчет 1 пуст");
      return "404";
    }
    if (report2.isEmpty()) {
      log.error("Отчет 2 пуст");
      return "404";
    }
    if (version.isPresent()) {

      model.addAttribute("version", version.get());
      model.addAttribute("task1", report1.get());
      model.addAttribute("task2", report2.get());
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


package com.fizlrock.pet.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * HomeController
 */
@Controller
@RequestMapping
public class HomeController {

  @GetMapping("/")
  public String getHome() {
    return "homePage";

  }

}

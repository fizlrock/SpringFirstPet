package com.fizlrock.pet.Domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.fizlrock.pet.Domain.DTO.Lab4Version;

/**
 * Lab4VersionProvider
 */
public class Lab4VersionProvider {

  public static List<Lab4Version> loadVersions() {

    List<Lab4Version> result = new ArrayList<>();

    ClassLoader classloader = Thread.currentThread().getContextClassLoader();
    var is = classloader.getResourceAsStream("labVersions.csv");
    try (Scanner s = new Scanner(is)) {
      while (s.hasNextLine()) {
        try {
          result.add(parseLine(s.nextLine()));
        } catch (Exception e) {
          System.out.printf("Ошибка парсинга строки" + e);
        }
      }
    }
    return result;
  }

  private static Lab4Version parseLine(String line) {
    String[] parts = line.split(",");
    return new Lab4Version(Integer.parseInt(parts[0]), parts[1], parts[2]);
  }

}

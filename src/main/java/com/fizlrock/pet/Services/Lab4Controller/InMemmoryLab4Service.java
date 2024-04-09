package com.fizlrock.pet.Services.Lab4Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fizlrock.pet.Domain.Lab4Executor;
import com.fizlrock.pet.Domain.Lab4VersionProvider;
import com.fizlrock.pet.Domain.DTO.Lab4Version;
import com.fizlrock.pet.Domain.DTO.Task1Report;
import com.fizlrock.pet.Domain.DTO.Task2Report;

import lombok.extern.slf4j.Slf4j;

/**
 * InMemmoryLab4Controller
 */
@Slf4j
@Component
public class InMemmoryLab4Service implements Lab4Service {

  @Autowired
  Lab4Executor executor;

  List<Lab4Version> versions;
  Map<Integer, Task1Report> task1reports = new HashMap<>();
  Map<Integer, Task2Report> task2reports = new HashMap<>();

  {
    // versions = List.of(
    // new Lab4Version(1, "01100011", "011010100011"),
    // new Lab4Version(2, "1292433", "123"),
    // new Lab4Version(3, "122403", "123320"),
    // new Lab4Version(4, "arstn43123", "arst123"));

    try {
      versions = Lab4VersionProvider.loadVersions();
    } catch (Exception e) {
      log.error("Ошибка загрузки вариантов из файла: {}", e);
      versions = new ArrayList<>();
    }
    log.info("Сервис ЛР4 создан");
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

  @Override
  public Optional<Task1Report> getTask1Report(int id) {
    log.info("Запрос отчета к задачи 1 варианта {}", id);
    var result = Optional.ofNullable(task1reports.get(id));
    if (result.isEmpty())
      result = Optional.of(executeTask1(id));
    return result;
  }

  @Override
  public Optional<Task2Report> getTask2Report(int id) {
    var result = Optional.ofNullable(task2reports.get(id));
    if (result.isEmpty())
      result = Optional.of(executeTask2(id));
    return result;
  }

  private Task1Report executeTask1(int versionNum) {
    log.info("Выполнение задания 1.");
    var version = versions.stream().filter(v -> v.versionNum == versionNum).findAny();

    if (version.isPresent()) {
      var result = executor.executeTask1(version.get().number1);
      task1reports.put(versionNum, result);
      log.info("Выполено задание 1 для варианта {} : {}", versionNum, result);
      return result;

    } else {
      log.warn("Запрос задания 1 для несуществующего варинта: {}", versionNum);
      return null;
    }
  }

  private Task2Report executeTask2(int versionNum) {
    var version = versions.stream().filter(v -> v.versionNum == versionNum).findAny();

    if (version.isPresent())
      return executor.executeTask2(version.get().number1);
    else
      return null;

  }

}

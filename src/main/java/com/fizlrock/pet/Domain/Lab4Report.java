
package com.fizlrock.pet.Domain;

import lombok.AllArgsConstructor;
import lombok.Builder;

/**
 * Отчет по 4 лабораторной работе.
 * Хранит:
 * <ol>
 * <li>Номер варианта ЛР
 * <li>Статус решения(Решено, Не решено, Ошибка)
 * <li>Отчет по заданиям
 * </ol>
 */

@AllArgsConstructor
@Builder
public class Lab4Report {
  private final int versionNum;

}

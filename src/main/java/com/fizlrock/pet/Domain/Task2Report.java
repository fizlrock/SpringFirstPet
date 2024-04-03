package com.fizlrock.pet.Domain;

import lombok.AllArgsConstructor;

/**
 * Task2Report
 * Хранит:
 * <ul>
 * <li>Кол-во бит в исходном сообщении
 * <li>Кол-во бит в закодированном сообщении
 * <li>Номера контрольных бит
 * <li>Номера информацонных бит
 * <li>Список шагов кодирования. 
 * </ul>
 */
public class Task2Report {

  /**
   * Шаг кодирования содержит:
   * <ul>
   * <li>Номер шага (текущего расчитываемого контрольного бита) 
   * <li>Текущее состояния буффера 
   * <li>Номера бит, используемых для расчета
   * <li>Краткий коментарий (1 + 0 + 1... = 34)
   * <li>Результат расчета (контрольного бита)
   * </ul>
   */
  @AllArgsConstructor
  public static class CodingStep {
    private final int stepNum;
    private final int[] bufferState;
    private final int[] usedBits;
    private final String comment;

  }

}

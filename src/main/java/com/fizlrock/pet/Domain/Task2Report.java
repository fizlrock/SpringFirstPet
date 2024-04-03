package com.fizlrock.pet.Domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;

/**
 * Хранит:
 * <ul>
 * <li>Кол-во бит в исходном сообщении
 * <li>Кол-во бит в закодированном сообщении
 * <li>Номера контрольных бит
 * <li>Номера информацонных бит
 * <li>Список шагов кодирования.
 * </ul>
 */
@Builder
public record Task2Report(
    int dataSize,
    int cryptedDataSize,
    int[] dataBits,
    int[] controlBits,
    List<CodingStep> steps) {

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
  @Builder
  public static record CodingStep(
      int stepNum,
      int[] bufferState,
      int[] usedBits,
      String comment) {
  };
}

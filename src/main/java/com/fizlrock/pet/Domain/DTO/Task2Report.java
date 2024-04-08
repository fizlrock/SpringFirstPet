package com.fizlrock.pet.Domain.DTO;

import java.util.List;

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
    String input,
    String output,
    String comment,
    TaskState state,
    int dataSize,
    int cryptedDataSize,
    List<Integer> dataBitsIndexes,
    List<Integer> controlBitsIndexes,
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
      int controlBitIndex,
      List<Integer> bufferState,
      List<Integer> usedBits,
      String comment) {
  };
}

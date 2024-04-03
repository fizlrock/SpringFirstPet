package com.fizlrock.pet.Domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * Бит четности. В начало байта ставится единица, если число единиц в исходнных
 * данных четное, иначе ставится 0.
 * Пример.
 * <ul>
 * <li>input: 01010100
 * <li>01010100 - 3 единици - нечетное число
 * <li>output : 001010100
 * </ul>
 */

@AllArgsConstructor
@Builder
@Getter
public class Task1Report {
  private String input;
  private String result;
  private String comment;
  private TaskState state;
}

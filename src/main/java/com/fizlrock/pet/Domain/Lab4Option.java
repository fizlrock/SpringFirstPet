
package com.fizlrock.pet.Domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <p>
 * Описание варианта лабораторной работы.
 * </p>
 * Задание:
 * <ol>
 * <li>Вычислить значение бита паритета к заданному информационному байту.
 * <li>Построить код Хемминга для информационного байта.
 * <li>Укажите номер бита с ошибкой в коде Хемминга (использовать логические
 * рассуждения и алгоритм Хемминга).
 * </ol>
 * 
 * Содержит следующие поля:
 * <ol>
 * <li>Номер варинта. Целое положительное число
 * <li>Информационный байт. Строка, состоящая из 8 символов(0 или 1)
 * <li>Код Хемминга с ошибкой. Строка, состоящая из 12 символов(0 или 1)
 * </ol>
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Lab4Option {

  public int versionNum;
  public String number1;
  public String number2;

  public int parityBit;

  @Override
  public int hashCode() {
    return (number1 + number2).hashCode();
  }

}

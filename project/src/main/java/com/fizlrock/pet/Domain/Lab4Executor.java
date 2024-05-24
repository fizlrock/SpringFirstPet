
package com.fizlrock.pet.Domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import com.fizlrock.pet.Domain.DTO.Task1Report;
import com.fizlrock.pet.Domain.DTO.Task2Report;
import com.fizlrock.pet.Domain.DTO.TaskState;
import com.fizlrock.pet.Domain.DTO.Task2Report.CodingStep;

/**
 * Task1Executor
 */
@Component
public class Lab4Executor {

  public static class BinaryValue {

    private int size;
    private boolean[] bits;

    public BinaryValue(int bits_count) {
      size = bits_count;
      bits = new boolean[bits_count];
    }

    public BinaryValue(String line) {
      var symbolStats = line.chars().boxed().collect(Collectors.groupingBy(x -> x, Collectors.counting()));
      size = line.length();
      var expected_set = Set.of(48, 49);
      if (!symbolStats.keySet().equals(expected_set))
        throw new IllegalArgumentException("Строка содержит недопустимые симоволы");

      bits = new boolean[line.length()];
      for (int i = 0; i < bits.length; i++)
        if (line.charAt(i) == '1')
          bits[i] = true;
      System.out.println(Arrays.toString(bits));

    }

    public long onesCount() {
      long counter = 0;
      for (boolean b : bits)
        if (b)
          counter++;

      return counter;
    }

    public long zerosCount() {
      long counter = 0;
      for (boolean b : bits)
        if (!b)
          counter++;

      return counter;
    }

    /**
     * Расчет бита четности для значений по указанным индексам.
     * 
     * @param indexes
     * @return
     */
    public boolean getParityBit(List<Integer> indexes) {
      int result = 0;
      for (int index : indexes)
        if (getBit(index))
          result += 1;
      return result % 2 != 0;
    }

    public boolean getBit(int index) {
      return bits[index];
    }

    public void setBit(int index, boolean value) {
      bits[index] = value;
    }

    @Override
    public String toString() {
      char[] chars = new char[size];
      for (int i = 0; i < size; i++)
        chars[i] = bits[i] ? '1' : '0';
      return new String(chars);
    }

    public List<Integer> getBufferState() {
      List<Integer> nums = new ArrayList<>();
      for (int i = 0; i < size; i++)
        nums.add(bits[i] ? 1 : 0);
      return nums;

    }

  }

  public Task2Report executeTask2(String line) {
    var report = Task2Report.builder();

    report.state(TaskState.NotResolved);
    report.input(line);

    BinaryValue input;
    try {
      input = new BinaryValue(line);
    } catch (Exception e) {
      report.state(TaskState.Failed);
      report.comment(e.getMessage());
      return report.build();
    }
    // Индексы контрольных бит
    var cb_nums = getCBIndexes(input.size);
    // Размер закодированного сообщения
    int output_size = input.size + cb_nums.size();

    // Формирование шаблона
    BinaryValue result = new BinaryValue(output_size);
    int cb_finded = 0;
    for (int i = 0; i < output_size; i++) {
      if (cb_nums.contains(i))
        cb_finded++;
      else
        result.setBit(i, input.getBit(i - cb_finded));
    }

    List<CodingStep> steps = new ArrayList<>();

    for (int cb_index : cb_nums) {
      var step = CodingStep.builder();
      var ibits = getInfoBits(cb_index, output_size);

      step.controlBitIndex(cb_index);
      step.usedBits(ibits);
      boolean cb_value = result.getParityBit(ibits);
      result.setBit(cb_index, cb_value);
      step.bufferState(result.getBufferState());

      var comment_parts = new StringJoiner("+");
      for (int i : ibits)
        comment_parts.add(String.valueOf(result.getBit(i) ? '1' : '0'));

      step.comment(comment_parts.toString() + "=" + String.valueOf(cb_value ? '1' : '0'));

      steps.add(step.build());
    }
    report.dataSize(input.size);
    report.cryptedDataSize(result.size);
    report.controlBitsIndexes(cb_nums);
    report.steps(steps);
    report.output(result.toString());

    return report.build();
  }

  public List<Integer> getCBIndexes(int message_size) {
    if (message_size <= 0)
      throw new IllegalArgumentException("Длина сообщения должна быть больше 0");

    List<Integer> result = new ArrayList<Integer>();
    int last_n = 1;
    while (last_n < message_size) {
      result.add(last_n - 1);
      last_n *= 2;
      message_size += 1;
    }
    return result;

  }

  public static List<Integer> getInfoBits(int cbIndex, int limit) {
    var result = new ArrayList<Integer>();
    System.out.println("Зашли в get для индекса:" + cbIndex);

    int delta = cbIndex + 1;
    int block = 0;

    for (int i = cbIndex; i < limit;) {
      if (block < delta) {
        result.add(i);
        block++;
        i++;
      } else {
        block = 0;
        i += delta;
      }
    }
    result.removeFirst();

    return result;
  }

  public Task1Report executeTask1(String line) {
    var report = Task1Report.builder();
    report.state(TaskState.NotResolved);
    report.input(line);

    BinaryValue bv;
    try {
      bv = new BinaryValue(line);
    } catch (Exception e) {
      report.state(TaskState.Failed);
      report.comment(e.getMessage());
      return report.build();
    }

    long one_count = bv.onesCount();
    String parityBit = "0";
    if (one_count % 2 == 1)
      parityBit = "1";
    var comment = String.format("%d %% 2 = %s, бит четности: %s", one_count, one_count % 2, parityBit);

    report.comment(comment);
    report.result(parityBit + line);
    report.state(TaskState.Successful);

    return report.build();
  }
}

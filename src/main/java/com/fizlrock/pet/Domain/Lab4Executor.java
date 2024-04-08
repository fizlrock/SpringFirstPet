
package com.fizlrock.pet.Domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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

  public static Task2Report executeTask2(String line) {
    var report = Task2Report.builder();

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
    var cb_nums = getCBIndexes(bv.size);

    List<CodingStep> steps = new ArrayList<>();

    for (int cb_index : cb_nums) {
      var ibits = getInfoBits(cb_index, bv.size + cb_nums.size());
      System.out.println(ibits);

    }

    return report.build();
  }

  public static List<Integer> getCBIndexes(int message_size) {
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

  public static class BinaryValue {

    private String line;
    private int size;
    private boolean[] bits;
    private Map<Integer, Long> symbolStats;

    public BinaryValue(String line) {
      this.line = line;
      symbolStats = line.chars().boxed().collect(Collectors.groupingBy(x -> x, Collectors.counting()));
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
      return symbolStats.get(49);
    }

    public long zerosCount() {
      return symbolStats.get(48);
    }

    /**
     * Расчет бита четности для значений по указанным индексам.
     * 
     * @param indexes
     * @return
     */
    public int getParityBit(int[] indexes) {
      int result = 0;
      for (int index : indexes)
        if (getBit(index))
          result += 1;
      return result % 2;
    }

    public boolean getBit(int index) {
      return bits[index];
    }

  }

  public static int log2(int n) {
    return (int) Math.ceil(Math.log(n) / Math.log(2));
  }

  public static List<Integer> getInfoBits(int cbIndex, int limit) {
    var result = new ArrayList<Integer>();
    System.out.println("Зашли в get");

    int blockSize = cbIndex + 1;
    int last = cbIndex;
    while (last <= limit) {
      if (blockSize < cbIndex) {
        result.add(last - 1);
        last++;
        blockSize++;
      } else {
        last += cbIndex;
        blockSize = 0;
      }

    }
    result.removeFirst();

    return result;
  }
}

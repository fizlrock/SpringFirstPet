package com.fizlrock.pet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.fizlrock.pet.Domain.Lab4Executor;
import com.fizlrock.pet.Domain.Lab4Executor.BinaryValue;

class PetApplicationTests {

	@Test
	void contextLoads() {
	}


	@Test
	void getParityBitTests() {
		BinaryValue br = new BinaryValue("001010100010");

		var testCases = Map.of(
				new int[] { 2, 4, 6, 8, 10 }, 0,
				new int[] { 2, 5, 6, 9, 10 }, 1,
				new int[] { 4, 5, 6, 11 }, 0,
				new int[] { 8, 9, 10, 11 }, 1);

		for (var e : testCases.entrySet()) {
			assertEquals(e.getValue(), br.getParityBit(e.getKey()), "On bits " + Arrays.toString(e.getKey()));
		}
	}

}

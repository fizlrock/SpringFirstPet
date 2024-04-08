package com.fizlrock.pet;

import java.util.Arrays;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fizlrock.pet.Domain.Lab4Executor;

@SpringBootApplication
public class PetApplication {

	public static void main(String[] args) {

		// SpringApplication.run(PetApplication.class, args);
		var report = Lab4Executor.executeTask2("11010010");
		System.out.println(report);

	}

}

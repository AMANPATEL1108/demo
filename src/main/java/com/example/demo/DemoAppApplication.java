package com.example.demo;

import com.example.demo.model.Alien;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DemoAppApplication {

	public static void main(String[] args) {

		ApplicationContext context=SpringApplication.run(DemoAppApplication.class, args);
		Alien alien1=context.getBean(Alien.class);

		alien1.setId(111);
		alien1.setName("Aman");
		alien1.setTech("java");


		AlienRepo repo=context.getBean(AlienRepo.class);
		repo.save(alien1);

		System.out.println(repo.findAll());
	}


}

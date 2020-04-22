package com.anibal.mimacher;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.anibal.mimacher.item.Item;
import com.anibal.mimacher.item.ItemRepository;

@SpringBootApplication
public class MimacherApplication {

	public static void main(String[] args) {
		SpringApplication.run(MimacherApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner example1(ItemRepository personRepository) {

		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {

				Item person = new Item();
				person.setDescricao("casaco da nike branco e preto") ;
				person.setTipo("tatat");
				
				
				Item person2 = new Item();
				person2.setDescricao("Camisa da Nike rosa") ;
				person2.setTipo("tatat"); 
				
				personRepository.save(person);
				personRepository.save(person2);


			}
		};
	}

}

package com.example.LibraryForSummer;

import com.example.LibraryForSummer.models.Parents.Genre;
import com.example.LibraryForSummer.models.Parents.Type;
import com.example.LibraryForSummer.repositories.forParents.GenresRepository;
import com.example.LibraryForSummer.repositories.forParents.TypesRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LibrarySpringBoodApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibrarySpringBoodApplication.class, args);
	}

	@Bean
	public CommandLineRunner insertData(GenresRepository genreRepository, TypesRepository typeRepository) {
		return args -> {
			// Добавляем данные в таблицу Genre
			genreRepository.save(new Genre("Fiction"));
			genreRepository.save(new Genre("Non-Fiction"));
			genreRepository.save(new Genre("Science Fiction"));
			genreRepository.save(new Genre("Fantasy"));
			genreRepository.save(new Genre("Mystery"));
			genreRepository.save(new Genre("Thriller"));
			genreRepository.save(new Genre("Romance" ));
			genreRepository.save(new Genre("Historical"));
			genreRepository.save(new Genre("Biography"));
			genreRepository.save(new Genre("Self-Help"));
			genreRepository.save(new Genre("Cookbook"));
			genreRepository.save(new Genre("Travel"));
			genreRepository.save(new Genre("Science"));
			genreRepository.save(new Genre("Childrens"));
			genreRepository.save(new Genre("Young Adult"));
			genreRepository.save(new Genre("Horror"));
			genreRepository.save(new Genre("Classic"));
			genreRepository.save(new Genre("Poetry"));
			genreRepository.save(new Genre("Adventure"));
			genreRepository.save(new Genre("Graphic Novel"));
			genreRepository.save(new Genre("Memoir"));
			genreRepository.save(new Genre("Philosophy"));
			genreRepository.save(new Genre("Religion"));
			genreRepository.save(new Genre("Health"));
			genreRepository.save(new Genre("Business"));
			genreRepository.save(new Genre("Politics"));
			genreRepository.save(new Genre("Psychology"));
			genreRepository.save(new Genre("Education"));
			genreRepository.save(new Genre("Art"));
			genreRepository.save(new Genre("Sports"));
			genreRepository.save(new Genre("Music"));
			genreRepository.save(new Genre("Technology"));

			// Добавляем данные в таблицу Type
			typeRepository.save(new Type("Educational"));
			typeRepository.save(new Type("Popular science"));
			typeRepository.save(new Type("Scientific"));
			typeRepository.save(new Type("Reference"));
			typeRepository.save(new Type("Fiction"));
			typeRepository.save(new Type("Non-Fiction"));
		};
	}
}

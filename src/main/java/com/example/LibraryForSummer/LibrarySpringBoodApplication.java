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
			genreRepository.save(new Genre("Fiction", null));
			genreRepository.save(new Genre("Non-Fiction", null));
			genreRepository.save(new Genre("Science Fiction", null));
			genreRepository.save(new Genre("Fantasy", null));
			genreRepository.save(new Genre("Mystery", null));
			genreRepository.save(new Genre("Thriller", null));
			genreRepository.save(new Genre("Romance", null));
			genreRepository.save(new Genre("Historical", null));
			genreRepository.save(new Genre("Biography", null));
			genreRepository.save(new Genre("Self-Help", null));
			genreRepository.save(new Genre("Cookbook", null));
			genreRepository.save(new Genre("Travel", null));
			genreRepository.save(new Genre("Science", null));
			genreRepository.save(new Genre("Childrens", null));
			genreRepository.save(new Genre("Young Adult", null));
			genreRepository.save(new Genre("Horror", null));
			genreRepository.save(new Genre("Classic", null));
			genreRepository.save(new Genre("Poetry", null));
			genreRepository.save(new Genre("Adventure", null));
			genreRepository.save(new Genre("Graphic Novel", null));
			genreRepository.save(new Genre("Memoir", null));
			genreRepository.save(new Genre("Philosophy", null));
			genreRepository.save(new Genre("Religion", null));
			genreRepository.save(new Genre("Health", null));
			genreRepository.save(new Genre("Business", null));
			genreRepository.save(new Genre("Politics", null));
			genreRepository.save(new Genre("Psychology", null));
			genreRepository.save(new Genre("Education", null));
			genreRepository.save(new Genre("Art", null));
			genreRepository.save(new Genre("Sports", null));
			genreRepository.save(new Genre("Music", null));
			genreRepository.save(new Genre("Technology", null));

			// Добавляем данные в таблицу Type
			typeRepository.save(new Type("Educational", null));
			typeRepository.save(new Type("Popular science", null));
			typeRepository.save(new Type("Scientific", null));
			typeRepository.save(new Type("Reference", null));
			typeRepository.save(new Type("Fiction", null));
			typeRepository.save(new Type("Non-Fiction", null));
		};
	}
}

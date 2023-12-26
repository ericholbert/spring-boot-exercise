package ericholbert.springbootexercise;

import ericholbert.springbootexercise.artwork.Artwork;
import ericholbert.springbootexercise.artwork.ArtworkRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootExerciseApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootExerciseApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(ArtworkRepository artworkRepository) {
		return args -> {
			artworkRepository.save(new Artwork(1L, "Prague, The National Gallery", "O 1552", "Albrecht Dürer", "Feast of the Rose Garlands", "1506", "oil on panel", "162 x 192 cm"));
			artworkRepository.save(new Artwork(2L, "Prague, The National Gallery", "O 84", null, "Votive Panel of Jan Očko from Vlašim", "before 1371", "tempera on panel", "181,5 x 96,5 cm"));
			artworkRepository.save(new Artwork(3L, "Prague, The National Gallery", "DO 4288", "Rembrandt", "Scholar in His Study", "1634", "oil on canvas", "141 x 135 cm"));
			artworkRepository.save(new Artwork(4L, "Prague, The National Gallery", "O 8021", "Pablo Picasso", "Self-Portrait", "1907", "oil on canvas", "56 x 46 cm"));
			artworkRepository.save(new Artwork(5L, "Prague, The National Gallery", "O 5736", "Jakub Schikaneder", "Murder in the House", "1890", "oil on canvas", "203 x 321 cm"));
			artworkRepository.save(new Artwork(6L, "Vienna, The Albertina Museum", "GE87DL", "Claude Monet", "The Water Lily Pond", "1917-1919", "oil on canvas", "100 x 200 cm"));
			artworkRepository.save(new Artwork(7L, "London, The National Gallery", "NG1383", "Johannes Vermeer", "A Young Woman standing at a Virginal", "1670-1672", "oil on canvas", "51,7 x 45,2 cm"));
			artworkRepository.save(new Artwork(8L, "London, The National Gallery", "NG35", "Titian", "Bacchus and Ariadne", "1520-1523", "oil on canvas", "176,5 x 191 cm"));
			artworkRepository.save(new Artwork(9L, "Edinburgh, National Galleries of Scotland", "NG 2839", "Titian", "Diana and Actaeon", "1556-1559", "oil on canvas", "184,5 x 202,2 cm"));
			artworkRepository.save(new Artwork(10L, "Edinburgh, National Galleries of Scotland", "NG 2626", "Antonio Canova", "The Three Graces", "1815-1817", "marble", "173 x 97,2 x 75 cm"));
		};
	}
}

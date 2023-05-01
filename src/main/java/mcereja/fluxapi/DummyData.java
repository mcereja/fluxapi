package mcereja.fluxapi;

import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import mcereja.fluxapi.document.Playlist;
import mcereja.fluxapi.repository.PlaylistRepository;
import reactor.core.publisher.Flux;

/*
 * Executa toda vez que a app starta, exclui todos registros existentes e insere
 *   novas linhas
 * Add o if false p/ignorar a partir do segundo start, pra nao ficar deletando e 
 *   inserindo a todo instante 
 */

@Component  @AllArgsConstructor
public class DummyData implements CommandLineRunner {

	private final PlaylistRepository playlistRepository;
	
    @SuppressWarnings("unused")
	@Override
    public void run(String... args) throws Exception {

    	if (false) {
    	playlistRepository.deleteAll()
            .thenMany(
                    Flux.just("API REST Spring Boot", "Deploy de uma aplicação java no IBM Cloud", "Java 8",
                            "Github", "Spring Security", "Web Service RESTFULL", "Bean no Spring Framework")
                     .map(nome -> new Playlist(UUID.randomUUID().toString(), nome))
                     .flatMap(playlistRepository::save))
            .subscribe(System.out::println);
    	}
    	else
    		System.out.println("IGONRANDO CLASSE DAMMYDATA...");
    }
	
}

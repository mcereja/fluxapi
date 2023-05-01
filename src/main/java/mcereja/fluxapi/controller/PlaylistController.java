package mcereja.fluxapi.controller;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mcereja.fluxapi.document.Playlist;
import mcereja.fluxapi.service.PlaylistService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

/*
 *  Classe com estilo padrao de programacao,existe tb o estilo de programacao funcional
 *  	usado nas classes PlaylistHandler e PlaylistRouter
 *  ATENCAO!!! AMBAS MAPEIAS CONTROLLERs PORTANTO, OU USA UMA OU OUTRA FORMA
 *    Para usar as 2 classes acima comentar as anotacoes:
 *      @RestController e @RequestMapping("/playlist")  dessa classe
 *   Para ativar essa classe comentar de:
 *   	PlaylistRouter.java    a anotacao @Configuration e @Bean
 *      PlaylistHandler.java   a anotacao @Component 
 *      
 *  OBS. O endpoint /webflux tem comportamento diferente se rodar no google chrome (normal)
 *    e no firefox(diferente)
 */

@RestController
@RequestMapping("/playlist")
public class PlaylistController {

	@Autowired
	PlaylistService playService;
	
	@GetMapping
	public Flux<Playlist> listaTodos(){
		return playService.findAll();
	}
	
	@GetMapping("/{id}")
	public Mono<Playlist> buscar(@PathVariable String id){
		return playService.findById(id);
	}
	
	@PostMapping
	public Mono<Playlist> salvar(@RequestBody Playlist playlist){
		return playService.save(playlist);
	}

  // Se fizer varias requisicoes nesse end-point vera que ele nao vai aguardar um 
  // terminar para iniciar o outro
	@GetMapping(value="/webflux", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Tuple2<Long, Playlist>> getPlaylistByWebflux(){

		System.out.println("---Start get Playlists by WEBFLUX--- " + LocalDateTime.now());
		Flux<Long> interval = Flux.interval(Duration.ofSeconds(10));
        Flux<Playlist> playlistFlux = playService.findAll();

        return Flux.zip(interval, playlistFlux);
        
	}

  // Nesse end-point da forma tradicional ele nao usa o flux e term que aguardar
  // uma requisicao terminar para continuar
	@GetMapping(value="/playtradicional")
	public List<String> getPlaylistByMvc() throws InterruptedException {

		System.out.println("---Start get Playlists by MVC--- " + LocalDateTime.now());


		List<String> playlistList = new ArrayList<>();
		playlistList.add("Java 8");
		playlistList.add("Spring Security");
		playlistList.add("Github");
		playlistList.add("Deploy de uma aplicação java no IBM Cloud");
		playlistList.add("Bean no Spring Framework");
		TimeUnit.SECONDS.sleep(15);

		return playlistList;

	}
	
}

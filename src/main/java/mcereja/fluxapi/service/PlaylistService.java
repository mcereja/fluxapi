package mcereja.fluxapi.service;

import mcereja.fluxapi.document.Playlist;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/*
 * Para trabalhar com arquitetura reativa, e' necessario indicar qdo ira trabalhar
 * 	com uma collection ou um unico registro:
 * No findAll() Retorna um Flux ao inves de uma List, representa colection
 * Qdo simboliza um unico registro usa-se o Mono 
 */

public interface PlaylistService {

	Flux<Playlist> findAll();	
	Mono<Playlist> findById(String id);
	Mono<Playlist> save(Playlist playlist);
	
}

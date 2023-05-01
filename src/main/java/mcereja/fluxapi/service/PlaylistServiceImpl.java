package mcereja.fluxapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mcereja.fluxapi.document.Playlist;
import mcereja.fluxapi.repository.PlaylistRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PlaylistServiceImpl implements PlaylistService {

	@Autowired
	private PlaylistRepository playRepo;
	
	@Override
	public Flux<Playlist> findAll() {
		return playRepo.findAll();
	}

	@Override
	public Mono<Playlist> findById(String id) {
		return playRepo.findById(id);
	}

	@Override
	public Mono<Playlist> save(Playlist playlist) {
		return playRepo.save(playlist);
	}

}

package mcereja.fluxapi.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import mcereja.fluxapi.document.Playlist;

public interface PlaylistRepository extends ReactiveMongoRepository<Playlist, String> {

}

package com.bridge.api.mongo.reposetory;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bridge.api.model.Note;

public interface NoteRepository extends MongoRepository<Note, String>{
	
}

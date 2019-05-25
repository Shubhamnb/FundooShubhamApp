package com.bridge.reposetory;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bridge.model.Book;

public interface BookReposetory extends MongoRepository<Book, Integer>{

}

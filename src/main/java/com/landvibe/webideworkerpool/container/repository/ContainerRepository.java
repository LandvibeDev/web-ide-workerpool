package com.landvibe.webideworkerpool.container.repository;

import com.landvibe.webideworkerpool.container.model.Container;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ContainerRepository extends MongoRepository<Container, String> {
    List<Container> findAllByUserId(String userId);
}

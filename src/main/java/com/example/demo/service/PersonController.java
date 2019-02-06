package com.example.demo.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.example.demo.domain.Person;

@RepositoryRestResource(collectionResourceRel="person",path="person")
public interface PersonController extends PagingAndSortingRepository<Person, UUID> {

	
	List<Person> findByEfternamn(@Param("efternamn") String efternamn);
}

package com.example.demo.service;

import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.demo.domain.Geometri;

@RepositoryRestResource(collectionResourceRel="geometri",path="geometri")
public interface GeometriController extends PagingAndSortingRepository<Geometri, UUID> {

}

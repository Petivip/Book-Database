package com.example.demo;

import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface BookRepository extends CrudRepository<book, Long> {
    ArrayList<book> findBySKUContainingIgnoreCaseOrAuthorContainingIgnoreCase(String entry1, String entry2);
}
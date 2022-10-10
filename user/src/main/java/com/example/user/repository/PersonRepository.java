package com.example.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.user.model.Person;

public interface PersonRepository extends JpaRepository<Person,Long>{
}

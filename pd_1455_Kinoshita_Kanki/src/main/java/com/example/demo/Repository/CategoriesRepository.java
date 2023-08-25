package com.example.demo.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.Categories;

public interface CategoriesRepository extends JpaRepository<Categories, Integer> {

	Optional<Categories> findByName(String categoryName);

}
